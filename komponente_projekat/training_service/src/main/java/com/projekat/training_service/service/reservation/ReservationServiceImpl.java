package com.projekat.training_service.service.reservation;

import com.projekat.training_service.configuration.userservice.dto.DiscountDto;
import com.projekat.training_service.configuration.userservice.dto.IncrementReservationCountDto;
import com.projekat.training_service.domain.Appointment;
import com.projekat.training_service.domain.Reservation;
import com.projekat.training_service.dto.ReservationCreateDto;
import com.projekat.training_service.dto.ReservationDto;
import com.projekat.training_service.exceptions.NotFoundException;
import com.projekat.training_service.listener.helper.MessageHelper;
import com.projekat.training_service.mapper.ReservationMapper;
import com.projekat.training_service.repository.AppointmentRepository;
import com.projekat.training_service.repository.ReservationRepository;
import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.retry.Retry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class ReservationServiceImpl implements ReservationService {
    private ReservationRepository reservationRepository;
    private ReservationMapper reservationMapper;
    private AppointmentRepository appointmentRepository;
    private RestTemplate userServiceRestTemplate;
    private JmsTemplate jmsTemplate;
    private String incrementReservationCountDestination;
    //    private String emailQueueDestination;
    private MessageHelper messageHelper;
    private Retry reservationServiceRetry;
    private Bulkhead reservationServiceBulkhead;

    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationMapper reservationMapper,
                                  AppointmentRepository appointmentRepository, RestTemplate userServiceRestTemplate, JmsTemplate jmsTemplate,
                                  @Value("${destination.incrementReservationCount}") String incrementReservationCountDestination, MessageHelper messageHelper,
                                  Retry reservationServiceRetry, Bulkhead reservationServiceBulkhead) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.appointmentRepository = appointmentRepository;
        this.userServiceRestTemplate = userServiceRestTemplate;
        this.jmsTemplate = jmsTemplate;
        this.incrementReservationCountDestination = incrementReservationCountDestination;
        this.messageHelper = messageHelper;
        this.reservationServiceRetry = reservationServiceRetry;
        this.reservationServiceBulkhead = reservationServiceBulkhead;
//        this.emailQueueDestination = emailQueueDestination;
//        this.objectMapper = objectMapper;
    }

    @Override
    public Page<ReservationDto> findAll(Pageable pageable) {
        return reservationRepository.findAll(pageable)
                .map(reservationMapper::reservationToReservationDto);
    }

    @Override
    public void addReservation(ReservationCreateDto reservationCreateDto) {
        Appointment appointment = appointmentRepository.findById(reservationCreateDto.getAppointmentId()).get();
        if (appointment.getCapacity() == 0) {
            return;
        }
        appointment.setCapacity(appointment.getCapacity() - 1);
        appointmentRepository.save(appointment);

        DiscountDto discountDto = Bulkhead.decorateSupplier(reservationServiceBulkhead,
                () -> Retry.decorateSupplier(reservationServiceRetry, () -> getDiscount(reservationCreateDto.getUserId())).get()).get();

//        ResponseEntity<DiscountDto> discountDtoResponseEntity = userServiceRestTemplate.exchange("/client/" +
//                reservationCreateDto.getUserId() + "/discount", HttpMethod.GET, null, DiscountDto.class);

        BigDecimal price = appointment.getTraining().getPrice().divide(BigDecimal.valueOf(100))
                .multiply(BigDecimal.valueOf(100 - Objects.requireNonNull(discountDto.getDiscount())));

        Reservation reservation = new Reservation(appointment, reservationCreateDto.getUserId(), price);
        reservationRepository.save(reservation);
        jmsTemplate.convertAndSend(incrementReservationCountDestination, messageHelper.createTextMessage(new IncrementReservationCountDto(reservationCreateDto.getUserId())));
//        jmsTemplate.convertAndSend(emailQueueDestination, messageHelper.createTextMessage("aaaaa"));
    }

    private DiscountDto getDiscount(Long userId) {
        System.out.println("[" + System.currentTimeMillis() / 1000 + "] Getting discount for id: " + userId);
        try {
            Thread.sleep(5000);
            return userServiceRestTemplate.exchange("/client/" + userId + "/discount", HttpMethod.GET, null, DiscountDto.class).getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                throw new NotFoundException(String.format("Discount for User with id: %d not found.", userId));
            throw new RuntimeException("Internal server error.");
        } catch (Exception e) {
            throw new RuntimeException("Internal server error.");
        }
    }

    @Override
    public void deleteById(Long id) {
        Reservation reservation = reservationRepository.findById(id).get();
        reservation.getAppointment().setCapacity(reservation.getAppointment().getCapacity() + 1);
        reservationRepository.deleteById(id);
    }
}

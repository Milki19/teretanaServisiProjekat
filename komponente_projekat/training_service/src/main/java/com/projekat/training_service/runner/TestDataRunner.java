package com.projekat.training_service.runner;

import com.projekat.training_service.domain.Appointment;
import com.projekat.training_service.domain.Gymnasium;
import com.projekat.training_service.domain.Training;
import com.projekat.training_service.domain.TrainingType;
import com.projekat.training_service.repository.AppointmentRepository;
import com.projekat.training_service.repository.GymnasiumRepository;
import com.projekat.training_service.repository.TrainingRepository;
import com.projekat.training_service.repository.TrainingTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {
    private GymnasiumRepository gymnasiumRepository;
    private TrainingTypeRepository trainingTypeRepository;
    private TrainingRepository trainingRepository;
    private AppointmentRepository appointmentRepository;

    public TestDataRunner(GymnasiumRepository gymnasiumRepository, TrainingTypeRepository trainingTypeRepository, TrainingRepository trainingRepository, AppointmentRepository appointmentRepository) {
        this.gymnasiumRepository = gymnasiumRepository;
        this.trainingTypeRepository = trainingTypeRepository;
        this.trainingRepository = trainingRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //Insert gyms
        Gymnasium gymnasium1 = new Gymnasium("Gymnasium 01", "First Gymnasium for Group trainings", 5, 12);
        Gymnasium gym2 = new Gymnasium("Gymnasium 02", "Second Gymnasium for Individual trainings", 2, 1);
        Gymnasium gym3 = new Gymnasium("Gymnasium 03", "Third Gymnasium for Group trainings", 3, 10);
        Gymnasium gym4 = new Gymnasium("Gymnasium 04", "Fourth Gymnasium for Individual trainings", 1, 1);
        gymnasiumRepository.save(gymnasium1);
        gymnasiumRepository.save(gym2);
        gymnasiumRepository.save(gym3);
        gymnasiumRepository.save(gym4);

        //Insert training types
        TrainingType tt1 = new TrainingType("Group", "Yoga");
        TrainingType tt2 = new TrainingType("Group", "Pilates");
        TrainingType tt3 = new TrainingType("Individual", "Powerlifting");
        TrainingType tt4 = new TrainingType("Individual", "Calisthenics");
        trainingTypeRepository.save(tt1);
        trainingTypeRepository.save(tt2);
        trainingTypeRepository.save(tt3);
        trainingTypeRepository.save(tt4);

        //Insert gym trainings
        Training gt1 = new Training(gymnasium1, tt1, BigDecimal.valueOf(10));
        Training gt2 = new Training(gymnasium1, tt2, BigDecimal.valueOf(15));
        Training gt3 = new Training(gym2, tt3, BigDecimal.valueOf(15));
        Training gt4 = new Training(gym3, tt1, BigDecimal.valueOf(20));
        Training gt5 = new Training(gym4, tt3, BigDecimal.valueOf(20));
        Training gt6 = new Training(gym4, tt4, BigDecimal.valueOf(25));

        trainingRepository.save(gt1);
        trainingRepository.save(gt2);
        trainingRepository.save(gt3);
        trainingRepository.save(gt4);
        trainingRepository.save(gt5);
        trainingRepository.save(gt6);

        //Insert appointments
        //gym1
        appointmentRepository.save(new Appointment(LocalDate.parse("17/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "11:00", "12:00", gt1, gt1.getGym().getCapacity()));
        appointmentRepository.save(new Appointment(LocalDate.parse("17/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "12:00", "13:00", gt2, gt2.getGym().getCapacity()));
        appointmentRepository.save(new Appointment(LocalDate.parse("17/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "18:00", "19:00", gt1, gt1.getGym().getCapacity()));
        appointmentRepository.save(new Appointment(LocalDate.parse("19/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "20:00", "21:00", gt2, gt2.getGym().getCapacity()));
        appointmentRepository.save(new Appointment(LocalDate.parse("19/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "22:00", "23:00", gt2, gt2.getGym().getCapacity()));
        appointmentRepository.save(new Appointment(LocalDate.parse("20/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "09:00", "10:00", gt1, gt1.getGym().getCapacity()));
        appointmentRepository.save(new Appointment(LocalDate.parse("20/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "12:00", "13:00", gt2, gt2.getGym().getCapacity()));
        //gym2
        appointmentRepository.save(new Appointment(LocalDate.parse("17/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "09:00", "10:00", gt3, gt3.getGym().getCapacity()));
        appointmentRepository.save(new Appointment(LocalDate.parse("17/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "12:00", "13:00", gt3, gt3.getGym().getCapacity()));
        appointmentRepository.save(new Appointment(LocalDate.parse("17/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "14:00", "15:00", gt3, gt3.getGym().getCapacity()));
        appointmentRepository.save(new Appointment(LocalDate.parse("19/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "15:00", "16:00", gt3, gt3.getGym().getCapacity()));
        appointmentRepository.save(new Appointment(LocalDate.parse("19/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "19:00", "20:00", gt3, gt3.getGym().getCapacity()));
        appointmentRepository.save(new Appointment(LocalDate.parse("20/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "08:00", "09:00", gt3, gt3.getGym().getCapacity()));
        appointmentRepository.save(new Appointment(LocalDate.parse("20/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "11:00", "12:00", gt3, gt3.getGym().getCapacity()));
        //gym3
        appointmentRepository.save(new Appointment(LocalDate.parse("17/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "10:00", "11:00", gt4, gt4.getGym().getCapacity()));
        appointmentRepository.save(new Appointment(LocalDate.parse("17/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "17:00", "18:00", gt4, gt4.getGym().getCapacity()));
        appointmentRepository.save(new Appointment(LocalDate.parse("17/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "20:00", "21:00", gt4, gt4.getGym().getCapacity()));
        appointmentRepository.save(new Appointment(LocalDate.parse("19/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "09:00", "10:00", gt4, gt4.getGym().getCapacity()));
        appointmentRepository.save(new Appointment(LocalDate.parse("19/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "14:00", "15:00", gt4, gt4.getGym().getCapacity()));
        appointmentRepository.save(new Appointment(LocalDate.parse("20/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "15:00", "16:00", gt4, gt4.getGym().getCapacity()));
        appointmentRepository.save(new Appointment(LocalDate.parse("20/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "21:00", "22:00", gt4, gt4.getGym().getCapacity()));
        //gym4
        appointmentRepository.save(new Appointment(LocalDate.parse("17/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "09:00", "10:00", gt5, gt5.getGym().getCapacity()));
        appointmentRepository.save(new Appointment(LocalDate.parse("17/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "19:00", "20:00", gt6, gt6.getGym().getCapacity()));
        appointmentRepository.save(new Appointment(LocalDate.parse("17/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "21:00", "22:00", gt6, gt6.getGym().getCapacity()));
        appointmentRepository.save(new Appointment(LocalDate.parse("19/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "10:00", "11:00", gt5, gt5.getGym().getCapacity()));
        appointmentRepository.save(new Appointment(LocalDate.parse("19/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "18:00", "19:00", gt6, gt6.getGym().getCapacity()));
        appointmentRepository.save(new Appointment(LocalDate.parse("20/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "09:00", "10:00", gt5, gt5.getGym().getCapacity()));
        appointmentRepository.save(new Appointment(LocalDate.parse("20/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "20:00", "21:00", gt5, gt5.getGym().getCapacity()));
    }
}

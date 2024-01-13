package com.projekat.demo.ms_sm_ac.runner;
import com.projekat.demo.ms_sm_ac.domain.Admin;
import com.projekat.demo.ms_sm_ac.domain.UserType;
import com.projekat.demo.ms_sm_ac.repository.AdminResopitory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {

    private AdminResopitory adminResopitory;

    public TestDataRunner(AdminResopitory adminResopitory) {
        this.adminResopitory = adminResopitory;
    }

    @Override
    public void run(String... args) throws Exception {
        //Insert admin
        Admin admin = new Admin();
        admin.setUsername("aleks7ndar");
        admin.setBirthDate(LocalDate.of(2003, 5, 15));
        admin.setEmail("acumic11622rn@raf.rs");
        admin.setPassword("admin");
        admin.setFirstName("Aleksandar");
        admin.setLastName("Cumic");
        admin.setUserType(UserType.ADMIN);

        Admin admin1 = new Admin();
        admin1.setUsername("Milki19");
        admin1.setBirthDate(LocalDate.of(2002, 9, 19));
        admin1.setEmail("smilic12222rn@raf.rs");
        admin1.setPassword("admin");
        admin1.setFirstName("Stefan");
        admin1.setLastName("Milic");
        admin1.setUserType(UserType.ADMIN);

        adminResopitory.save(admin);
        adminResopitory.save(admin1);
    }
}

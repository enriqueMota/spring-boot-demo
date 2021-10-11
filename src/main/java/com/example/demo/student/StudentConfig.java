package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student enrique = new Student(
				"Enrique",
				"abelmota@live.com",
				LocalDate.of(1999, Month.MARCH, 4)
            );
            Student pepe = new Student(
				"Pepe",
				"pepe@live.com",
				LocalDate.of(2004, Month.MAY, 19)
			);

            repository.saveAll(
                List.of(enrique, pepe)
            );
        };
    }
}

/*
    saveAll method saves values in the db
*/

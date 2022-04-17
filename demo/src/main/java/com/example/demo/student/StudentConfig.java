package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return  args -> {
            Student hby = new Student(
                    "HBY",
                    LocalDate.of(1991, Month.NOVEMBER, 4),
                    "hby@mail"
            );

            Student jack = new Student(
                    "Jack",
                    LocalDate.of(1994, Month.MARCH, 25),
                    "jack@mail"
            );

            repository.saveAll(List.of(hby, jack));
        };
    }
}

package com.vacancy.vacancy_aggregator_core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VacancyAggregatorCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(VacancyAggregatorCoreApplication.class, args);
    }

}

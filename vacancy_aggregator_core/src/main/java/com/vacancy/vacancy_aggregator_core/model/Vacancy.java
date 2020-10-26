package com.vacancy.vacancy_aggregator_core.model;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Vacancy {
    private String id;
    private String name;
    @EqualsAndHashCode.Exclude private Date date;
    private String cityName;
    private String companyName;
    private String shortDescription;
    @EqualsAndHashCode.Exclude private String designBannerUrl;
}

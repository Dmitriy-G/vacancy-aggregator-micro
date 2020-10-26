package com.vacancy.vacancy_aggregator_core.source;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Setter
@Getter
@Configuration
@EqualsAndHashCode
@ToString
public class RabotaUASource implements Source {
    //TODO: get URL from properties file
    private String url;
    private HttpHeaders headers;

    public RabotaUASource() {
        this.url = "https://api.rabota.ua/vacancy/search?keyWords=java";
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }
}

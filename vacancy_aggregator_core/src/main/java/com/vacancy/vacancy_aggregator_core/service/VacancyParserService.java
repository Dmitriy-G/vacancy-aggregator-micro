package com.vacancy.vacancy_aggregator_core.service;


import com.vacancy.vacancy_aggregator_core.model.Vacancy;

import java.util.List;

/**
 * Service for parsing data from vendors to model
 *
 * @author Dmitriy G
 */
public interface VacancyParserService {
    /**
     * Parse from JSON to model
     *
     * @param json Data in JSON format
     */
    List<Vacancy> parse(String json);
}

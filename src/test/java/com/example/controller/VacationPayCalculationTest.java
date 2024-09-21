package com.example.controller;

import com.example.service.days.DaysCalculationServiceImpl;
import com.example.service.vacation.VacationPayCalculatorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class VacationPayCalculationTest {

    private VacationPayCalculatorServiceImpl vacationPayCalculatorService;
    private DaysCalculationServiceImpl daysCalculationService;

    private final BigDecimal testAverageSalaryPerYear = new BigDecimal("45000.00");
    private final int testVacationDays = 14;

    @BeforeEach
    void init() {
        log.info("startup");
        vacationPayCalculatorService = new VacationPayCalculatorServiceImpl();
        daysCalculationService = new DaysCalculationServiceImpl();
    }

    @AfterEach
    public void teardown() {
        log.info("teardown");
        vacationPayCalculatorService = null;
        daysCalculationService = null;
    }

    @Test
    @DisplayName("Vacation pay test without holidays and weekends")
    void calculationOfVacationPayForEmployeeUsingSimpleQueryTest() {

        BigDecimal actual = vacationPayCalculatorService.getVacationPayCalculation(testAverageSalaryPerYear, testVacationDays).getVacationPay();
        assertEquals(BigDecimal.valueOf(18706.76), actual);
    }

    @Test
    @DisplayName("Vacation pay test with holidays and weekends")
    void calculationOfVacationPayForEmployeeUsingQueryWithDateTest() {

        LocalDate testStartVacationDate = LocalDate.of(2024, 2, 21);

        int testPaidVacationDays = daysCalculationService.calculatePaidDays(testVacationDays, testStartVacationDate);
        BigDecimal actual = vacationPayCalculatorService.getVacationPayCalculation(testAverageSalaryPerYear, testPaidVacationDays).getVacationPay();
        assertEquals(BigDecimal.valueOf(12025.56), actual);
    }
}
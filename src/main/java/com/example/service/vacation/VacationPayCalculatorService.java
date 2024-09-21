package com.example.service.vacation;

import com.example.dto.VacationPayResponse;

import java.math.BigDecimal;

public interface VacationPayCalculatorService {

    VacationPayResponse getVacationPayCalculation(BigDecimal averageSalaryPerYear,
                                                  int vacationDays);
}

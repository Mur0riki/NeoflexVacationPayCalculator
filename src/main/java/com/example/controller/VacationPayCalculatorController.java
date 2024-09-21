package com.example.controller;

import com.example.dto.VacationPayResponse;
import com.example.service.days.DaysCalculationService;
import com.example.service.vacation.VacationPayCalculatorService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;


@RestController
@RequestMapping("/vacationpaycalculator")
public class VacationPayCalculatorController {
    private final VacationPayCalculatorService vacationPayCalculatorService;
    private final DaysCalculationService daysCalculationService;

    public VacationPayCalculatorController(VacationPayCalculatorService vacationPayCalculatorService,
                                           DaysCalculationService daysCalculationService) {
        this.vacationPayCalculatorService = vacationPayCalculatorService;
        this.daysCalculationService = daysCalculationService;
    }


    @GetMapping("/calculate")
    public VacationPayResponse getVacationPay(
            @RequestParam("averageSalary") BigDecimal averageSalaryPerYear,
            @RequestParam("vacationDays") int vacationDays,
            @RequestParam("vacationStartDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> startVacationDate
    ) {
        if (startVacationDate.isPresent()) {
            vacationDays = daysCalculationService.calculatePaidDays(vacationDays, startVacationDate.get());
        }
        return vacationPayCalculatorService.getVacationPayCalculation(averageSalaryPerYear, vacationDays);
    }
}
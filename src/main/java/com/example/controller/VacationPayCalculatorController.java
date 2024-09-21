package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/vacationpaycalculator")
public class VacationPayCalculatorController {


    @GetMapping("/calculate")
    public String getVacationPay(
    ) {
        return "yes, im alive";
    }
}
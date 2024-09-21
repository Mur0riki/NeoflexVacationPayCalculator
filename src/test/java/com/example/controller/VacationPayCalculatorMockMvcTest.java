package com.example.controller;

import com.example.ResolversAbstractCommonConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class VacationPayCalculatorMockMvcTest extends ResolversAbstractCommonConfiguration {

    public final static String VACATION_PAY_API = "/vacationpaycalculator/calculate";

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void calculationOfVacationPayForEmployeeTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get(VACATION_PAY_API)
                        .param("averageSalary", String.valueOf(45000.00))
                        .param("vacationDays", String.valueOf(14))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.vacationPay").value(BigDecimal.valueOf(18706.76)))
                .andReturn();

        log.info(result.getResponse().getContentAsString());
    }

    @Test
    void calculationOfVacationPayForEmployeeWithDateTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get(VACATION_PAY_API)
                        .param("averageSalary", String.valueOf(45000.00))
                        .param("vacationDays", String.valueOf(14))
                        .param("vacationStartDate", "2024-09-21")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.vacationPay").value(BigDecimal.valueOf(13361.4)))
                .andReturn();

        log.info(result.getResponse().getContentAsString());
    }
}
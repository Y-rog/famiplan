package com.yrog.famiplan.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(DashboardControllerTest.class)
public class DashboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDashboardTemplate() throws Exception {
        // Simule une requête GET sur "/dashboard"
        mockMvc.perform(get("/dashboard"))
                // Vérifie que le statut HTTP est 200 (OK)
                .andExpect(status().isOk())
                // Vérifie que le template "home" est retourné
                .andExpect(view().name("dashboard"));

    }
}

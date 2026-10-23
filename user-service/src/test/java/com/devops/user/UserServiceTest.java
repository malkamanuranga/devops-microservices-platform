package com.devops.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class UserServiceTest {
    @Autowired MockMvc mvc;

    @Test void findsExistingUser() throws Exception {
        mvc.perform(get("/users/1")).andExpect(status().isOk());
    }

    @Test void missingUserReturns404() throws Exception {
        mvc.perform(get("/users/99")).andExpect(status().isNotFound());
    }
}
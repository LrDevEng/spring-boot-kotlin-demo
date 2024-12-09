package com.example.demo.controller

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
internal class GuestControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `should return all guests`() {
        // When/Then
        mockMvc.get("/api/guests")
            .andDo{print()}
            .andExpect{
                status{isOk()}
                jsonPath("$[0].name"){value("John")}
                jsonPath("$[0].surname"){value("Doe")}
                jsonPath("$[1].name"){value("Jane")}
                jsonPath("$[1].surname"){value("Doe")}
                jsonPath("$[2].name"){value("John")}
                jsonPath("$[2].surname"){value("Smith")}
                jsonPath("$[3].name"){value("Jane")}
                jsonPath("$[3].surname"){value("Smith")}
            }
    }
}
package com.example.demo.controller

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
internal class GuestControllerTest {
    private val baseUrl = "/api/guests"

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `should return all guests`() {
        // When/Then
        mockMvc.get(baseUrl)
            .andDo { print() }
            .andExpect {
                status { isOk() }
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

    @Test
    fun `should return guest based on id`() {
        // When/Then
        mockMvc.get("$baseUrl/0")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.name"){value("John")}
                jsonPath("$.surname"){value("Doe")}
            }
    }

    @Test
    fun `should return NOT FOUND if guest with given id does not exist`() {
        // When/Then
        mockMvc.get("$baseUrl/99")
            .andDo { print() }
            .andExpect {
                status { isNotFound() }
            }
    }
}
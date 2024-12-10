package com.example.demo.controller

import com.example.demo.model.Guest
import com.example.demo.model.UpdateGuestRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.patch
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class GuestControllerTest {
    private val baseUrl = "/api/guests"

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    // --- Get request tests ---
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

    // --- Post request tests ---
    @Test
    fun `should add new guest`() {
        // Given
        val newGuest = Guest(5, "Sherlock", "Holmes", true)

        // When
        val postResponse = mockMvc.post(baseUrl) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(newGuest)
        }

        // Then
        postResponse
            .andDo { print() }
            .andExpect {
                status { isCreated() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.name"){value("Sherlock")}
                jsonPath("$.surname"){value("Holmes")}
            }
    }

    @Test
    fun `should return BAD REQUEST if guest with given id already exists`() {
        // Given
        val newGuest = Guest(0, "Sherlock", "Holmes", true)

        // When
        val postResponse = mockMvc.post(baseUrl) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(newGuest)
        }

        // Then
        postResponse
            .andDo { print() }
            .andExpect {
                status { isBadRequest() }
            }
    }

    // --- Patch request tests ---
    @Test
    fun `should update existing guest`() {
        // Given
        val updateGuestRequest = UpdateGuestRequest(null, null, participation = false);

        // When
        val patchResponse = mockMvc.patch("$baseUrl/0") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(updateGuestRequest)
        }

        // Then
        patchResponse
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.name"){value("John")}
                jsonPath("$.surname"){value("Doe")}
                jsonPath("$.participation"){value(false)}
            }
    }

    @Test
    fun `should return NOT FOUND if guest with given id does not exist for update`() {
        // Given
        val updateGuestRequest = UpdateGuestRequest(null, null, participation = false);

        // When
        val patchResponse = mockMvc.patch("$baseUrl/99") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(updateGuestRequest)
        }

        // Then
        patchResponse
            .andDo { print() }
            .andExpect {
                status { isNotFound() }
            }
    }
}
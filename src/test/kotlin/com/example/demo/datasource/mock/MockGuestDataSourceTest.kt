package com.example.demo.datasource.mock

import org.junit.jupiter.api.Assertions.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class MockGuestDataSourceTest {
    private val mockGuestDataSource = MockGuestDataSource()

    @Test
    fun `should provide a collection of guests`() {
        // When
        val guests = mockGuestDataSource.retrieveGuests()
        // Then
        Assertions.assertThat(guests).isNotEmpty
    }

    @Test
    fun `should provide mock data`() {
        // When
        val guests = mockGuestDataSource.retrieveGuests()
        // Then
        Assertions.assertThat(guests).allMatch { guest ->
            guest.name.isNotBlank()
        }
        Assertions.assertThat(guests).allMatch { guest ->
            guest.id >= 0
        }
    }
}
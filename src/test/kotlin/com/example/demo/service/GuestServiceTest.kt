package com.example.demo.service

import com.example.demo.datasource.GuestDataSource
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GuestServiceTest {
    private val dataSource: GuestDataSource = mockk(relaxed = true)
    private val guestService = GuestService(dataSource)

    @Test
    fun `should call its data source to retrieve banks`() {
        // When
        guestService.getGuests()

        // Then
        verify(exactly = 1) { dataSource.retrieveGuests() }
    }
}
package com.example.demo.datasource.mock

import com.example.demo.datasource.GuestDataSource
import com.example.demo.model.Guest
import org.springframework.stereotype.Repository

@Repository
class MockGuestDataSource: GuestDataSource {

    val guests = listOf(
        Guest(0, "John", "Doe", true),
        Guest(1, "Jane", "Doe", false),
        Guest(2, "John", "Smith", true),
        Guest(3, "Jane", "Smith", false),
    )

    override fun retrieveGuests(): Collection<Guest> = guests
}
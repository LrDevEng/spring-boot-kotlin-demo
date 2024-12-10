package com.example.demo.datasource.mock

import com.example.demo.datasource.GuestDataSource
import com.example.demo.model.Guest
import org.springframework.stereotype.Repository

@Repository
class MockGuestDataSource: GuestDataSource {

    val guests = mutableListOf(
        Guest(0, "John", "Doe", true),
        Guest(1, "Jane", "Doe", false),
        Guest(2, "John", "Smith", true),
        Guest(3, "Jane", "Smith", false),
    )

    override fun retrieveGuests(): Collection<Guest> = guests
    override fun retrieveGuest(id: Int): Guest = guests.firstOrNull() { it.id == id } ?: throw NoSuchElementException("Could not find guest with id $id")
    override fun createGuest(guest: Guest): Guest {
        if (guests.any { it.id == guest.id }) {
            throw IllegalArgumentException("Guest with id ${guest.id} already exists.")
        }
        guests.add(guest)
        return guest
    }
}
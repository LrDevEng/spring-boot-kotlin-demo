package com.example.demo.datasource

import com.example.demo.model.Guest
import com.example.demo.model.UpdateGuestRequest

interface GuestDataSource {
    fun retrieveGuests(): Collection<Guest>
    fun retrieveGuest(id: Int): Guest
    fun createGuest(guest: Guest): Guest
    fun updateGuest(id: Int, request: UpdateGuestRequest): Guest
}
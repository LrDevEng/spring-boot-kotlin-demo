package com.example.demo.service

import com.example.demo.datasource.GuestDataSource
import com.example.demo.model.Guest
import org.springframework.stereotype.Service

@Service
class GuestService (private val dataSource: GuestDataSource) {
    fun getGuests(): Collection<Guest> = dataSource.retrieveGuests()
    fun getGuest(id: Int): Guest = dataSource.retrieveGuest(id)
    fun addGuest(guest: Guest): Guest = dataSource.createGuest(guest)
}
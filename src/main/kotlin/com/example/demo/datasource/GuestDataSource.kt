package com.example.demo.datasource

import com.example.demo.model.Guest

interface GuestDataSource {
    fun retrieveGuests(): Collection<Guest>
}
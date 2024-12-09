package com.example.demo.controller

import com.example.demo.model.Guest
import com.example.demo.service.GuestService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/guests")
class GuestController(
    private val service: GuestService
) {
    @GetMapping
    fun getGuests(): Collection<Guest> = service.getGuests()
}
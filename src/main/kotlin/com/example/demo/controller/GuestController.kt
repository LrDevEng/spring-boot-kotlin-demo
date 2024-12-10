package com.example.demo.controller

import com.example.demo.model.Guest
import com.example.demo.service.GuestService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/guests")
class GuestController(
    private val service: GuestService
) {
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> = ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @GetMapping
    fun getGuests(): Collection<Guest> = service.getGuests()

    @GetMapping("/{id}")
    fun getGuest(@PathVariable id: Int): Guest = service.getGuest(id)
}
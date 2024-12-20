package com.example.demo.controller

import com.example.demo.model.Guest
import com.example.demo.model.UpdateGuestRequest
import com.example.demo.service.GuestService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/guests")
class GuestController(
    private val service: GuestService
) {
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> = ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> = ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping
    fun getGuests(): Collection<Guest> = service.getGuests()

    @GetMapping("/{id}")
    fun getGuest(@PathVariable id: Int): Guest = service.getGuest(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addGuest(@RequestBody guest: Guest): Guest = service.addGuest(guest)

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateGuest(@PathVariable id: Int, @RequestBody updateGuestRequest: UpdateGuestRequest): Guest = service.updateGuest(id, updateGuestRequest)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteGuest(@PathVariable id: Int): Guest = service.deleteGuest(id)
}
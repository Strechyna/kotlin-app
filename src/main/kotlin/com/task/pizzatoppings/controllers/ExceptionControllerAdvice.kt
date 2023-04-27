package com.task.pizzatoppings.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {

    data class ErrorMessage(
        val status: HttpStatus,
        val exception: String
    )

    @ExceptionHandler
    fun handleIllegalStateException(ex: ToppingsNotFoundException): ResponseEntity<ErrorMessage> {
        return ResponseEntity(ErrorMessage(HttpStatus.NOT_FOUND, ex.toString()), HttpStatus.BAD_REQUEST)
    }
}

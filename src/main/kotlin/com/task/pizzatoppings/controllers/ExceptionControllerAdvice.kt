package com.task.pizzatoppings.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {

    class ErrorMessageModel(
        val status: HttpStatus? = null,
        val exception: String? = null
    )

    @ExceptionHandler
    fun handleIllegalStateException(ex: ToppingsNotFoundException): ResponseEntity<ErrorMessageModel> {

        val errorMessage = ErrorMessageModel(
            HttpStatus.NOT_FOUND,
            ex.toString()
        )
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }
}

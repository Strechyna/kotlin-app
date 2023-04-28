package com.task.pizzatoppings.advices

import com.task.pizzatoppings.exceptions.CustomException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {

    data class ExceptionBody (
        val exception: String,
        val message: String?
    )

    @ExceptionHandler
    fun handleIllegalStateException(ex: CustomException): ResponseEntity<ExceptionBody> {
        return ResponseEntity.status(ex.status).body(ExceptionBody(ex.javaClass.name, ex.message))
    }
}

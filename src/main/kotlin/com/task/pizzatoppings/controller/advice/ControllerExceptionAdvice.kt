package com.task.pizzatoppings.controller.advice

import com.task.pizzatoppings.exception.ToppingsNotFoundException
import jakarta.validation.ValidationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import java.time.Instant


@RestControllerAdvice
class ControllerExceptionAdvice {

    data class ExceptionBody (
        val instant: Instant,
        val status: HttpStatus,
        val message: String?,
        val path: String
    )

    @ExceptionHandler(ToppingsNotFoundException::class)
    fun handleToppingsNotFoundException(ex: ToppingsNotFoundException, request: WebRequest): ResponseEntity<ExceptionBody> {
        return createResponseEntity(request, ex.message, HttpStatus.NOT_FOUND)

    }

    @ExceptionHandler(ValidationException::class)
    fun handleValidationExceptionException(ex: ValidationException, request: WebRequest): ResponseEntity<ExceptionBody> {
        return createResponseEntity(request, ex.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException, request: WebRequest): ResponseEntity<ExceptionBody> {
        val message = ex.bindingResult.fieldErrors
                .joinToString { fieldError -> "${fieldError.objectName}.${fieldError.field}: ${fieldError.defaultMessage}" }
        return createResponseEntity(request, message, HttpStatus.BAD_REQUEST)
    }

    private fun createResponseEntity(
        request: WebRequest,
        message: String?,
        status: HttpStatus
    ): ResponseEntity<ExceptionBody> {
        val uri = (request as ServletWebRequest).request.requestURI
        return ResponseEntity.status(status)
            .body(ExceptionBody(Instant.now(), status, message, uri))
    }

}

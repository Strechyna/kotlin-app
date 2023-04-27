package com.task.pizzatoppings.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class CustomerInfo(
    @field:Email
    @field:NotEmpty
    val email: String
)

package com.task.pizzatoppings.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class ChosenToppings(
    @field:Email
    @field:NotEmpty
    val email: String,
    val toppings: List<String>
)

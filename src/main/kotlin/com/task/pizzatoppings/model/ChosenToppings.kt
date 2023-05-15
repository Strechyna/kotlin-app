package com.task.pizzatoppings.model

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class ChosenToppings(
    @field:Email
    @field:NotEmpty
    val email: String,
    @field:NotEmpty
    val toppings: List<String>
)

package com.task.pizzatoppings.dto

import jakarta.validation.Valid

data class ChosenToppings(
    @field:Valid
    val customer: CustomerInfo,
    val toppings: List<String>
)

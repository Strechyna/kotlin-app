package com.task.pizzatoppings.dto

data class ChosenToppings(
    val customer: CustomerInfo,
    val toppings: List<String>
)

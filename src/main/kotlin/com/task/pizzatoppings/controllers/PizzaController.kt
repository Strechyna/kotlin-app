package com.task.pizzatoppings.controllers

import com.task.pizzatoppings.dto.ChosenToppings
import com.task.pizzatoppings.dto.ToppingInfo
import com.task.pizzatoppings.services.PizzaService
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
@RequestMapping("/toppings")
class PizzaController(private val service: PizzaService) {

    @PostMapping("/")
    fun submitToppings(@RequestBody @Valid requestBody: ChosenToppings) {
        service.submitToppings(requestBody)
    }

    @GetMapping("/")
    fun getToppings(): List<ToppingInfo> =
        service.getAllToppings()

    @GetMapping("/choice")
    fun getToppingsByCustomer(@RequestParam("email") @Valid @Email @NotEmpty email: String): List<String> =
        service.getToppingsByCustomer(email)
}

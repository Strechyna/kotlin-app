package com.task.pizzatoppings.controllers

import com.task.pizzatoppings.dto.ChosenToppings
import com.task.pizzatoppings.dto.CustomerInfo
import com.task.pizzatoppings.dto.Toppings
import com.task.pizzatoppings.services.PizzaService
import jakarta.validation.Valid
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
@RequestMapping("/toppings")
class PizzaController(val service: PizzaService) {

    @PostMapping("/")
    fun submitToppings(@Valid @RequestBody requestBody: ChosenToppings) {
        service.submitToppings(requestBody)
    }

    @GetMapping("/")
    fun getToppings(): Toppings {
        return service.getAllToppings()
    }

    @PostMapping("/choice")
    fun getToppingsByCustomer(@Valid @RequestBody requestBody: CustomerInfo): ChosenToppings {
        return service.getToppingsByCustomer(requestBody.email)
    }
}

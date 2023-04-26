package com.task.pizzatoppings.controllers

import com.task.pizzatoppings.dto.ChosenToppings
import com.task.pizzatoppings.dto.Toppings
import com.task.pizzatoppings.services.PizzaService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/toppings")
class PizzaController(val service: PizzaService) {

    @PostMapping("/")
    fun submitToppings(@RequestBody requestBody: ChosenToppings) {
        service.submitToppings(requestBody)
    }

    @GetMapping("/")
    fun getToppings(): Toppings {
        return service.getAllToppings();
    }

    @GetMapping("/choice")
    fun getToppingsByCustomer(@RequestParam("email") email: String): ChosenToppings? {
        return service.getToppingsByCustomer(email)
    }
}

package com.task.pizzatoppings.services

import com.task.pizzatoppings.dto.ChosenToppings
import com.task.pizzatoppings.dto.CustomerInfo
import com.task.pizzatoppings.dto.ToppingInfo
import com.task.pizzatoppings.dto.Toppings
import com.task.pizzatoppings.repositories.CustomerRepository
import com.task.pizzatoppings.repositories.ToppingRepository
import com.task.pizzatoppings.repositories.model.Customer
import com.task.pizzatoppings.repositories.model.Topping
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class PizzaService(
    val toppingRepository: ToppingRepository,
    val customerRepository: CustomerRepository
) {
    @Transactional
    fun submitToppings(chosenToppings: ChosenToppings) {
        val email = chosenToppings.customer.email

        val customer = Customer(email)

        customer.toppings.forEach(customer::removeTopping)
        chosenToppings.toppings.forEach { topping -> customer.addTopping(Topping(topping)) }
        customerRepository.save(customer)
    }

    fun getAllToppings(): Toppings {
        return Toppings(toppingRepository.getToppingCounts()
            .map { toppingCount -> ToppingInfo(toppingCount.getName(), toppingCount.getCountResult()) })
    }

    fun getToppingsByCustomer(email: String): ChosenToppings? {
        return customerRepository.findById(email).map { customer ->
            ChosenToppings(CustomerInfo(customer.email),
                customer.toppings.map { topping -> topping.name })
        }.orElse(null)
    }
}

package com.task.pizzatoppings.services

import com.task.pizzatoppings.exceptions.ToppingsNotFoundException
import com.task.pizzatoppings.dto.ChosenToppings
import com.task.pizzatoppings.dto.ToppingInfo
import com.task.pizzatoppings.repositories.CustomerRepository
import com.task.pizzatoppings.repositories.ToppingRepository
import com.task.pizzatoppings.model.Customer
import com.task.pizzatoppings.model.Topping
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class PizzaService(
    private val toppingRepository: ToppingRepository,
    private val customerRepository: CustomerRepository
) {
    fun submitToppings(chosenToppings: ChosenToppings) {
        val email = chosenToppings.email

        val customer = Customer(email)

        customer.toppings.forEach(customer::removeTopping)
        chosenToppings.toppings.forEach { topping -> customer.addTopping(Topping(topping)) }
        customerRepository.save(customer)
    }

    fun getAllToppings(): List<ToppingInfo> =
        getListResult { toppingRepository.getToppingCounts() }

    fun getToppingsByCustomer(email: String): List<String> =
        getListResult { toppingRepository.getToppingsByEmail(email) }

    private fun<T> getListResult(find: () -> List<T>): List<T> {
        val result: List<T> = find()
        checkListResult(result)
        return result
    }

    private fun<T> checkListResult(result: List<T>) {
        if (result.isEmpty()) {
            throw ToppingsNotFoundException()
        }
    }
}

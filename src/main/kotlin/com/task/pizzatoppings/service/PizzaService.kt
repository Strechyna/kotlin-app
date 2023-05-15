package com.task.pizzatoppings.service

import com.task.pizzatoppings.exception.ToppingsNotFoundException
import com.task.pizzatoppings.model.ChosenToppings
import com.task.pizzatoppings.model.ToppingInfo
import com.task.pizzatoppings.repository.CustomerRepository
import com.task.pizzatoppings.repository.ToppingRepository
import com.task.pizzatoppings.domain.Customer
import com.task.pizzatoppings.domain.Topping
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class PizzaService(
    private val toppingRepository: ToppingRepository,
    private val customerRepository: CustomerRepository
) {

    @Transactional
    fun submitToppings(chosenToppings: ChosenToppings) {
        val email = chosenToppings.email

        val customer = customerRepository.findByEmail(email)
            .orElseGet { Customer(null, email) }

        customer.toppings.clear()
        val toppingsMap = toppingRepository.findAllByNameIn(chosenToppings.toppings)
            .associateBy({ it.name }, { it })
        customer.toppings = chosenToppings.toppings
            .map{toppingName -> toppingsMap.getOrElse(toppingName) { Topping(null, toppingName) } }
            .toHashSet()
        customerRepository.save(customer)
    }

    fun getToppingStatistics(): Collection<ToppingInfo> {
        val toppingInfoList: Collection<ToppingInfo> = toppingRepository.getToppingCounts()
        if (toppingInfoList.isEmpty()) {
            throw ToppingsNotFoundException("Not found any toppings")
        }
        return toppingInfoList
    }

    fun getToppingsByCustomer(email: String): Collection<String> {
        val customer: Customer = customerRepository.findByEmail(email)
            .orElseThrow{ToppingsNotFoundException("Toppings for $email not found")}

        return customer.toppings.map(Topping::name)
    }
}

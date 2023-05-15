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

        customer.removeAllToppings()
        val toppingsMap = toppingRepository.findAllByNameIn(chosenToppings.toppings)
            .associateBy({ it.name }, { it })
        chosenToppings.toppings
            .map{toppingName -> toppingsMap.getOrElse(toppingName) { Topping(null, toppingName) } }
            .forEach { topping -> customer.addTopping(topping)}
        customerRepository.save(customer)
    }

    fun getToppingStatistics(): Collection<ToppingInfo> =
        getCollectionResult({ toppingRepository.getToppingCounts() },
            { throw ToppingsNotFoundException("Not found any toppings") })

    fun getToppingsByCustomer(email: String): Collection<String> =
        getCollectionResult({
            toppingRepository.findAllByCustomersEmail(email)
                .map(Topping::name)
        }, { throw ToppingsNotFoundException("Toppings for $email not found") })

    private fun<T> getCollectionResult(find: () -> Collection<T>, throwException: () -> Void): Collection<T> {
        val result: Collection<T> = find()
        checkListResult(result, throwException)
        return result
    }

    private fun<T> checkListResult(result: Collection<T>, throwException: () -> Void) {
        if (result.isEmpty()) {
            throwException()
        }
    }
}

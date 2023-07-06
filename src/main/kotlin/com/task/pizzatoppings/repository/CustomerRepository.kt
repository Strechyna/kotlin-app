package com.task.pizzatoppings.repository

import com.task.pizzatoppings.domain.Customer
import org.springframework.data.repository.CrudRepository
import java.util.*

interface  CustomerRepository : CrudRepository<Customer, Long> {

    fun findByEmail(email: String): Optional<Customer>
}

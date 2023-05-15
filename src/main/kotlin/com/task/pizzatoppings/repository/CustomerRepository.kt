package com.task.pizzatoppings.repository

import com.task.pizzatoppings.domain.Customer
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface  CustomerRepository : JpaRepository<Customer, Long> {

    fun findByEmail(email: String): Optional<Customer>
}

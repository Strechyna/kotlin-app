package com.task.pizzatoppings.repositories

import com.task.pizzatoppings.repositories.model.Customer
import org.springframework.data.repository.CrudRepository

interface  CustomerRepository : CrudRepository<Customer, String> {
}

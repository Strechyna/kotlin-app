package com.task.pizzatoppings.repositories

import com.task.pizzatoppings.dto.ToppingInfo
import com.task.pizzatoppings.model.Topping
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface ToppingRepository : CrudRepository<Topping, String> {

    @Query("SELECT topping_name AS name, COUNT(topping_name) AS numberOfRequests FROM customer_topping GROUP BY topping_name",
        nativeQuery = true)
    fun getToppingCounts(): List<ToppingInfo>

    @Query("SELECT topping_name FROM customer_topping WHERE customer_email = :email",
        nativeQuery = true)
    fun getToppingsByEmail(email: String): List<String>
}

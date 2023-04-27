package com.task.pizzatoppings.repositories

import com.task.pizzatoppings.repositories.model.Topping
import com.task.pizzatoppings.repositories.model.ToppingCount
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface ToppingRepository : CrudRepository<Topping, String> {

    @Query("SELECT topping_name AS name, COUNT(topping_name) AS countResult FROM customer_topping GROUP BY topping_name",
        nativeQuery = true)
    fun getToppingCounts(): List<ToppingCount>
}

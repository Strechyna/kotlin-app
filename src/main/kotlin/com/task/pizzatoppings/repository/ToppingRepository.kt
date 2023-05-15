package com.task.pizzatoppings.repository

import com.task.pizzatoppings.model.ToppingInfo
import com.task.pizzatoppings.domain.Topping
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ToppingRepository : JpaRepository<Topping, Long> {

    @Query("""SELECT t.name AS name,
                     COUNT(c.id) AS numberOfRequests
              FROM Topping AS t
                   JOIN Customer AS c
              GROUP BY t.name""")
    fun getToppingCounts(): Collection<ToppingInfo>

    fun findAllByCustomersEmail(email: String): Collection<Topping>

    fun findAllByNameIn(name: Collection<String>): Collection<Topping>
}

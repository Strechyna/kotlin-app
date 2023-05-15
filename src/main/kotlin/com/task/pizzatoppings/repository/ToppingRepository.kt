package com.task.pizzatoppings.repository

import com.task.pizzatoppings.model.ToppingInfo
import com.task.pizzatoppings.domain.Topping
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ToppingRepository : JpaRepository<Topping, Long> {

    @Query("""SELECT t.name AS name,
                     COUNT(t.name) AS numberOfRequests
              FROM Customer AS c
                   JOIN c.toppings AS t
              GROUP BY t.name""")
    fun getToppingCounts(): Collection<ToppingInfo>

    fun findAllByNameIn(name: Collection<String>): Collection<Topping>
}

package com.task.pizzatoppings.services

import com.task.pizzatoppings.controllers.ToppingsNotFoundException
import com.task.pizzatoppings.repositories.CustomerRepository
import com.task.pizzatoppings.repositories.ToppingRepository
import com.task.pizzatoppings.repositories.model.ToppingCount
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PizzaServiceTest {

    private val toppingRepository: ToppingRepository = mockk<ToppingRepository>()
    private val customerRepository: CustomerRepository = mockk<CustomerRepository>()
    private val service = PizzaService(toppingRepository, customerRepository)

    @Test
    fun whenGetToppingCounts_thenReturnToppings() {
        every { toppingRepository.getToppingCounts() } returns listOf(
            getToppingCount("topping1", 3),
            getToppingCount("topping2", 4)
        )

        val result = service.getAllToppings()

        verify(exactly = 1) { toppingRepository.getToppingCounts() }
        assertNotNull(result)
        assertFalse(result.toppings.isEmpty())
        assertEquals(2, result.toppings.size)
    }

    @Test
    fun whenGetToppingCounts_thenThrowToppingsNotFoundException() {
        every { toppingRepository.getToppingCounts() } returns listOf()

        val thrown: ToppingsNotFoundException = assertThrows(ToppingsNotFoundException::class.java) {
            service.getAllToppings()
        }

        assertEquals("Not found any toppings", thrown.message)
    }

    private fun getToppingCount(name: String, count: Int): ToppingCount {
        return object : ToppingCount {
            override fun getName() = name
            override fun getCountResult() = count
        }
    }

}

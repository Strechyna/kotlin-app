package com.task.pizzatoppings.service

import com.task.pizzatoppings.model.ToppingInfo
import com.task.pizzatoppings.exception.ToppingsNotFoundException
import com.task.pizzatoppings.repository.CustomerRepository
import com.task.pizzatoppings.repository.ToppingRepository
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
            getToppingInfo("topping1", 3),
            getToppingInfo("topping2", 4)
        )

        val result = service.getToppingStatistics()

        verify(exactly = 1) { toppingRepository.getToppingCounts() }
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertEquals(2, result.size)
    }

    @Test
    fun whenGetToppingCounts_thenThrowToppingsNotFoundException() {
        every { toppingRepository.getToppingCounts() } returns listOf()

        val thrown: ToppingsNotFoundException = assertThrows(ToppingsNotFoundException::class.java) {
            service.getToppingStatistics()
        }

        assertEquals("Not found any toppings", thrown.message)
    }

    private fun getToppingInfo(name: String, count: Int): ToppingInfo {
        return object : ToppingInfo {
            override val name: String
                get() = name
            override val numberOfRequests: Int
                get() = count
        }
    }

}

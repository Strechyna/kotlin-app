package com.task.pizzatoppings.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class Topping (
    @Id
    @GeneratedValue
    val id: Long?,

    @Column(nullable = false, unique = true, )
    val name: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Topping

        return name == other.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}

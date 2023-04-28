package com.task.pizzatoppings.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany

@Entity
class Topping (
    @Id
    val name: String,

    @ManyToMany(mappedBy = "toppings")
    val customers: MutableList<Customer> = arrayListOf()
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

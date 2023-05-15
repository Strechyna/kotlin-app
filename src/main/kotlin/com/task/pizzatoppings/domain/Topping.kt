package com.task.pizzatoppings.domain

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import org.hibernate.annotations.NaturalId

@Entity
class Topping (
    @Id
    @GeneratedValue
    val id: Long?,

    @NaturalId
    val name: String,

    @ManyToMany(mappedBy = "toppings", fetch = FetchType.EAGER)
    val customers: MutableSet<Customer> = hashSetOf()
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

package com.task.pizzatoppings.domain

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import org.hibernate.annotations.NaturalId

@Entity
class Customer (
    @Id
    @GeneratedValue
    val id: Long?,

    @NaturalId
    val email: String,

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE], fetch = FetchType.EAGER)
    @JoinTable(name = "customer_topping",
        joinColumns = [JoinColumn(name = "customer_id")],
        inverseJoinColumns = [JoinColumn(name = "topping_id")])
    var toppings: MutableSet<Topping> = hashSetOf()
) {

    fun addTopping(topping: Topping) {
        toppings.add(topping)
        topping.customers.add(this)
    }

    fun removeTopping(topping: Topping) {
        toppings.remove(topping)
        topping.customers.remove(this)
    }

    fun removeAllToppings() {
        toppings.forEach{topping -> topping.customers.remove(this)}
        toppings.clear()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Customer

        return email == other.email
    }

    override fun hashCode(): Int {
        return email.hashCode()
    }

}

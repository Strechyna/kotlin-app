package com.task.pizzatoppings.repositories.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany

@Entity
class Customer (
    @Id
    val email: String,

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE], fetch = FetchType.EAGER)
    @JoinTable(name = "customer_topping",
        joinColumns = [JoinColumn(name = "customer_email", referencedColumnName = "email")],
        inverseJoinColumns = [JoinColumn(name = "topping_name", referencedColumnName = "name")])
    var toppings: MutableList<Topping> = arrayListOf()
) {

    fun addTopping(topping: Topping) {
        toppings.add(topping)
        topping.customers.add(this)
    }

    fun removeTopping(topping: Topping) {
        toppings.remove(topping)
        topping.customers.remove(this)
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

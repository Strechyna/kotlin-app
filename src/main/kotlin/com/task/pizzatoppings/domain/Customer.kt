package com.task.pizzatoppings.domain

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany

@Entity
class Customer (
    @Id
    @GeneratedValue
    val id: Long?,

    @Column(nullable = false, unique = true)
    val email: String,

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(name = "customer_topping",
        joinColumns = [JoinColumn(name = "customer_id")],
        inverseJoinColumns = [JoinColumn(name = "topping_id")])
    var toppings: MutableSet<Topping> = hashSetOf()
) {

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

package com.tul.demo.model

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Version
import javax.validation.constraints.NotBlank

@Entity
class Item(
    @Id @Column(name = "id", length = 16, unique = true, nullable = false)
    val id: UUID = UUID.randomUUID(),
    @Version
    val version: Long? = null,
    @field:NotBlank
    val name: String,
    @field:NotBlank
    val sku: String,
    val description: String?= null,
    internal val price: Double,
    var amount: Int,
    val itemWithDiscount: Boolean,
){
    override fun equals(other: Any?) = when {
        this === other -> true
        javaClass != other?.javaClass -> false
        id != (other as Item).id -> false
        else -> true
    }

    override fun hashCode(): Int = id.hashCode()
}
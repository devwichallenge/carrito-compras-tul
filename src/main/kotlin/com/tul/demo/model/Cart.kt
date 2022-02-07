package com.tul.demo.model

import java.util.*
import javax.persistence.*

@Entity
class Cart(
    @Id @Column(name = "id", length = 16, unique = true, nullable = false)
    val id: UUID = UUID.randomUUID(),
    @Version
    val version: Long? = null,
    @OneToMany var items: MutableList<Item>,
    var state: State = State.PENDING
)
{
    override fun equals(other: Any?) = when {
        this === other -> true
        javaClass != other?.javaClass -> false
        id != (other as Cart).id -> false
        else -> true
    }

    override fun hashCode(): Int = id.hashCode()
}
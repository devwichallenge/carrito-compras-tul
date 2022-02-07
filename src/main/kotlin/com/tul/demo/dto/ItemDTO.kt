package com.tul.demo.dto

import java.util.*

class ItemDTO(
    id: UUID? = null,
    val name: String,
    val sku: String,
    val description: String?= null,
    val price: Int? = null,
    val amount: Int?= null,
    val discount: Boolean
)

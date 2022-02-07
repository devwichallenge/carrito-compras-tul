package com.tul.demo.service

import com.tul.demo.model.Item
import java.util.*

data class CartRequest(
    val idCart: UUID?,
    val item: Item
)
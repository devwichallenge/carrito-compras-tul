package com.tul.demo

import com.tul.demo.model.Item
import java.util.*

open class TestExtensions{

    private fun anyString() = UUID.randomUUID().toString()

    fun anyUUID(): UUID = UUID.randomUUID()

    fun anyItem(id: UUID) = Item(
        id = id,
        name = anyString(),
        sku = anyString(),
        description = anyString(),
        price = 100.0,
        amount = 100,
        itemWithDiscount = false)
}

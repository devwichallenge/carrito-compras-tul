package com.tul.demo.service

import com.tul.demo.model.Item
import java.util.*

interface ItemService {

    fun getItems(): List<Item>

    fun createItem(item: Item): Item

    fun updateItem(id: UUID, item: Item): Item

    fun deleteItem(id: UUID)
}
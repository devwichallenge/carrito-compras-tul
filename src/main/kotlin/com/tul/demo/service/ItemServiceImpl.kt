package com.tul.demo.service

import com.tul.demo.model.Item
import com.tul.demo.repository.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ItemServiceImpl(@Autowired private val itemRepository: ItemRepository) : ItemService {

    override fun getItems(): List<Item> = itemRepository.findAll()

    override fun createItem(item: Item): Item {
        return itemRepository.save(item)
    }

    override fun updateItem(id: UUID, item: Item): Item {
        val update = Item(id= id, name = item.name, sku = item.sku, description = item.description,
            price = item.price, amount = item.amount, itemWithDiscount = item.itemWithDiscount)
        return itemRepository.save(update)
    }

    override fun deleteItem(id: UUID): Unit = itemRepository.deleteById(id)

}
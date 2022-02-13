package com.tul.demo.service

import com.tul.demo.model.Item
import org.springframework.stereotype.Component

@Component
class ItemDiscountImpl: ItemDiscount {

    companion object {
        var divider: Int = 2
    }

    override fun totalWithDiscount(item: List<Item>) =
        item.filter { it.itemWithDiscount }
            .sumOf { itemWithDiscount ->
                (itemWithDiscount.price.times(itemWithDiscount.amount)).div(divider)
            }

    override fun totalWithOutDiscount(item: List<Item>) =
        item.filterNot { it.itemWithDiscount }
            .sumOf { itemWithOutDiscount -> itemWithOutDiscount.price.times(itemWithOutDiscount.amount) }


}
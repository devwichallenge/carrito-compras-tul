package com.tul.demo.service

import com.tul.demo.model.Cart
import com.tul.demo.model.Item
import java.util.*

interface CartService {

    fun itemsByCart(id: UUID): MutableList<Item>

    fun addItemInCart(cartRequest: CartRequest): Cart

    fun updateItemInCart(id: UUID, item: Item): Cart

    fun deleteItemInCart(id: UUID, itemId: UUID): Cart

    fun checkoutPayment(id: UUID): VerifyPaymentResponse
}
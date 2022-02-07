package com.tul.demo.service

import com.tul.demo.TestExtensions
import com.tul.demo.model.Cart
import com.tul.demo.model.Item
import com.tul.demo.repository.CartRepository
import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*


class CartServiceImplTest : TestExtensions() {

    companion object{
        private val staticUuId: UUID = UUID.fromString("bcc68d33-4f69-4f06-88fd-0bc6dcba048f")
        private val otherUUID: UUID = UUID.fromString("be7e9a6c-9b80-42c2-bbc4-51411f13a03e")
    }

    private val repository: CartRepository = Mockito.mock(CartRepository::class.java)

    private lateinit var sut: CartServiceImpl //system under test

    @BeforeEach
    fun initUseCase(){
        sut = CartServiceImpl(repository)
        // init values for use on the tests context
        val anyItem = Item(id = otherUUID ,name = "name01", sku = "sku", itemWithDiscount = false, price = 100.0, amount = 1)
        val anyItem2 = Item(id = staticUuId, name = "name02", sku = "sku2", itemWithDiscount = true, price = 100.1, amount = 1)
        val listOfItems = mutableListOf(anyItem, anyItem2)
        val initCart = Cart(items = listOfItems)
        Mockito.`when`(repository.findById(staticUuId)).thenReturn(Optional.of(initCart))
        Mockito.`when`(repository.save(initCart)).thenReturn(initCart)
    }

    @Test
    fun `can get items  by cart`(){
        val listItem = mutableListOf(anyItem(anyUUID()), anyItem(anyUUID()))
        sut.itemsByCart(staticUuId).also { actualItems ->
            assertEquals(listItem.size, actualItems.size)
        }
    }

    @Test
    fun `can add items to a cart`(){
        val anyItem = Item(id = otherUUID ,name = "name01", sku = "sku", itemWithDiscount = false, price = 100.0, amount = 1)
        val initRequest = CartRequest(staticUuId, anyItem)
        sut.addItemInCart(cartRequest = initRequest).also {
            assertEquals(initRequest.item.name, it.items.firstOrNull()!!.name)
            assertEquals(initRequest.item.sku, it.items.firstOrNull()!!.sku)
            assertEquals(3, it.items.size)
        }
    }

    @Test
    fun `can update every item into cart`(){
        val expectedItem = Item(id= otherUUID, name = "name01", sku = "sku", description = "description01", price = 35.0, amount = 3,
            itemWithDiscount = false)
        sut.updateItemInCart(staticUuId, expectedItem).items.first().also { itemActual ->
            assertEquals(expectedItem.amount, itemActual.amount)
        }
    }

    @Test
    fun `can delete product in cart list`(){
        sut.deleteItemInCart(staticUuId, otherUUID).also {
            assertEquals(1, it.items.size)
        }
    }

    @Test
    fun `can verify checkout final payment`(){
        sut.checkoutPayment(staticUuId).also {
            assertEquals(150.05, it.finalAmount)
        }
    }


}
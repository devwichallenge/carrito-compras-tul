package com.tul.demo.service

import com.tul.demo.model.Cart
import com.tul.demo.model.Item
import com.tul.demo.model.State
import com.tul.demo.repository.CartRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CartServiceImpl(@Autowired val cartRepository: CartRepository): CartService {

    companion object{
        var divider: Int = 2
    }

    override fun itemsByCart(id: UUID): MutableList<Item> = cartRepository.findById(id).map(Cart::items).get()

    override fun addItemInCart(cartRequest: CartRequest): Cart {
        var cart = Cart(items = mutableListOf()) // init empty
        cartRequest.idCart?.also { uuIdIsNotEmpty ->
            cart =  cartRepository.findById(uuIdIsNotEmpty).get() //imperative programming
        }
        cart.items.add(cartRequest.item)
        return cartRepository.save(cart)
    }


    override fun updateItemInCart(id: UUID, item: Item): Cart {
        val currentCart = cartRepository.findById(id).get()
        currentCart.items.map { cartItem ->
            if(cartItem.id == item.id)
                cartItem.amount = item.amount // el monto puede variar en cualquiera de los items

        }
        return cartRepository.save(currentCart)
    }

    override fun deleteItemInCart(id: UUID, itemId: UUID): Cart {
        cartRepository.findById(id).get().also {
            it.items = it.items.filterNot { item -> item.id == itemId }.toMutableList() // modify the list
        }.let {
            return cartRepository.save(it)
        }
    }

    /*esto deberia estar definido en una interfaz aparte donde pueda manejar los tipos de descuento en una implementacion
    especifica. Asi tengo implementaciones concretas por tipo de descuento ejemplo: Descuento por cupon
    descuento por empleado etc...
     */

    override fun checkoutPayment(id: UUID): VerifyPaymentResponse {
        with(cartRepository.findById(id).get()){
            val finalAmountOperation =
                this.items.filter { it.itemWithDiscount }
                    .sumOf { itemWithDiscount -> (itemWithDiscount.price.times(itemWithDiscount.amount)).div(divider)}
                .plus(
                    this.items.filterNot{it.itemWithDiscount}
                        .sumOf { itemWithOutDiscount -> itemWithOutDiscount.price.times(itemWithOutDiscount.amount)})
            this.state = State.COMPLETED
            cartRepository.save(this)
            return  VerifyPaymentResponse(finalAmountOperation)
        }
    }
}

class VerifyPaymentResponse(val finalAmount: Double)
package com.tul.demo.controller

import com.tul.demo.model.Cart
import com.tul.demo.model.Item
import com.tul.demo.service.CartRequest
import com.tul.demo.service.CartService
import com.tul.demo.service.VerifyPaymentResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/shopping-carts", produces = [MediaType.APPLICATION_JSON_VALUE])
class CartController(@Autowired private val cartService: CartService) {

    companion object{
        private val log = LoggerFactory.getLogger(CartController::class.java)
    }

    @GetMapping("/{id}")
    fun itemsByCart(
        @PathVariable id: UUID
    ): MutableList<Item> = cartService.itemsByCart(id)

    @PostMapping
    fun addItemToCart(
        @Validated @RequestBody cartRequest: CartRequest
    ): Cart = cartService.addItemInCart(cartRequest).also {
        log.info("this cart was updated ${it.id}")
    }


    @PutMapping("/{id}")
    fun updateItemInCart(
        @PathVariable id: UUID,
        @Valid @RequestBody item: Item
    ): Cart = cartService.updateItemInCart(id, item)

    @DeleteMapping("/{id}/{idItem}")
    fun deleteItemInCart(
        @PathVariable id: UUID,
        @PathVariable idItem: UUID
    ): Cart = cartService.deleteItemInCart(id, idItem)

    @GetMapping("/{id}/checkout")
    fun checkout(
        @PathVariable id: UUID
    ): VerifyPaymentResponse = cartService.checkoutPayment(id)

}
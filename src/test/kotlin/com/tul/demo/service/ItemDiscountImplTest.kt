package com.tul.demo.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ItemDiscountImplTest: CartServiceImplTest() {

    private lateinit var sut: ItemDiscountImpl

    @BeforeEach // cannot should be repeat this function for every Impl
    fun initUseCase2(){
        sut = ItemDiscountImpl()
    }

    @Test
    fun `can test items with discount`(){
        val expectedAmount = 50.05
        sut.totalWithDiscount(listOfItems).also {
            assertEquals(expectedAmount, it)
        }
    }

    @Test
    fun `can test items without discount`(){
        val expectedAmount = 100.0
        sut.totalWithOutDiscount(listOfItems).also {
            assertEquals(expectedAmount, it)
        }
    }
}
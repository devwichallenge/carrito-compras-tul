package com.tul.demo.service

import com.tul.demo.model.Item

interface ItemDiscount {

    fun totalWithDiscount(item: List<Item>): Double

    fun totalWithOutDiscount(item: List<Item>): Double
}
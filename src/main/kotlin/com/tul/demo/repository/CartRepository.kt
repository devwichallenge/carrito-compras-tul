package com.tul.demo.repository

import com.tul.demo.model.Cart
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CartRepository: JpaRepository<Cart, UUID>
package com.tul.demo.repository

import com.tul.demo.model.Item
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ItemRepository: JpaRepository<Item, UUID>
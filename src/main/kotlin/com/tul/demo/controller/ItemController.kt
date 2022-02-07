package com.tul.demo.controller

import com.tul.demo.model.Item
import com.tul.demo.service.ItemService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/shopping", produces = [MediaType.APPLICATION_JSON_VALUE])
class ItemController(@Autowired private val service: ItemService) {

    companion object{
        private val log = LoggerFactory.getLogger(ItemController::class.java)
    }

    @GetMapping
    fun items(): List<Item> = service.getItems()

    @PostMapping
    fun createItem(@RequestBody @Valid item: Item): Item = service.createItem(item).also {
        log.info("Item was created $it")
    }

    @PutMapping("/{id}")
    fun updateItem(
        @PathVariable id: UUID,
        @RequestBody @Valid item: Item
    ): Item = service.updateItem(id, item).also {
        log.info("Item was updated $it")
    }

    @DeleteMapping("/{id}")
    fun deleteItem(
        @PathVariable id: UUID
    ): Unit = service.deleteItem(id)

}
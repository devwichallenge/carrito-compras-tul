package com.tul.demo.service

import com.tul.demo.TestExtensions
import com.tul.demo.model.Item
import com.tul.demo.repository.ItemRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class ItemServiceImplTest : TestExtensions() {

    private val repository: ItemRepository = Mockito.mock(ItemRepository::class.java)

    private lateinit var sut: ItemServiceImpl // System under test

    companion object{
        val staticUUID: UUID = UUID.fromString("be7e9a6c-9b80-42c2-bbc4-51411f13a03e")
    }

    @BeforeEach
    fun initUseCase(){
        sut = ItemServiceImpl(repository)
        savedItem()
    }

    @Test
    fun `can save a item`(){
        val anyExpectedItem = savedItem()
        sut.createItem(anyExpectedItem).also {
            Assertions.assertEquals(anyExpectedItem, it)
        }
    }

    @Test
    fun `can list all items`(){
        val anyItemList = listOf(anyItem(anyUUID()), anyItem(anyUUID()))
        Mockito.`when`(repository.findAll()).thenReturn(anyItemList)
        assertThat(sut.getItems().size).isGreaterThanOrEqualTo(2);
    }

    @Test
    fun `can update a item`(){
        val item = anyItem(staticUUID)
        sut.updateItem(staticUUID, item).also {
            Assertions.assertEquals(item, it)
        }
    }

    private fun savedItem(): Item {
        val item = anyItem(staticUUID)
        Mockito.`when`(repository.save(item)).thenReturn(item)
        return  item
    }
}
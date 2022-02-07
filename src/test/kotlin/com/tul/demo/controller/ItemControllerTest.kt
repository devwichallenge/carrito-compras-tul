package com.tul.demo.controller

import com.tul.demo.TestExtensions
import com.tul.demo.model.Item
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ItemControllerTest(@Autowired val restTmplClientSide: TestRestTemplate) : TestExtensions() {

    //Este test solo prueba el lado del cliente
    //TODO Implementar test con MockMvc para probar la aplicacion del lado del servidor

    companion object{
        val httpStatusOk = HttpStatus.OK
    }

    @Test
    fun `can get list of items ok`(){
        val itemEntity = restTmplClientSide.getForEntity<List<Item>>("/shopping")
        Assertions.assertThat(itemEntity.statusCode).isEqualTo(httpStatusOk)
    }

    @Test
    fun `can create a item ok`(){
        val item = anyItem(anyUUID())
        val itemEntity = restTmplClientSide.postForEntity("/shopping", item, Item::class.java)
        Assertions.assertThat(itemEntity.statusCode).isEqualTo(httpStatusOk)
    }

    @Test
    fun `can update a item ok`(){
        val uuid = anyUUID()
        val item = anyItem(uuid)
        restTmplClientSide.put("/shopping", item, uuid)
    }

    @Test
    fun `can delete item ok`(){
        val uuid = anyUUID()
        restTmplClientSide.delete("/shopping", uuid)
    }
}
package com.tul.demo.advice

import com.tul.demo.exception.GeneralTulException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GeneralControllerAdvice {

    @ExceptionHandler(GeneralTulException::class)
    fun generalTulException(e: GeneralTulException): ResponseEntity<*> {
        val response = mapOf("Message" to e.message)
        return ResponseEntity.badRequest().body(response)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<*> {
        val response = mapOf("Mensaje del admin: " to getFieldError(e))
        return ResponseEntity.badRequest().body(response)
    }

    private fun getFieldError(e: MethodArgumentNotValidException): String {
        val fieldError = e.bindingResult.fieldErrors.stream().findFirst().get()
        return "[" + fieldError.field + "] " + fieldError.defaultMessage
    }
}


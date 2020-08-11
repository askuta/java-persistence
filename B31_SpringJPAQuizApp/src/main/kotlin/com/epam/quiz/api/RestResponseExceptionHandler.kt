package com.epam.quiz.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.validation.ConstraintViolationException

@ControllerAdvice(basePackages = ["com.epam.quiz.api"])
class RestResponseExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [(NoSuchElementException::class)])
    fun handleIdDoesNotExist(exception: NoSuchElementException): ResponseEntity<String> =
            ResponseEntity(exception.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(value = [(ConstraintViolationException::class), (IllegalArgumentException::class)])
    fun handleBadRequest(exception: ConstraintViolationException): ResponseEntity<String> =
            ResponseEntity(exception.message, HttpStatus.BAD_REQUEST)

    @ExceptionHandler(value = [(Exception::class)])
    fun handleExceptions(exception: Exception): ResponseEntity<String> =
            ResponseEntity("Something terrible happened: ${exception.message}", HttpStatus.INTERNAL_SERVER_ERROR)
}

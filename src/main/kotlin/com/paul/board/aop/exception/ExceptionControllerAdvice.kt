package com.paul.board.aop.exception

import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {
    companion object : KLogging()

    @ExceptionHandler
    fun handleIllegalStateException(ex: IllegalStateException): ResponseEntity<ExceptionMessageModel> {
        logger.error("=========> IllegalStateException(): " + ex.message)
        val errorMessage = ExceptionMessageModel(
            HttpStatus.NOT_FOUND.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleIllegalArgumentException(ex: java.lang.IllegalArgumentException): ResponseEntity<ExceptionMessageModel> {
        logger.error("=========> IllegalArgumentException(): " + ex.message)
        val errorMessage = ExceptionMessageModel(
            HttpStatus.BAD_REQUEST.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }
}
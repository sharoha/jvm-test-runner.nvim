package com.example.demo.support

import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiExceptionHandler {
	@ExceptionHandler(NotFoundException::class)
	fun handleNotFound(exception: NotFoundException): ProblemDetail =
		ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.message ?: "Resource not found")

	@ExceptionHandler(ConflictException::class)
	fun handleConflict(exception: ConflictException): ProblemDetail =
		ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, exception.message ?: "Request could not be completed")

	@ExceptionHandler(MethodArgumentNotValidException::class)
	fun handleValidation(exception: MethodArgumentNotValidException): ProblemDetail {
		val detail = exception.bindingResult.fieldErrors.joinToString("; ") { error ->
			"${error.field}: ${error.defaultMessage}"
		}
		return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, detail)
	}
}

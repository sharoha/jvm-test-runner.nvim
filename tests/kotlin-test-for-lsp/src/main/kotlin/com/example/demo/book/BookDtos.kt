package com.example.demo.book

import jakarta.validation.constraints.NotBlank

data class CreateBookRequest(
	@get:NotBlank
	val title: String,
	@get:NotBlank
	val author: String,
	@get:NotBlank
	val isbn: String,
)

data class BorrowBookRequest(
	@get:NotBlank
	val borrower: String,
)

data class BookResponse(
	val id: Long,
	val title: String,
	val author: String,
	val isbn: String,
	val status: BookStatus,
	val borrowedBy: String?,
)

fun Book.toResponse(): BookResponse = BookResponse(
	id = requireNotNull(id),
	title = title,
	author = author,
	isbn = isbn,
	status = status,
	borrowedBy = borrowedBy,
)

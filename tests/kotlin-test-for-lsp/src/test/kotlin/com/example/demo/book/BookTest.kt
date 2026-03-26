package com.example.demo.book

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class BookTest {
	@Test
	fun `borrow updates availability`() {
		val book = Book(title = "Clean Code", author = "Robert C. Martin", isbn = "9780132350884")

		book.borrow("Ava")

		assertEquals(BookStatus.BORROWED, book.status)
		assertEquals("Ava", book.borrowedBy)
	}

	@Test
	fun `return resets borrower`() {
		val book = Book(
			title = "Refactoring",
			author = "Martin Fowler",
			isbn = "9780201485677",
			status = BookStatus.BORROWED,
			borrowedBy = "Kai",
		)

		book.markReturned()

		assertEquals(BookStatus.AVAILABLE, book.status)
		assertEquals(null, book.borrowedBy)
	}

	@Test
	fun `borrow fails when unavailable`() {
		val book = Book(
			title = "Domain-Driven Design",
			author = "Eric Evans",
			isbn = "9780321125217",
			status = BookStatus.BORROWED,
			borrowedBy = "Noah",
		)

		assertFailsWith<IllegalStateException> {
			book.borrow("Mia")
		}
	}
}

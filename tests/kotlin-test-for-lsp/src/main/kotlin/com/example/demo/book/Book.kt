package com.example.demo.book

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "books")
class Book(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null,
	@Column(nullable = false)
	var title: String,
	@Column(nullable = false)
	var author: String,
	@Column(nullable = false, unique = true)
	var isbn: String,
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	var status: BookStatus = BookStatus.AVAILABLE,
	var borrowedBy: String? = null,
) {
	fun borrow(borrower: String) {
		check(status == BookStatus.AVAILABLE) { "Book is already borrowed" }
		status = BookStatus.BORROWED
		borrowedBy = borrower
	}

	fun markReturned() {
		check(status == BookStatus.BORROWED) { "Book is not currently borrowed" }
		status = BookStatus.AVAILABLE
		borrowedBy = null
	}
}

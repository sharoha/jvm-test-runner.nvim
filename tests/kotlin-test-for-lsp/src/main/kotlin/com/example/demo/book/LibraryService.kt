package com.example.demo.book

import com.example.demo.support.ConflictException
import com.example.demo.support.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LibraryService(
    private val bookRepository: BookRepository,
) {
    @Transactional(readOnly = true)
    fun listBooks(): List<BookResponse> = bookRepository.findAll()
        .sortedBy { it.id }
        .map(Book::toResponse)

    fun addBook(request: CreateBookRequest): BookResponse {
        if (bookRepository.existsByIsbn(request.isbn)) {
            throw ConflictException("A book with ISBN ${request.isbn} already exists")
        }

        val savedBook = bookRepository.save(
            Book(
                title = request.title.trim(),
                author = request.author.trim(),
                isbn = request.isbn.trim(),
            ),
        )

        return savedBook.toResponse()
    }

    fun borrowBook(bookId: Long, borrower: String): BookResponse {
        val book = getBook(bookId)
        try {
            book.borrow(borrower.trim())
        } catch (_: IllegalStateException) {
            throw ConflictException("Book $bookId is already borrowed")
        }

        return book.toResponse()
    }

    fun returnBook(bookId: Long): BookResponse {
        val book = getBook(bookId)
        try {
            book.markReturned()
        } catch (_: IllegalStateException) {
            throw ConflictException("Book $bookId is not currently borrowed")
        }

        return book.toResponse()
    }

    private fun getBook(bookId: Long): Book =
        bookRepository.findById(bookId).orElseThrow {
            NotFoundException("Book $bookId was not found")
        }
}

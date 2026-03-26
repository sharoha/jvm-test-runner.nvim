package com.example.demo.book

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@Service
@RequestMapping("/api/books")
class LibraryController(
    private val libraryService: LibraryService,
) {
    @GetMapping
    fun listBooks(): List<BookResponse> = libraryService.listBooks()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addBook(@Valid @RequestBody request: CreateBookRequest): BookResponse =
        libraryService.addBook(request)

    @PostMapping("/{bookId}/borrow")
    fun borrowBook(
        @PathVariable bookId: Long,
        @Valid @RequestBody request: BorrowBookRequest,
    ): BookResponse = libraryService.borrowBook(bookId, request.borrower)

    @PostMapping("/{bookId}/return")
    fun returnBook(@PathVariable bookId: Long): BookResponse =
        libraryService.returnBook(bookId)
}

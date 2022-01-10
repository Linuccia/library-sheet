package com.lowlevel.librarysheet.controller

import com.lowlevel.librarysheet.model.Book
import com.lowlevel.librarysheet.service.BookService
import com.lowlevel.librarysheet.service.impl.BookServiceImpl
import tornadofx.Controller

class BooksController: Controller() {

    private val bookService: BookService = BookServiceImpl()

    fun getAllBooks(): List<Book> {
        return bookService.findAllBooks()
    }

    fun findByName(name: String): List<Book> {
        return bookService.findBooksByName(name)
    }

    fun findByAuthor(author: String): List<Book> {
        return bookService.findBooksByAuthor(author)
    }

    fun addBook(name: String, author: String, count: Int): String {
        return when (bookService.addBook(name, author, count)) {
            "Added" -> "Добавлена новая книга в количестве $count"
            "Updated" -> "Количество книги увеличено на $count"
            else -> ""
        }
    }

}
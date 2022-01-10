package com.lowlevel.librarysheet.service

import com.lowlevel.librarysheet.model.Book

interface BookService {

    fun addBook(name: String, author: String, count: Int): String
    fun findBooksByName(name: String): List<Book>
    fun findBooksByAuthor(author: String): List<Book>
    fun findAllBooks(): List<Book>
    fun findBookById(id: Int): Book

}
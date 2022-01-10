package com.lowlevel.librarysheet.database.repository

import com.lowlevel.librarysheet.model.Book

interface BookRepository {

    fun findById(id: Int): Book
    fun findByName(name: String): List<Book>
    fun findByAuthor(author: String): List<Book>
    fun findByNameAndAuthor(name: String, author: String): Book
    fun updateCount(bookId: Int, count: Int)
    fun findAll(): List<Book>
    fun insert(name: String, author: String, count: Int)
}
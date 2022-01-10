package com.lowlevel.librarysheet.service.impl

import com.lowlevel.librarysheet.database.repository.impl.BookRepositoryImpl
import com.lowlevel.librarysheet.model.Book
import com.lowlevel.librarysheet.service.BookService

class BookServiceImpl : BookService {

    private val bookRepository = BookRepositoryImpl()

    override fun addBook(name: String, author: String, count: Int): String {
        val book = bookRepository.findByNameAndAuthor(name, author)
        return if (book.getId() == null)  {
            bookRepository.insert(name, author, count)
            "Added"
        } else {
            bookRepository.updateCount(book.getId()!!, count)
            "Updated"
        }
    }

    override fun findBooksByName(name: String): List<Book> {
        return bookRepository.findByName(name)
    }

    override fun findBooksByAuthor(author: String): List<Book> {
        return bookRepository.findByAuthor(author)
    }

    override fun findBookById(id: Int): Book {
        return bookRepository.findById(id)
    }

    override fun findAllBooks(): List<Book> {
        return bookRepository.findAll()
    }

}
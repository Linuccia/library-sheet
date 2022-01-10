package com.lowlevel.librarysheet.service.impl

import com.lowlevel.librarysheet.database.repository.impl.BookRepositoryImpl
import com.lowlevel.librarysheet.database.repository.impl.ReaderRepositoryImpl
import com.lowlevel.librarysheet.database.repository.impl.ReaderSheetRepositoryImpl
import com.lowlevel.librarysheet.model.ReaderSheet
import com.lowlevel.librarysheet.service.ReaderSheetService

class ReaderSheetServiceImpl : ReaderSheetService {

    val readSheetRepository = ReaderSheetRepositoryImpl()
    val bookRepository = BookRepositoryImpl()
    val readerRepository = ReaderRepositoryImpl()

    override fun takeBook(number: String, bookId: Int): String {
        val book = bookRepository.findById(bookId)
        if (book.getCount()!! < 1) return "Out of count"
        val reader = readerRepository.findByNumber(number, false)
        if (reader.getNumber() == null) return "Not exists"
        readSheetRepository.insert(number, bookId)
        bookRepository.updateCount(bookId, -1)
        readerRepository.updateCount(number, 1)
        return "Success"
    }

    override fun returnBook(number: String, bookName: String, bookAuthor: String) {
        val bookId =  bookRepository.findByNameAndAuthor(bookName, bookAuthor).getId()
        readSheetRepository.deleteByNumberAndBookId(number, bookId!!)
        bookRepository.updateCount(bookId, 1)
        readerRepository.updateCount(number, -1)
    }

    override fun findByNumber(number: String): List<ReaderSheet> {
        return readSheetRepository.findByNumber(number)
    }

}
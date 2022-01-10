package com.lowlevel.librarysheet.controller

import com.lowlevel.librarysheet.service.BookService
import com.lowlevel.librarysheet.service.ReaderSheetService
import com.lowlevel.librarysheet.service.impl.BookServiceImpl
import com.lowlevel.librarysheet.service.impl.ReaderSheetServiceImpl
import tornadofx.Controller

class TakeBookController: Controller() {

    private val readerSheetService: ReaderSheetService = ReaderSheetServiceImpl()
    private val bookService: BookService = BookServiceImpl()

    fun takeBook(bookId: Int, number: String): String {
        return when (readerSheetService.takeBook(number, bookId)) {
            "Out of count" -> "Отсутствуют экземпляры книг"
            "Not exists" -> "Читателя не существует либо Он заблокирован"
            "Success" -> "Записано в читательский лист"
            else -> ""
        }
    }

    fun returnNameAndAuthor(bookId: Int): List<String> {
        val book = bookService.findBookById(bookId)
        val list = ArrayList<String>()
        book.getName()?.let { list.add(it) }
        book.getAuthor()?.let { list.add(it) }
        return list
    }

}
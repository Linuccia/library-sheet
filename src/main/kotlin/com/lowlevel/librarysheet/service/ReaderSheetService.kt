package com.lowlevel.librarysheet.service

import com.lowlevel.librarysheet.model.ReaderSheet

interface ReaderSheetService {

    fun takeBook(number: String, bookId: Int): String
    fun returnBook(number: String, bookName: String, bookAuthor: String)
    fun findByNumber(number: String): List<ReaderSheet>

}
package com.lowlevel.librarysheet.model

import java.sql.*

class ReaderSheet(_readerNumber: String?, _bookName: String?, _bookAuthor: String?, _startDate: Date?, _endDate: Date?) {

    private var readerNumber: String?
    private var bookName: String?
    private var bookAuthor: String?
    private var startDate: Date?
    private var endDate: Date?

    init {
        readerNumber = _readerNumber
        bookName = _bookName
        bookAuthor = _bookAuthor
        startDate = _startDate
        endDate = _endDate
    }

    fun getReaderNum() = readerNumber
    fun getBookName() = bookName
    fun getBookAuthor() = bookAuthor
    fun getStartDate() = startDate
    fun getEndDate() = endDate

    fun setReaderNumber(number: String) {
        readerNumber = number
    }

    fun setBookName(_bookName: String?) {
        bookName = _bookName
    }

    fun setBookAuthor(_bookAuthor: String?) {
        bookAuthor = _bookAuthor
    }

    fun setStartDate(date: Date) {
        startDate = date
    }

    fun setEndDate(date: Date) {
        endDate = date
    }

    override fun toString(): String {
        return "ReaderSheet(readerNumber=$readerNumber, bookName=$bookName, " +
                "bookAuthor=$bookAuthor, startDate=$startDate, endDate=$endDate)"
    }


}
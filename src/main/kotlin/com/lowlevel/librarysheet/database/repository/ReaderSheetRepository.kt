package com.lowlevel.librarysheet.database.repository

import com.lowlevel.librarysheet.model.ReaderSheet

interface ReaderSheetRepository {

    fun findByNumber(number: String): List<ReaderSheet>
    fun deleteByNumberAndBookId(number: String, bookId: Int)
    fun insert(number: String, bookId: Int)
    fun findAll(): List<ReaderSheet>

}
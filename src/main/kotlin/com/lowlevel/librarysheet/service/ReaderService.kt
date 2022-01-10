package com.lowlevel.librarysheet.service

import com.lowlevel.librarysheet.model.Reader
import com.lowlevel.librarysheet.model.ReaderSheet

interface ReaderService {

    fun findReaderByNumber(number: String, blocked: Boolean): Reader
    fun findAllReaders(blocked: Boolean): List<Reader>
    fun addReader(name: String, number: String): String
    fun blockReader(number: String?)
    fun unblockReader(number: String?)
    fun checkReaderOverdue()

}
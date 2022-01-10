package com.lowlevel.librarysheet.controller

import com.lowlevel.librarysheet.model.ReaderSheet
import com.lowlevel.librarysheet.service.ReaderService
import com.lowlevel.librarysheet.service.ReaderSheetService
import com.lowlevel.librarysheet.service.impl.ReaderServiceImpl
import com.lowlevel.librarysheet.service.impl.ReaderSheetServiceImpl
import tornadofx.Controller

class ReaderSheetController: Controller() {

    private val readerSheetService: ReaderSheetService = ReaderSheetServiceImpl()
    private val readerService: ReaderService = ReaderServiceImpl()

    fun getAllByNumber(number: String): List<ReaderSheet> {
        return readerSheetService.findByNumber(number)
    }

    fun getName(number: String, blocked: Boolean): String? {
        return readerService.findReaderByNumber(number, blocked).getName()
    }

    fun returnBook(number: String, bookName: String, bookAuthor: String) {
        readerSheetService.returnBook(number, bookName, bookAuthor)
    }

}
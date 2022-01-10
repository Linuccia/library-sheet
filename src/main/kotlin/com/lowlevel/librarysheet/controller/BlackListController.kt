package com.lowlevel.librarysheet.controller

import com.lowlevel.librarysheet.model.Reader
import com.lowlevel.librarysheet.service.ReaderService
import com.lowlevel.librarysheet.service.impl.ReaderServiceImpl
import tornadofx.Controller

class BlackListController: Controller() {

    private val readerService: ReaderService = ReaderServiceImpl()

    fun getAllReaders(): List<Reader> {
        return readerService.findAllReaders(true)
    }

    fun findByNumber(number: String): List<Reader>? {
        val list = ArrayList<Reader>()
        val reader = readerService.findReaderByNumber(number, true)
        if (reader.getNumber() == null) return null
        list.add(reader)
        return list
    }

    fun unblockReader(number: String?): List<Reader> {
        readerService.unblockReader(number)
        return getAllReaders()
    }
}
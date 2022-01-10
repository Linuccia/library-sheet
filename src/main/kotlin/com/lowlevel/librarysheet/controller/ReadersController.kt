package com.lowlevel.librarysheet.controller

import com.lowlevel.librarysheet.model.Reader
import com.lowlevel.librarysheet.service.ReaderService
import com.lowlevel.librarysheet.service.impl.ReaderServiceImpl
import tornadofx.Controller

class ReadersController: Controller() {

    private val readerService: ReaderService = ReaderServiceImpl()

    fun getAllReaders(): List<Reader> {
        return readerService.findAllReaders(false)
    }

    fun findByNumber(number: String): List<Reader>? {
        val list = ArrayList<Reader>()
        val reader = readerService.findReaderByNumber(number, false)
        if (reader.getNumber() == null) return null
        list.add(reader)
        return list
    }

    fun blockReader(number: String?): List<Reader> {
        readerService.blockReader(number)
        return getAllReaders()
    }

    fun addReader(name: String, number: String): String {
        return when (readerService.addReader(name, number)) {
            "Already exists" -> "Читатель уже существует"
            "Is blocked" -> "Читатель заблокирован"
            "Success" -> "Читатель успешно добавлен"
            else -> ""
        }
    }

    fun checkOverdue() {
        readerService.checkReaderOverdue()
    }

}
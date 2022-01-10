package com.lowlevel.librarysheet.service.impl

import com.lowlevel.librarysheet.database.repository.impl.ReaderRepositoryImpl
import com.lowlevel.librarysheet.database.repository.impl.ReaderSheetRepositoryImpl
import com.lowlevel.librarysheet.model.Reader
import com.lowlevel.librarysheet.service.ReaderService
import java.sql.Date
import java.util.stream.Collectors

class ReaderServiceImpl : ReaderService {

    private val readerRepository = ReaderRepositoryImpl()
    private val readerSheetRepository = ReaderSheetRepositoryImpl()

    override fun findReaderByNumber(number: String, blocked: Boolean): Reader {
        return readerRepository.findByNumber(number, blocked)
    }

    override fun findAllReaders(blocked: Boolean): List<Reader> {
        return readerRepository.findAll(blocked)
    }

    override fun addReader(name: String, number: String): String {
        if (readerRepository.findByNumber(number, false).getNumber() != null)
            return "Already exists"
        if (readerRepository.findByNumber(number, true).getNumber() != null)
            return "Is blocked"
        readerRepository.insert(name, number)
        return "Success"
    }

    override fun blockReader(number: String?) {
        readerRepository.updateBlocked(number, true)
    }

    override fun unblockReader(number: String?) {
        readerRepository.updateBlocked(number, false)
    }

    override fun checkReaderOverdue() {
        val hashMap =  HashMap<String, Boolean>()
        hashMap.putAll(readerRepository.findAll(false)
            .stream()
            .filter { r -> r.getBookCount() == 0}
            .collect(Collectors.toList()).associateBy( { it.getNumber()!! }, { false } ))
        hashMap.putAll(readerSheetRepository.findAll()
            .stream()
            .filter { r -> r.getEndDate()!! < Date(System.currentTimeMillis()) }
            .collect(Collectors.toList()).associateBy( { it.getReaderNum()!! }, { true } ))
        hashMap.putAll(readerSheetRepository.findAll()
            .stream()
            .filter { r -> r.getEndDate()!! >= Date(System.currentTimeMillis()) }
            .filter { r -> !(hashMap.contains(r.getReaderNum())) }
            .collect(Collectors.toList()).associateBy( { it.getReaderNum()!! }, { false } ))
        for ((key, value) in hashMap) {
            readerRepository.updateOverdue(key, value)
        }
    }

}
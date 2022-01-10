package com.lowlevel.librarysheet.database.repository

import com.lowlevel.librarysheet.model.Reader

interface ReaderRepository {

    fun findByNumber(number: String, blocked: Boolean): Reader
    fun findAll(blocked: Boolean): List<Reader>
    fun updateBlocked(number: String?, blocked: Boolean)
    fun updateOverdue(number: String, overdue: Boolean)
    fun updateCount(number: String, count: Int)
    fun insert(name: String, number: String)

}
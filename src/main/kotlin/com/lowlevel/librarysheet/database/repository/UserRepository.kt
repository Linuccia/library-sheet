package com.lowlevel.librarysheet.database.repository

interface UserRepository {

    fun findPasswordByLogin(login: String): String
    fun findByLogin(login: String): Boolean
    fun insert(login: String, password: String)

}
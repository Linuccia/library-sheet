package com.lowlevel.librarysheet.service

interface UserService {

    fun signUp(login: String, password: String): String
    fun logIn(login: String, password: String): String

}
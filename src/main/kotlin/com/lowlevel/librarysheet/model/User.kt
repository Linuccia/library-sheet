package com.lowlevel.librarysheet.model

object User {

    private var login: String? = null
    private var password: String? = null

    fun login(login: String?) {
        this.login = login
    }

    fun password(password: String?) {
        this.password = password
    }

    override fun toString(): String = login ?:""

}
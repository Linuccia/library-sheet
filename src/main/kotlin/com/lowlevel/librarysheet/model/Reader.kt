package com.lowlevel.librarysheet.model

class Reader(_name: String?, _number: String?, _bookCount: Int?, _overdue: Boolean?, _blocked: Boolean?) {

    private var name: String?
    private var number: String?
    private var bookCount: Int?
    private var overdue: Boolean?
    private var blocked: Boolean?

    init {
        name = _name
        number = _number
        bookCount = _bookCount
        overdue = _overdue
        blocked = _blocked
    }

    fun getName() = name
    fun getNumber() = number
    fun getBookCount() = bookCount
    fun getOverdue() = overdue
    fun getBlocked() = blocked

    fun setName(name: String) {
        this.name = name
    }

    fun setNumber(number: String) {
        this.number = number
    }

    fun setBookCount(bookCount: Int) {
        this.bookCount = bookCount
    }

    fun setOverdue(overdue: Boolean) {
        this.overdue = overdue
    }

    fun setBlocked(blocked: Boolean) {
        this.blocked = blocked
    }

    override fun toString(): String {
        return "Reader(name=$name, number=$number, bookCount=$bookCount, overdue=$overdue, blocked=$blocked)"
    }


}
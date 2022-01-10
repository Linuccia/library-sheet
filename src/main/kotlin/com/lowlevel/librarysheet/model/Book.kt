package com.lowlevel.librarysheet.model

class Book(_id: Int?, _name: String?, _author: String?, _count: Int?) {

    private var id: Int?
    private var name: String?
    private var author: String?
    private var count: Int?


    init {
        id = _id
        name = _name
        author = _author
        count = _count
    }

    fun getId() = id
    fun getName() = name
    fun getAuthor() = author
    fun getCount() = count

    fun setId(id: Int) {
        this.id = id
    }

    fun setName(name: String) {
        this.name = name
    }

    fun setAuthor(author: String) {
        this.author = author
    }

    fun setCount(count: Int) {
        this.count = count
    }

    override fun toString(): String {
        return "Book(id=$id, name=$name, author=$author, count=$count)"
    }


}
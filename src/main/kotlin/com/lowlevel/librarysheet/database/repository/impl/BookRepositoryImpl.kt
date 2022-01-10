package com.lowlevel.librarysheet.database.repository.impl

import com.lowlevel.librarysheet.database.connection.impl.DatabaseConnection
import com.lowlevel.librarysheet.database.repository.BookRepository
import com.lowlevel.librarysheet.model.Book
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement

class BookRepositoryImpl: BookRepository {

    private val connection = DatabaseConnection.connection()
    private lateinit var statement: Statement
    private lateinit var preStatement: PreparedStatement
    private lateinit var resultSet: ResultSet

    private fun returnList(): List<Book> {
        val list = ArrayList<Book>()
        while (resultSet.next()) {
            val book = Book(resultSet.getInt("id"), resultSet.getString("name"),
                resultSet.getString("author"), resultSet.getInt("count"))
            list.add(book)
        }
        return list
    }

    override fun findById(id: Int): Book {
        preStatement = connection.prepareStatement("select * from Books where id = ?;")
        preStatement.setInt(1, id)
        resultSet = preStatement.executeQuery()
        if (resultSet.next()) return Book(resultSet.getInt("id"), resultSet.getString("name"),
                                    resultSet.getString("author"), resultSet.getInt("count"))
        return Book(null, null, null, null)
    }

    override fun findByName(name: String): List<Book> {
        preStatement = connection.prepareStatement("select * from Books where name = ?;")
        preStatement.setString(1, name)
        resultSet = preStatement.executeQuery()
        return returnList()
    }

    override fun findByAuthor(author: String): List<Book> {
        preStatement = connection.prepareStatement("select * from Books where author = ?;")
        preStatement.setString(1, author)
        resultSet = preStatement.executeQuery()
        return returnList()
    }

    override fun findAll(): List<Book> {
        statement = connection.createStatement()
        resultSet = statement.executeQuery("select * from Books;")
        return returnList()
    }

    override fun findByNameAndAuthor(name: String, author: String): Book {
        preStatement = connection.prepareStatement("select * from Books where name = ? and author = ?;")
        preStatement.setString(1, name)
        preStatement.setString(2, author)
        resultSet = preStatement.executeQuery()
        if (resultSet.next()) return Book(resultSet.getInt("id"), resultSet.getString("name"),
                                    resultSet.getString("author"), resultSet.getInt("count"))
        return Book(null, null, null, null)
    }

    override fun updateCount(bookId: Int, count: Int) {
        preStatement = connection.prepareStatement("update books set count = count + ? where id = ?;")
        preStatement.setInt(1, count)
        preStatement.setInt(2, bookId)
        preStatement.execute()
    }

    override fun insert(name: String, author: String, count: Int) {
        preStatement = connection.prepareStatement("insert into books values (nextval('Books_IDS'), ?, ?, ?);")
        preStatement.setString(1, name)
        preStatement.setString(2, author)
        preStatement.setInt(3, count)
        preStatement.execute()
    }

}
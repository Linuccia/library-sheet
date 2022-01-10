package com.lowlevel.librarysheet.database.repository.impl

import com.lowlevel.librarysheet.database.connection.impl.DatabaseConnection
import com.lowlevel.librarysheet.database.repository.ReaderRepository
import com.lowlevel.librarysheet.model.Reader
import java.sql.PreparedStatement
import java.sql.ResultSet

class ReaderRepositoryImpl : ReaderRepository {

    private val connection = DatabaseConnection.connection()
    private lateinit var preStatement: PreparedStatement
    private lateinit var resultSet: ResultSet

    override fun findByNumber(number: String, blocked: Boolean): Reader {
        preStatement = connection.prepareStatement("select * from Readers where number = ? and blocked = ?;")
        preStatement.setString(1, number)
        preStatement.setBoolean(2, blocked)
        resultSet = preStatement.executeQuery()
        val reader = Reader(null, null, null, null, null)
        while (resultSet.next()) {
            reader.setName(resultSet.getString("name"))
            reader.setNumber(resultSet.getString("number"))
            reader.setBookCount(resultSet.getInt("book_count"))
            reader.setOverdue(resultSet.getBoolean("overdue"))
            reader.setBlocked(resultSet.getBoolean("blocked"))
        }
        return reader
    }

    private fun returnList(): List<Reader> {
        val list = ArrayList<Reader>()
        while (resultSet.next()) {
            val reader = Reader(resultSet.getString("name"), resultSet.getString("number"),
                resultSet.getInt("book_count"), resultSet.getBoolean("overdue"),
                resultSet.getBoolean("blocked"))
            list.add(reader)
        }
        return list
    }

    override fun findAll(blocked: Boolean): List<Reader> {
        preStatement = connection.prepareStatement("select * from Readers where blocked = ?;")
        preStatement.setBoolean(1, blocked)
        resultSet = preStatement.executeQuery()
        return returnList()
    }

    override fun updateBlocked(number: String?, blocked: Boolean) {
        preStatement = connection.prepareStatement("update Readers set blocked = ? where number = ?")
        preStatement.setString(2, number)
        preStatement.setBoolean(1, blocked)
        preStatement.execute()
    }

    override fun updateOverdue(number: String, overdue: Boolean) {
        preStatement = connection.prepareStatement("update Readers set overdue = ? where number = ?")
        preStatement.setString(2, number)
        preStatement.setBoolean(1, overdue)
        preStatement.execute()
    }

    override fun updateCount(number: String, count: Int) {
        preStatement = connection.prepareStatement("update Readers set book_count = book_count + ? where number = ?")
        preStatement.setString(2, number)
        preStatement.setInt(1, count)
        preStatement.execute()
    }

    override fun insert(name: String, number: String) {
        preStatement = connection.prepareStatement("insert into readers values (?, ?, 0, false, false);")
        preStatement.setString(1, name)
        preStatement.setString(2, number)
        preStatement.execute()
    }

}
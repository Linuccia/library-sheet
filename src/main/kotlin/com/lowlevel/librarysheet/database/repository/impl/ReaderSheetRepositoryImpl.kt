package com.lowlevel.librarysheet.database.repository.impl

import com.lowlevel.librarysheet.database.connection.impl.DatabaseConnection
import com.lowlevel.librarysheet.database.repository.ReaderSheetRepository
import com.lowlevel.librarysheet.model.ReaderSheet
import java.sql.Date
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement
import kotlin.collections.ArrayList

class ReaderSheetRepositoryImpl : ReaderSheetRepository {

    private val connection = DatabaseConnection.connection()
    private val bookRepository = BookRepositoryImpl()
    private lateinit var preStatement: PreparedStatement
    private lateinit var statement: Statement
    private lateinit var resultSet: ResultSet

    private fun returnList(): List<ReaderSheet> {
        val list = ArrayList<ReaderSheet>()
        while (resultSet.next()) {
            val book = bookRepository.findById(resultSet.getInt("book_id"))
            val readerSheet = ReaderSheet(resultSet.getString("reader_num"), book.getName(), book.getAuthor(),
                resultSet.getDate("start_date"), resultSet.getDate("end_date"))
            list.add(readerSheet)
        }
        return list
    }

    override fun findByNumber(number: String): List<ReaderSheet> {
        preStatement = connection.prepareStatement("select * from Reader_Sheet where reader_num = ?;")
        preStatement.setString(1, number)
        resultSet = preStatement.executeQuery()
        return returnList()
    }

    override fun deleteByNumberAndBookId(number: String, bookId: Int) {
        preStatement = connection.prepareStatement("delete from reader_sheet where reader_num = ? and book_id = ?;")
        preStatement.setString(1, number)
        preStatement.setInt(2, bookId)
        preStatement.execute()
    }

    override fun insert(number: String, bookId: Int) {
        preStatement = connection.prepareStatement("insert into reader_sheet values (?, ?, ?, ?);")
        preStatement.setString(1, number)
        preStatement.setInt(2, bookId)
        val startDate = Date(System.currentTimeMillis())
        val endDate = Date.valueOf(startDate.toLocalDate().plusDays(14))
        preStatement.setDate(3, startDate)
        preStatement.setDate(4, endDate)
        preStatement.execute()
    }

    override fun findAll(): List<ReaderSheet> {
        statement = connection.createStatement()
        resultSet = statement.executeQuery("select * from reader_sheet;")
        return returnList()
    }

}
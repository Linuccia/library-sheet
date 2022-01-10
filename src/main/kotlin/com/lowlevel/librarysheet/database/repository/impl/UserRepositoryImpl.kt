package com.lowlevel.librarysheet.database.repository.impl

import com.lowlevel.librarysheet.database.connection.impl.DatabaseConnection
import com.lowlevel.librarysheet.database.repository.UserRepository
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement

class UserRepositoryImpl : UserRepository {

    private val connection = DatabaseConnection.connection()
    private lateinit var statement: Statement
    private lateinit var preStatement: PreparedStatement
    private lateinit var resultSet: ResultSet

    override fun findPasswordByLogin(login: String): String {
        preStatement = connection.prepareStatement("select password from Users where login = ?;")
        preStatement.setString(1, login)
        resultSet = preStatement.executeQuery()
        var password = ""
        while (resultSet.next()) {
            password = resultSet.getString("password")
        }
        return password
    }

    override fun findByLogin(login: String): Boolean {
        preStatement = connection.prepareStatement("select password from Users where login = ?;")
        preStatement.setString(1, login)
        resultSet = preStatement.executeQuery()
        return resultSet.next()
    }

    override fun insert(login: String, password: String) {
        preStatement = connection.prepareStatement("insert into Users values (?, ?);")
        preStatement.setString(1, login)
        preStatement.setString(2, password)
        preStatement.execute()
    }

}
package com.lowlevel.librarysheet.database.connection.impl

import com.lowlevel.librarysheet.database.connection.ConnectionHandler
import java.io.FileInputStream
import java.io.IOException
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.*

class ConnectionHandlerImpl : ConnectionHandler {

    override fun setConnection(propertiesPath: String): Connection {
        val properties = Properties()
        println("Trying to connect database...")
        properties.load(FileInputStream(propertiesPath))
        Class.forName("org.postgresql.Driver")
        val connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("name"),
        properties.getProperty("password"))
        println("Connection created")
        return connection
    }

}
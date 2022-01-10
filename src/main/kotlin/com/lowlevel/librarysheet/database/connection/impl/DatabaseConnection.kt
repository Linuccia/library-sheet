package com.lowlevel.librarysheet.database.connection.impl

import java.io.IOException
import java.sql.Connection
import java.sql.SQLException

class DatabaseConnection {

    companion object {

        lateinit var connection: Connection

        fun connect(propertiesPath: String) {
            try {
                val connectionHandler = ConnectionHandlerImpl()
                connection = connectionHandler.setConnection(propertiesPath)
            } catch (e: IOException) {
                e.printStackTrace()
                println("Failed to connect database: incorrect properties path")
            } catch (e: SQLException) {
                e.printStackTrace()
                println("Failed to connect database")
            }
        }

        fun connection() = connection

    }

}
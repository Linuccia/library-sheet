package com.lowlevel.librarysheet.database.connection

import java.sql.Connection

interface ConnectionHandler {

    fun setConnection(propertiesPath: String): Connection

}
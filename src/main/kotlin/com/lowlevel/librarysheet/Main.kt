package com.lowlevel.librarysheet

import com.lowlevel.librarysheet.database.connection.impl.DatabaseConnection
import com.lowlevel.librarysheet.view.UIApp
import tornadofx.launch

fun main() {
    DatabaseConnection.connect("src/main/resources/database.properties")
    launch<UIApp>()
}
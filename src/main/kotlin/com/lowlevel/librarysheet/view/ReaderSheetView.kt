package com.lowlevel.librarysheet.view

import com.lowlevel.librarysheet.controller.ReaderSheetController
import com.lowlevel.librarysheet.model.ReaderSheet
import javafx.event.EventHandler
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.layout.AnchorPane
import tornadofx.View
import java.sql.*

class ReaderSheetView (number: String, blocked: Boolean): View() {

    override val root: AnchorPane by fxml("/readerSheetView.fxml")
    private val controller: ReaderSheetController by inject()

    private val readerNameLabel: Label by fxid("readerNameLabel")
    private val returnBookButton: Button by fxid("returnBookButton")
    private val readerSheetTable: TableView<ReaderSheet> by fxid("readerSheetTable")
    private val selectionModel : TableView.TableViewSelectionModel<ReaderSheet> = readerSheetTable.selectionModel
    private val bookNameColumn: TableColumn<ReaderSheet, String> by fxid("bookNameColumn")
    private val authorColumn: TableColumn<ReaderSheet, String> by fxid("authorColumn")
    private val startDateColumn: TableColumn<ReaderSheet, Date> by fxid("startDateColumn")
    private val endDateColumn: TableColumn<ReaderSheet, Date> by fxid("endDateColumn")
    private var number: String
    private var blocked: Boolean

    init {
        this.number = number
        this.blocked = blocked
        readerNameLabel.text = controller.getName(number, blocked)
        initTable()
    }

    private fun initTable() {
        bookNameColumn.cellValueFactory = PropertyValueFactory("bookName")
        authorColumn.cellValueFactory = PropertyValueFactory("bookAuthor")
        startDateColumn.cellValueFactory = PropertyValueFactory("startDate")
        endDateColumn.cellValueFactory = PropertyValueFactory("endDate")
        fillTable(controller.getAllByNumber(number))
        returnBookButton.onAction = EventHandler { returnBookButtonAction() }
    }

    private fun fillTable(sheets: List<ReaderSheet>) {
        readerSheetTable.items.clear()
        readerSheetTable.items.addAll(sheets)
    }

    private fun returnBookButtonAction() {
        if (selectionModel.selectedItem != null) {
            val name = selectionModel.selectedItem.getBookName()
            val author = selectionModel.selectedItem.getBookAuthor()
            controller.returnBook(number, name!!, author!!)
            fillTable(controller.getAllByNumber(number))
        }
    }

}
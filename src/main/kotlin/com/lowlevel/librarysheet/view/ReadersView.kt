package com.lowlevel.librarysheet.view

import com.lowlevel.librarysheet.controller.ReadersController
import com.lowlevel.librarysheet.model.Reader
import com.lowlevel.librarysheet.model.User
import javafx.event.EventHandler
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.layout.AnchorPane
import tornadofx.View

class ReadersView: View() {

    override val root: AnchorPane by fxml("/readersView.fxml")
    private val controller: ReadersController by inject()
    private val userLabel: Label by fxid("userLabel")
    private val booksButton: Button by fxid("booksButton")
    private val blackListButton: Button by fxid("blackListButton")
    private val searchReaderField: TextField by fxid("searchReaderField")
    private val searchReaderButton: Button by fxid("searchReaderButton")
    private val readerSheetButton: Button by fxid("readerSheetButton")
    private val addReaderButton: Button by fxid("addReaderButton")
    private val checkButton: Button by fxid("checkButton")
    private val blockButton: Button by fxid("blockButton")
    private val readersTable: TableView<Reader> by fxid("readersTable")
    private val selectionModel : TableView.TableViewSelectionModel<Reader> = readersTable.selectionModel
    private val readerNameColumn: TableColumn<Reader, String> by fxid("readerNameColumn")
    private val readerNumberColumn: TableColumn<Reader, String> by fxid("readerNumberColumn")
    private val readerBookCountColumn: TableColumn<Reader, Int> by fxid("readerBookCountColumn")
    private val overdueColumn: TableColumn<Reader, Boolean> by fxid("overdueColumn")

    private fun booksView() {
        replaceWith<BooksView>()
    }

    private fun blackListView() {
        replaceWith<BlackListView>()
    }

    init {
        userLabel.text = User.toString()
        booksButton.onAction = EventHandler { booksView() }
        blackListButton.onAction = EventHandler { blackListView() }
        initTable()
        searchReaderButton.onAction = EventHandler { searchReaderButton() }
        blockButton.onAction = EventHandler { blockButtonAction() }
        addReaderButton.onAction = EventHandler { addReaderButtonAction() }
        readerSheetButton.onAction = EventHandler { readerSheetButtonAction() }
        checkButton.onAction = EventHandler { checkButtonAction() }
    }

    private fun initTable() {
        readerNameColumn.cellValueFactory = PropertyValueFactory("name")
        readerNumberColumn.cellValueFactory = PropertyValueFactory("number")
        readerBookCountColumn.cellValueFactory = PropertyValueFactory("bookCount")
        overdueColumn.cellValueFactory = PropertyValueFactory("overdue")
        fillTable(controller.getAllReaders())
    }

    private fun fillTable(readers: List<Reader>) {
        readersTable.items.clear()
        readersTable.items.addAll(readers)
    }

    private fun searchReaderButton() {
        if (searchReaderField.text.isNullOrBlank()) fillTable(controller.getAllReaders())
        else {
            val result = controller.findByNumber(searchReaderField.text)
            if (result != null) fillTable(result)
        }
    }

    private fun blockButtonAction() {
        if (selectionModel.selectedItem != null)
            fillTable(controller.blockReader(selectionModel.selectedItem.getNumber()))
    }

    private fun readerSheetButtonAction() {
        if (selectionModel.selectedItem != null)
            selectionModel.selectedItem.getNumber()?.let { ReaderSheetView(it, false).openWindow() }
    }

    private fun addReaderButtonAction() {
        AddReaderView().openWindow()
    }

    private fun checkButtonAction() {
        controller.checkOverdue()
        fillTable(controller.getAllReaders())
    }



}
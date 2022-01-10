package com.lowlevel.librarysheet.view

import com.lowlevel.librarysheet.controller.BlackListController
import com.lowlevel.librarysheet.model.Reader
import com.lowlevel.librarysheet.model.User
import javafx.event.EventHandler
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.layout.AnchorPane
import tornadofx.View

class BlackListView: View() {

    override val root: AnchorPane by fxml("/blackListView.fxml")
    private val controller: BlackListController by inject()

    private val userLabel: Label by fxid("userLabel")
    private val readersButton: Button by fxid("readersButton")
    private val booksButton: Button by fxid("booksButton")
    private val blackListTable: TableView<Reader> by fxid("blackListTable")
    private val selectionModel : TableView.TableViewSelectionModel<Reader> = blackListTable.selectionModel
    private val blockedNameColumn: TableColumn<Reader, String> by fxid("blockedNameColumn")
    private val blockedNumberColumn: TableColumn<Reader, String> by fxid("blockedNumberColumn")
    private val blockedBooksColumn: TableColumn<Reader, Int> by fxid("blockedBooksColumn")
    private val searchBlockedField: TextField by fxid("searchBlockedField")
    private val searchBlockedButton: Button by fxid("searchBlockedButton")
    private val blockedSheetButton: Button by fxid("blockedSheetButton")
    private val unblockButton: Button by fxid("unblockButton")


    private fun booksView() {
        replaceWith<BooksView>()
    }

    private fun readersView() {
        replaceWith<ReadersView>()
    }

    init {
        userLabel.text = User.toString()
        booksButton.onAction = EventHandler { booksView() }
        readersButton.onAction = EventHandler { readersView() }
        initTable()
    }

    private fun initTable() {
        blockedNameColumn.cellValueFactory = PropertyValueFactory("name")
        blockedNumberColumn.cellValueFactory = PropertyValueFactory("number")
        blockedBooksColumn.cellValueFactory = PropertyValueFactory("bookCount")
        fillTable(controller.getAllReaders())
        searchBlockedButton.onAction = EventHandler { searchBlockedButton() }
        unblockButton.onAction = EventHandler { unblockButtonAction() }
        blockedSheetButton.onAction = EventHandler { blockedSheetButtonAction() }
    }

    private fun fillTable(readers: List<Reader>) {
        blackListTable.items.clear()
        blackListTable.items.addAll(readers)
    }

    private fun searchBlockedButton() {
        if (searchBlockedField.text.isNullOrBlank()) fillTable(controller.getAllReaders())
        else {
            val result = controller.findByNumber(searchBlockedField.text)
            if (result != null) fillTable(result)
        }
    }

    private fun unblockButtonAction() {
        if (selectionModel.selectedItem != null)
            fillTable(controller.unblockReader(selectionModel.selectedItem.getNumber()))
    }

    private fun blockedSheetButtonAction() {
        if (selectionModel.selectedItem != null)
            selectionModel.selectedItem.getNumber()?.let { ReaderSheetView(it, true).openWindow() }
    }
}
package com.lowlevel.librarysheet.view

import com.lowlevel.librarysheet.controller.BooksController
import com.lowlevel.librarysheet.model.Book
import com.lowlevel.librarysheet.model.User
import javafx.event.EventHandler
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.layout.AnchorPane
import tornadofx.View

class BooksView: View() {

    override val root: AnchorPane by fxml("/booksView.fxml")
    private val controller: BooksController by inject()
    private val userLabel: Label by fxid("userLabel")
    private val readersButton: Button by fxid("readersButton")
    private val blackListButton: Button by fxid("blackListButton")
    private val booksTable: TableView<Book> by fxid("booksTable")
    private val selectionModel : TableView.TableViewSelectionModel<Book> = booksTable.selectionModel
    private val bookNameColumn: TableColumn<Book, String> by fxid("bookNameColumn")
    private val authorColumn: TableColumn<Book, String> by fxid("authorColumn")
    private val bookCountColumn: TableColumn<Book, Int> by fxid("bookCountColumn")
    private val searchBookField: TextField by fxid("searchBookField")
    private val searchByNameButton: Button by fxid("searchByName")
    private val searchByAuthorButton: Button by fxid("searchByAuthor")
    private val takeBookButton: Button by fxid("takeBookButton")
    private val addBookButton: Button by fxid("addBookButton")


    private fun readersView() {
        replaceWith<ReadersView>()
    }

    private fun blackListView() {
        replaceWith<BlackListView>()
    }

    init {
        userLabel.text = User.toString()
        readersButton.onAction = EventHandler { readersView() }
        blackListButton.onAction = EventHandler { blackListView() }
        initTable()
        searchByNameButton.onAction = EventHandler { searchByNameButton() }
        searchByAuthorButton.onAction = EventHandler { searchByAuthorButton() }
        takeBookButton.onAction = EventHandler { takeBookButtonAction() }
        addBookButton.onAction = EventHandler { addBookButtonAction() }
    }

    private fun initTable() {
        bookNameColumn.cellValueFactory = PropertyValueFactory("name")
        authorColumn.cellValueFactory = PropertyValueFactory("author")
        bookCountColumn.cellValueFactory = PropertyValueFactory("count")
        fillTable(controller.getAllBooks())
    }

    private fun fillTable(books: List<Book>) {
        booksTable.items.clear()
        booksTable.items.addAll(books)
    }

    private fun searchByNameButton() {
        if (searchBookField.text.isNullOrBlank()) fillTable(controller.getAllBooks())
        else {
            val result = controller.findByName(searchBookField.text)
            if (result.isNotEmpty()) fillTable(result)
        }
    }

    private fun searchByAuthorButton() {
        if (searchBookField.text.isNullOrBlank()) fillTable(controller.getAllBooks())
        else {
            val result = controller.findByAuthor(searchBookField.text)
            if (result.isNotEmpty()) fillTable(result)
        }
    }

    private fun takeBookButtonAction() {
        if (selectionModel.selectedItem != null)
            selectionModel.selectedItem.getId()?.let { TakeBookView(it).openWindow() }
    }

    private fun addBookButtonAction() {
        AddBookView().openWindow()
    }

}
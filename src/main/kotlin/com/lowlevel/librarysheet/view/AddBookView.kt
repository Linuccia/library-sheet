package com.lowlevel.librarysheet.view

import com.lowlevel.librarysheet.controller.BooksController
import javafx.event.EventHandler
import javafx.scene.control.*
import javafx.scene.layout.AnchorPane
import tornadofx.View

class AddBookView: View() {

    override val root: AnchorPane by fxml("/addBookView.fxml")
    private val controller: BooksController by inject()
    private val bookNameField: TextField by fxid("bookNameField")
    private val authorField: TextField by fxid("authorField")
    private val countSpinner: Spinner<Int> by fxid("countSpinner")
    private val messageLabel: Label by fxid("messageLabel")
    private val okButton: Button by fxid("okButton")

    init {
        okButton.disableProperty().bind((bookNameField.textProperty().isEmpty)
            .or(authorField.textProperty().isEmpty))
        val valueFactory: SpinnerValueFactory<Int> = SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1)
        countSpinner.valueFactory = valueFactory
        okButton.onAction = EventHandler { okButtonAction() }
    }

    private fun okButtonAction() {
        val message = controller.addBook(bookNameField.text, authorField.text, countSpinner.value)
        messageLabel.text = message
    }

}
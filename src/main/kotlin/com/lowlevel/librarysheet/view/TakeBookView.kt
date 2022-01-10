package com.lowlevel.librarysheet.view

import com.lowlevel.librarysheet.controller.TakeBookController
import javafx.event.EventHandler
import javafx.scene.control.*
import javafx.scene.layout.AnchorPane
import tornadofx.View

class TakeBookView (bookId: Int): View() {

    override val root: AnchorPane by fxml("/takeBookView.fxml")
    private val controller: TakeBookController by inject()
    private val bookNameLabel: Label by fxid("bookNameLabel")
    private val authorLabel: Label by fxid("authorLabel")
    private val readerNumberField: TextField by fxid("readerNumberField")
    private val messageLabel: Label by fxid("messageLabel")
    private val okButton: Button by fxid("okButton")
    private var bookId: Int = 0

    init {
        okButton.disableProperty().bind((readerNumberField.textProperty().isEmpty))
        this.bookId = bookId
        val values = controller.returnNameAndAuthor(bookId)
        bookNameLabel.text = values[0]
        authorLabel.text = values[1]
        okButton.onAction = EventHandler { okButtonAction() }
    }

    private fun okButtonAction() {
        val message = controller.takeBook(bookId, readerNumberField.text)
        messageLabel.text = message
    }

}
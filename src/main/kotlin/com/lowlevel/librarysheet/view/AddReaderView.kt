package com.lowlevel.librarysheet.view

import com.lowlevel.librarysheet.controller.ReadersController
import javafx.event.EventHandler
import javafx.scene.control.*
import javafx.scene.layout.AnchorPane
import tornadofx.View

class AddReaderView: View() {

    override val root: AnchorPane by fxml("/addReaderView.fxml")
    val controller: ReadersController by inject()
    private val readerNameField: TextField by fxid("readerNameField")
    private val readerNumberField: TextField by fxid("readerNumberField")
    private val messageLabel: Label by fxid("messageLabel")
    private val okButton: Button by fxid("okButton")

    init {
        okButton.disableProperty().bind((readerNameField.textProperty().isEmpty)
            .or(readerNumberField.textProperty().isEmpty))
        okButton.onAction = EventHandler { okButtonAction() }
    }

    private fun okButtonAction() {
        messageLabel.text = controller.addReader(readerNameField.text, readerNumberField.text)
    }

}
package com.lowlevel.librarysheet.view

import com.lowlevel.librarysheet.service.impl.UserServiceImpl
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.*
import javafx.scene.layout.AnchorPane
import tornadofx.View
import tornadofx.getProperty

class AuthorizationView: View() {

    override val root: AnchorPane by fxml("/authorizationView.fxml")
    private val controller: UserServiceImpl by inject()
    private val loginLabel: Label by fxid("loginLabel")
    private val loginField: TextField by fxid("loginField")
    private val passwordField: PasswordField by fxid("passwordField")
    private val loginButton: Button by fxid("loginButton")
    private val signupLabel: Label by fxid("signupLabel")
    private val signupButton: Button by fxid("signupButton")

    private fun mainView() {
        replaceWith<ReadersView>(centerOnScreen = true, sizeToScene = true)
    }

    init {
        signupButton.disableProperty().bind((loginField.textProperty().isEmpty)
            .or(passwordField.textProperty().isEmpty))
        loginButton.disableProperty().bind((loginField.textProperty().isEmpty)
            .or(passwordField.textProperty().isEmpty))
        signupButton.onAction = EventHandler {event ->
            signupLabel.text = controller.signUp(loginField.text, passwordField.text)
        }
        loginButton.onAction = EventHandler {event ->
            val result = controller.logIn(loginField.text, passwordField.text)
            if (result == "Success") mainView()
            else loginLabel.text = result
        }
    }



}
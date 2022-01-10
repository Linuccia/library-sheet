package com.lowlevel.librarysheet.view

import javafx.stage.Stage
import tornadofx.App

class UIApp: App(AuthorizationView::class) {

    override fun start(stage: Stage) {
        super.start(stage)
        stage.isResizable = false
    }
}
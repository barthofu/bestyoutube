package fr.bgili.bestyoutube

import fr.bgili.bestyoutube.services.DatabaseService

class Application: android.app.Application() {

    val database by lazy { DatabaseService.getInstance(this) }
}
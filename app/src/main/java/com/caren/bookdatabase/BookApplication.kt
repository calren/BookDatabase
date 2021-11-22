package com.caren.bookdatabase

import android.app.Application
import com.caren.bookdatabase.database.AppDatabase

// Created and runs whenever the app first starts
class BookApplication : Application() {

    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}
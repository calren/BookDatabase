package com.caren.bookdatabase

import android.app.Application
import androidx.room.RoomDatabase

class BookApplication : Application() {

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database: BookDatabase by lazy { BookDatabase.getDatabase(this) }

}
package com.caren.bookdatabase.database

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface BookDao {

    @Insert
    suspend fun addBook(book: Book)
}
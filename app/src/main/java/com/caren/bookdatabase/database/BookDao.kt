package com.caren.bookdatabase.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("SELECT * FROM Book")
    fun getAllBooks(): Flow<List<Book>>

    @Query("SELECT * FROM Book ORDER BY id DESC LIMIT 1")
    suspend fun getMostRecentlyAddedBook(): Book

    @Insert
    suspend fun addBook(book: Book)

    @Delete
    suspend fun deleteBook(book: Book)


}
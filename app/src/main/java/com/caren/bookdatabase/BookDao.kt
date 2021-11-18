package com.caren.bookdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("SELECT * FROM book_table")
    fun getBooks(): Flow<List<Book>>


    @Query("SELECT * FROM book_table")
    suspend fun getBooksSlow(): List<Book>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(book: Book): Long

}
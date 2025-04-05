package com.bcit.myminiapp.data

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Entity(tableName = "excretion_table")
data class LocalExcretion(
    @PrimaryKey(autoGenerate = true) val eid: Int? = null,
    val date: String, // yyyy-mm-dd
    val time: String, // HH:mm
    val shape: String,
    val color: String,
    val quantity: String,
    val option: String?
)

@Dao
interface ExcretionDao {
    @Query("SELECT * FROM excretion_table")
    fun getAll(): List<LocalExcretion>

    @Insert
    fun add(excretion: LocalExcretion)
}

@Database(entities = [LocalExcretion:: class], version = 1)
abstract  class AppDatabase: RoomDatabase(){
    abstract fun excretionDao(): ExcretionDao
}

// Singleton pattern
object MyDatabase{
    fun getDatabase(context: Context) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "my_db")
            .allowMainThreadQueries()
            .build()
    }
}
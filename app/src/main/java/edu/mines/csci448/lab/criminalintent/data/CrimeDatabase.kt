package edu.mines.csci448.lab.criminalintent.data

import android.content.Context
import androidx.room.*

private const val DATABASE_NAME = "crime-database"

@Database(entities = [Crime :: class], version = 1)
@TypeConverters(CrimeTypeConverters :: class)


abstract class CrimeDatabase: RoomDatabase() {

    abstract fun crimeDao(): CrimeDao

    companion object{
        private var instance: CrimeDatabase? = null

        fun getInstance(context: Context): CrimeDatabase {
            return instance ?: let {
                instance ?: Room.databaseBuilder(context, CrimeDatabase :: class.java, DATABASE_NAME).build()
            }
        }
    }
}
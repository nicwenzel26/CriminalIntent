package edu.mines.csci448.lab.criminalintent.data

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

private const val DATABASE_NAME = "crime-database"

@Database(entities = [Crime :: class], version = 2)
@TypeConverters(CrimeTypeConverters :: class)


abstract class CrimeDatabase: RoomDatabase() {

    abstract fun crimeDao(): CrimeDao

    companion object{
        private var instance: CrimeDatabase? = null

        private val migration_1_2 = object : Migration(1,2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE crime ADD COLUMN suspect TEXT DEFAULT NULL")
                database.execSQL("ALTER TABLE crime ADD COLUMN suspectNumber TEXT DEFAULT NULL")
            }
        }

        fun getInstance(context: Context): CrimeDatabase {
            return instance ?: let {
                instance ?: Room.databaseBuilder(context, CrimeDatabase :: class.java, DATABASE_NAME).addMigrations(
                    migration_1_2).build()
            }
        }
    }
}
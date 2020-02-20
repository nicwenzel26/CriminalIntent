package edu.mines.csci448.lab.criminalintent.data

import android.content.Context
import androidx.lifecycle.LiveData
import java.util.*
import java.util.concurrent.Executors

class CrimeRepository(private val crimeDao: CrimeDao) {

    private val executor = Executors.newSingleThreadExecutor()


    fun getCrimes() : LiveData< List<Crime> > = crimeDao.getCrimes()

    fun getCrime(id : UUID): LiveData <Crime?> = crimeDao.getCrime(id)


    fun updateCrime(crime:Crime) {
        executor.execute{
            crimeDao.updateCrime(crime)
        }
    }

    fun addCrime(crime: Crime) {
        executor.execute{
            crimeDao.addCrime(crime)
        }
    }


    companion object {
        private var instance: CrimeRepository? = null

        fun getInstance(context: Context): CrimeRepository? {
            return instance ?: let {
                if (instance == null) {
                    val database = CrimeDatabase.getInstance(context)
                    instance = CrimeRepository(database.crimeDao())
                }
                return instance
            }
        }
    }
}
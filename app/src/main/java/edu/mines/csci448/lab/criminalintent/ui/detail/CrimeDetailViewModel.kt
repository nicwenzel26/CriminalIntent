package edu.mines.csci448.lab.criminalintent.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import edu.mines.csci448.lab.criminalintent.data.Crime
import edu.mines.csci448.lab.criminalintent.data.CrimeRepository
import java.util.*

class CrimeDetailViewModel(private val crimeRepository: CrimeRepository): ViewModel() {
    private val crimeIdLiveData = MutableLiveData<UUID>()

    var crimeLiveData: LiveData<Crime?> =
        Transformations.switchMap(crimeIdLiveData) { crimeId ->
            crimeRepository.getCrime(crimeId)
        }

    fun loadCrime(crimeId: UUID) {
        crimeIdLiveData.value = crimeId
    }

    fun saveCrime(crime: Crime){
        crimeRepository.updateCrime(crime)
    }

}
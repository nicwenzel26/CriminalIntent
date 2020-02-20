package edu.mines.csci448.lab.criminalintent.ui.list

import androidx.lifecycle.ViewModel
import edu.mines.csci448.lab.criminalintent.data.Crime
import edu.mines.csci448.lab.criminalintent.data.CrimeRepository

class CrimeListViewModel(private val crimeRepository: CrimeRepository) : ViewModel() {

    val crimesListLiveData = crimeRepository.getCrimes()
}



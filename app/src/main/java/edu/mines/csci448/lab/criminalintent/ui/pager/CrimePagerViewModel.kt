package edu.mines.csci448.lab.criminalintent.ui.pager

import androidx.lifecycle.ViewModel
import edu.mines.csci448.lab.criminalintent.data.CrimeRepository

class CrimePagerViewModel(private val crimeRepository: CrimeRepository): ViewModel() {
    val crimeListLiveData = crimeRepository.getCrimes()
}
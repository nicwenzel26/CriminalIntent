package edu.mines.csci448.lab.criminalintent.ui.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.mines.csci448.lab.criminalintent.data.CrimeRepository

class CrimeDetailViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CrimeRepository :: class.java).newInstance(CrimeRepository.getInstance(context))
    }
}
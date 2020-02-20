package edu.mines.csci448.lab.criminalintent.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import edu.mines.csci448.lab.criminalintent.R
import edu.mines.csci448.lab.criminalintent.ui.detail.CrimeDetailFragment
import edu.mines.csci448.lab.criminalintent.ui.list.CrimeListFragment
import edu.mines.csci448.lab.criminalintent.ui.list.CrimeListViewModel
import java.util.*

class MainActivity : AppCompatActivity(), CrimeListFragment.Callbacks {

    private val logTag = "448.MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(logTag, "onCreate() called")
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if(currentFragment == null) {
            val fragment = CrimeListFragment()
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
        }
    }

    override fun onCrimeSelected(crimeID: UUID) {
        val fragment = CrimeDetailFragment.newInstance(crimeID)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack(null).commit()
    }

    override fun onStart() {
        super.onStart()
        Log.d(logTag, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(logTag, "onResume() called")
    }

    override fun onPause() {
        Log.d(logTag, "onPause() called")
        super.onPause()
    }

    override fun onStop() {
        Log.d(logTag, "onStop() called")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(logTag, "onDestroy() called")
        super.onDestroy()
    }
}
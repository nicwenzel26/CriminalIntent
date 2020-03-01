package edu.mines.csci448.lab.criminalintent.ui.pager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import edu.mines.csci448.lab.criminalintent.data.Crime
import edu.mines.csci448.lab.criminalintent.ui.detail.CrimeDetailFragment

class CrimePagerAdapter(fragment: Fragment, private val crimes: List<Crime>) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return crimes.size
    }

    override fun createFragment(position: Int): Fragment {
        val crime = crimes[position]

        return CrimeDetailFragment.newInstance(crime.id)
    }
}
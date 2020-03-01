package edu.mines.csci448.lab.criminalintent.ui.pager

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import edu.mines.csci448.lab.criminalintent.R
import edu.mines.csci448.lab.criminalintent.data.Crime
import java.util.*

private const val ARG_CRIME_ID = "crime_id_pager"

class CrimePagerFragment : Fragment() {
    private val logTag = "448.PagerFrag"

    private lateinit var crimeViewPager: ViewPager2
    private lateinit var crimePagerViewModel: CrimePagerViewModel
    private lateinit var adapter: CrimePagerAdapter
    private lateinit var crimeID: UUID


    companion object {
        fun newInstance(crimeId: UUID) : CrimePagerFragment {
            val args = Bundle().apply {
                putSerializable(ARG_CRIME_ID, crimeId)
            }

            return CrimePagerFragment().apply {
                arguments = args
            }
        }
    }


    override fun onAttach(context: Context) {
        Log.d(logTag, "onAttachCalled()")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(logTag, "onCreate() called")
        super.onCreate(savedInstanceState)

        val factory = CrimePagerViewModelFactory(requireContext())
        crimePagerViewModel = ViewModelProvider(this, factory).get(CrimePagerViewModel::class.java)

        crimeID = arguments?.getSerializable(ARG_CRIME_ID) as UUID
    }

    private fun updateUI(crimes: List<Crime>) {
        adapter = CrimePagerAdapter(this, crimes)
        crimeViewPager.adapter = adapter

        crimes.forEachIndexed{ position, crime ->
            if(crime.id == crimeID) {
                crimeViewPager.currentItem = position
                return
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(logTag, "onCreateView() called")

        val view = inflater.inflate(R.layout.fragment_pager, container, false)

        crimeViewPager = view.findViewById(R.id.crime_view_page)

        updateUI(emptyList())

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(logTag, "onViewCreated() called")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(logTag, "onActivityCreated() ")
        super.onActivityCreated(savedInstanceState)

        crimePagerViewModel.crimeListLiveData.observe(
            viewLifecycleOwner,
            Observer { crimes ->
                crimes?.let {
                    Log.d(logTag, "Got ${crimes.size} crimes")
                    updateUI(crimes)
                }
            }
        )
    }

    override fun onStart() {
        Log.d(logTag, "onStart() called")
        super.onStart()
    }

    override fun onDestroy() {
        Log.d(logTag, "onDestroy() called")
        super.onDestroy()
    }

    override fun onResume() {
        Log.d(logTag, "onResume() called")
        super.onResume()
    }

    override fun onPause() {
        Log.d(logTag, "onPause() called")
        super.onPause()
    }

    override fun onDestroyView() {
        Log.d(logTag, "onDestroyView() called")
        super.onDestroyView()
    }

    override fun onStop() {
        Log.d(logTag, "onStop() called")
        super.onStop()
    }

    override fun onDetach() {
        Log.d(logTag, "onDetach() called")
        super.onDetach()
    }

}
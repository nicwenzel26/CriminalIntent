package edu.mines.csci448.lab.criminalintent.ui.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.mines.csci448.lab.criminalintent.R
import edu.mines.csci448.lab.criminalintent.data.Crime
import kotlinx.android.synthetic.main.list_item_crime.*
import java.util.*
import kotlin.math.log

class CrimeListFragment : Fragment() {

    interface Callbacks {
        fun onCrimeSelected(crimeID: UUID)
    }


    private var callbacks: Callbacks? = null

    private val logTag = "448.CLF"
    private lateinit var crimeListViewModel: CrimeListViewModel
    private lateinit var crimeRecyclerView: RecyclerView
    private lateinit var adapter: CrimeListAdapter

    override fun onAttach(context: Context) {
        Log.d(logTag, "onAttach() called")
        super.onAttach(context)

        callbacks = context as Callbacks?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(logTag, "onCreate() called")
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

        val factory = CrimeListViewModelFactory(requireContext())
        crimeListViewModel = ViewModelProvider(this, factory).get(CrimeListViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        Log.d(logTag, "onCreateOptionsMenu() called")
        inflater.inflate(R.menu.fragment_crime_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d(logTag, "onOptionsItemSelected() called")
        return when(item.itemId) {
            R.id.new_crime_menu_item -> {
                val crime = Crime()
                crimeListViewModel.addCrime(crime)
                callbacks?.onCrimeSelected(crime.id)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(logTag, "onCreateView() called")
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        crimeRecyclerView =
            view.findViewById(R.id.crime_recycler_view) as RecyclerView
        crimeRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI(emptyList())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        crimeListViewModel.crimesListLiveData.observe(
            viewLifecycleOwner,
            Observer { crimes ->
                crimes?.let {
                    Log.i(logTag, "Got crimes ${crimes.size}")
                    updateUI(crimes)
                }
            })
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(logTag, "onActivityCreated() called")
        super.onActivityCreated(savedInstanceState)
    }


    private fun updateUI(crimes: List<Crime>) {
        adapter = CrimeListAdapter(crimes) {crime: Crime -> Unit
            callbacks?.onCrimeSelected(crime.id)

        }
        crimeRecyclerView.adapter = adapter
    }


    override fun onStart() {
        Log.d(logTag, "onStart() called")
        super.onStart()
    }

    override fun onResume() {
        Log.d(logTag, "onResume() called")
        super.onResume()
    }

    override fun onPause() {
        Log.d(logTag, "onPause() called")
        super.onPause()
    }

    override fun onStop() {
        Log.d(logTag, "onStop() called")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d(logTag, "onDestroyView() called")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d(logTag, "onDestroy() called")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d(logTag, "onDetach() called ")
        super.onDetach()
        callbacks = null
    }


}
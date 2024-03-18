package com.example.dzhiadze.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dzhiadze.ActivityViewModel
import com.example.dzhiadze.App
import com.example.dzhiadze.adapters.HomeAdapter
import com.example.dzhiadze.Service
import com.example.dzhiadze.adapters.CinemasHomeAdapter
import com.example.dzhiadze.adapters.FavesAdapter
import com.example.dzhiadze.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HomeAdapter
    private val datamodel: ActivityViewModel by activityViewModels()
    private val service: Service
        get() = (context?.applicationContext as App).service
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        datamodel.filmCardState.value= View.VISIBLE
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        if (datamodel.reddy.value==false) {
            datamodel.reddy.observe(viewLifecycleOwner) { state ->
                if (state == true) {

                    loadRecycler()

                }
            }
        }
        else
        {
            loadRecycler()

        }


        return binding.root
    }

    private fun loadRecycler()
    {
        val controller = findNavController()

        adapter = HomeAdapter(controller, datamodel, service)
        adapter.movies = datamodel.movies.value!!

        val layoutManagerTomorrow =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerToday.layoutManager = layoutManagerTomorrow
        binding.progressBar.visibility=GONE
        binding.recyclerToday.adapter = adapter
        //////////////////////////
        val adapter2 = CinemasHomeAdapter()
        adapter2.cinemas = datamodel.movies.value!!
        val layoutManagerCinemas =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerCinemas.layoutManager = layoutManagerCinemas
        binding.recyclerCinemas.adapter = adapter2
    }




}
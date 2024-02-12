package com.example.dzhiadze.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dzhiadze.ActivityViewModel
import com.example.dzhiadze.App
import com.example.dzhiadze.R
import com.example.dzhiadze.adapters.FavesAdapter
import com.example.dzhiadze.Service
import com.example.dzhiadze.databinding.FragmentFavoritesBinding


class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private val datamodel: ActivityViewModel by activityViewModels()
    private lateinit var adapter: FavesAdapter
    private val service: Service
        get() = (context?.applicationContext as App).service

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        datamodel.filmCardState.value = VISIBLE


        val controller = findNavController()
        adapter = FavesAdapter(controller, datamodel, service)
        adapter.movies = datamodel.favesMovies.value!!
        val layoutManagerTomorrow =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerToday.layoutManager = layoutManagerTomorrow
        binding.recyclerToday.adapter = adapter
        return binding.root
    }
}
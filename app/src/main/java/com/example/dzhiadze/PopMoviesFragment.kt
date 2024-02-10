package com.example.dzhiadze

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dzhiadze.databinding.FragmentPopMoviesBinding
import com.example.dzhiadze.retrofit.RetrofitClass
import com.example.dzhiadze.retrofit.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PopMoviesFragment : Fragment() {
    private lateinit var binding: FragmentPopMoviesBinding
    private lateinit var adapter: MovieAdapter
    private val datamodel: ActivityViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopMoviesBinding.inflate(inflater, container, false)
        datamodel.filmCardState.value= View.VISIBLE
        val apiOb = RetrofitClass()
        adapter = MovieAdapter()
        lifecycleScope.launch(Dispatchers.IO) {
            val s =apiOb.getMoviesFromApi()
            launch(Dispatchers.Main) {
                adapter.movies=s.items
            }
        }
        val layoutManagerTomorrow =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerToday.layoutManager = layoutManagerTomorrow
        binding.recyclerToday.adapter = adapter
        return binding.root
    }


}
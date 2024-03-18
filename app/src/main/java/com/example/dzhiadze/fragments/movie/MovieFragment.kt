package com.example.dzhiadze.fragments.movie

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.dzhiadze.ActivityViewModel
import com.example.dzhiadze.databinding.FragmentMovieBinding
import com.example.dzhiadze.retrofit.RetrofitClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MovieFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding
    private val datamodel: ActivityViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMovieBinding.inflate(inflater, container, false)
        val movieViewModel =
            ViewModelProvider(this).get(MovieViewModel::class.java)
        val id = arguments?.getInt("id")
        datamodel.internetConection.value=hasConnection()
        datamodel.internetConection.observe(viewLifecycleOwner) { state ->
            if (state == true) {
                loadData(id!!)
            }
        }

        return binding.root
    }



    private fun loadData(id:Int) {
        val apiOb = RetrofitClass()
        var genres=""
        var countries=""
        var year=""

        lifecycleScope.launch(Dispatchers.IO) {

            val s = apiOb.getMovieByIdFromApi(id)

            launch(Dispatchers.Main) {
                Glide.with(binding.poster.context)
                    .load(s.posterUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.poster)
                binding.movieName.text=s.nameRu
                binding.description.text=s.description
                if (s.year!=0) {
                    year = s.year.toString()
                }else {
                    year = "Неизвестно"
                }
                binding.year.text=year
                if (s.filmLength!=0) {
                    binding.length.text=s.filmLength.toString()+ " минут"
                }else {
                    binding.length.text="Неизвестно"
                }


                if (s.countries.isNotEmpty()){
                    countries += s.countries[0].country
                    for (i in 1 until s.countries.size)
                        countries+=", " + s.countries[i].country
                }
                else
                {
                    countries = "Неизвестно"
                }
                binding.country.text=countries

                if (s.genres.isNotEmpty()){
                    genres += s.genres[0].genre
                    for (i in 1 until s.genres.size)
                        genres+=", " + s.genres[i].genre
                }
                else
                {
                    genres = "Неизвестно"
                }
                binding.genre.text=genres
                binding.movFr.visibility=VISIBLE
                binding.progressBar.visibility= GONE

            }

        }
    }
    private fun hasConnection(): Boolean {
        val cm: ConnectivityManager =
            requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var wifiInfo: NetworkInfo? = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        if (wifiInfo != null && wifiInfo.isConnected) {
            return true
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if (wifiInfo != null && wifiInfo.isConnected) {
            return true
        }
        wifiInfo = cm.activeNetworkInfo
        return wifiInfo != null && wifiInfo.isConnected
    }
}
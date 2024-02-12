package com.example.dzhiadze


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.dzhiadze.databinding.ActivityMainBinding
import com.example.dzhiadze.models.MovieModel
import com.example.dzhiadze.retrofit.RetrofitClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private val controller by lazy {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navHostFragment.navController
    }
    private val service: Service
        get() = (applicationContext as App).service
    private val datamodel: ActivityViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        datamodel.internetConection.value=hasConnection()


        datamodel.internetConection.observe(this) { state ->
            if (state == true) {
                binding.normalCont.visibility = VISIBLE
                binding.internetProbCont.visibility = GONE
                binding.topBar.setupWithNavController(controller)
                datamodel.filmCardState.observe(this) { state ->
                    binding.favButton.visibility = state
                    binding.popButton.visibility = state
                }
                if (datamodel.reddy.value==false and state){
                    loadData()
                }
                binding.favButton.setOnClickListener{
                    controller.navigate(R.id.favoritesFragment)
                }
                binding.popButton.setOnClickListener{
                    controller.navigate(R.id.popMoviesFragment)
                }
            }
            else{
                binding.normalCont.visibility = GONE
                binding.internetProbCont.visibility = VISIBLE
                binding.update.setOnClickListener{
                    val tmp=hasConnection()
                    if (tmp != datamodel.internetConection.value)
                        datamodel.internetConection.value=tmp
                }
            }
        }
    }



    private fun loadData(){
        val apiOb = RetrofitClass()
        val k = mutableListOf<Int>()
        var s = listOf<MovieModel>()
        val faves = mutableListOf<MovieModel>()
        lifecycleScope.launch(Dispatchers.IO) {
            for (i in 1..5) {
                s = s + apiOb.getMoviesFromApi(i)
            }
            val t = service.getFavsMoviesId()

            launch(Dispatchers.Main) {
                for (i in t)
                    k.add(i.movieId)
                for (i in 0 until s.size)
                    if (s[i].kinopoiskId in k) {
                        s[i].isFavs = VISIBLE
                        faves.add(s[i])
                    }
                datamodel.favesMovies.value = faves
                datamodel.movies.value = s.toMutableList()
                datamodel.reddy.value=true
            }
        }
    }

    private fun hasConnection(): Boolean {
        val cm: ConnectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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
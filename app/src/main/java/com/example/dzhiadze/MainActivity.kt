package com.example.dzhiadze


import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.dzhiadze.databinding.ActivityMainBinding
import com.example.dzhiadze.retrofit.RetrofitClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private val controller by lazy {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        navHostFragment.navController
    }

    private val datamodel: ActivityViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        datamodel.filmCardState.observe(this) { state ->
            binding.button3.visibility = state
            binding.button4.visibility = state
        }
        binding.button3.setOnClickListener{
            controller.navigate(R.id.favoritesFragment)

        }
        binding.button4.setOnClickListener{
            controller.navigate(R.id.popMoviesFragment)

        }
    }
}
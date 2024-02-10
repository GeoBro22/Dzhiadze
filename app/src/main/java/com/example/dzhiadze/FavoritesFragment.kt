package com.example.dzhiadze

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.dzhiadze.databinding.FragmentFavoritesBinding


class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private val datamodel: ActivityViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        datamodel.filmCardState.value= VISIBLE


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val controller =findNavController()
        binding.button3.setOnClickListener {
            controller.navigate(
                R.id.movieFragment
            )
            datamodel.filmCardState.value=INVISIBLE
        }
    }
}
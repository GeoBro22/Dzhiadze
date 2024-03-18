package com.example.dzhiadze.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.dzhiadze.R
import com.example.dzhiadze.databinding.CinemaCardBinding
import com.example.dzhiadze.databinding.MovieCardMainBinding
import com.example.dzhiadze.models.MovieModel
import com.example.dzhiadze.movies.room.entities.FavsDbEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CinemasHomeAdapter: RecyclerView.Adapter<CinemasHomeAdapter.CinemasHomeViewHolder>() {

    var cinemas: List<MovieModel> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }


    override fun getItemCount(): Int = cinemas.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemasHomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CinemaCardBinding.inflate(inflater, parent, false)
        return CinemasHomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CinemasHomeViewHolder, position: Int) {
        val movie = cinemas[position]

    }


    class CinemasHomeViewHolder(
        val binding: CinemaCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
    }
}
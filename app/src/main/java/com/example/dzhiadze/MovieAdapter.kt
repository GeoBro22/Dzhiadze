package com.example.dzhiadze

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dzhiadze.R
import com.example.dzhiadze.databinding.MovieCardBinding
import com.example.dzhiadze.retrofit.models.Movie
import com.example.dzhiadze.retrofit.models.Movies

class MovieAdapter() :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var movies: List<Movie> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }


    override fun getItemCount(): Int = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieCardBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        Log.d("AAAA", movie.toString())
        with(holder.binding) {
            mvname.text = movie.nameRu
            if (!movie.genres.isEmpty())
                genre.text = movie.genres[0].genre
            if (movie.posterUrl!="")
                Glide.with(movieImage.context).load(movie.posterUrl).into(movieImage)
            val bundle = Bundle()
//            holder.itemView.setOnClickListener {
//                navController.navigate(
//                    R.id.infoMovieFragment,
//                    bundle
//                )
//            }
        }

    }


    class MovieViewHolder(
        val binding: MovieCardBinding
    ) : RecyclerView.ViewHolder(binding.root)
}
package com.example.dzhiadze.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.dzhiadze.ActivityViewModel
import com.example.dzhiadze.R
import com.example.dzhiadze.Service
import com.example.dzhiadze.databinding.MovieCardFavesBinding
import com.example.dzhiadze.models.MovieModel
import com.example.dzhiadze.movies.room.entities.FavsDbEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavesAdapter(val controller: NavController, val datamodel: ActivityViewModel, val service: Service) :
    RecyclerView.Adapter<FavesAdapter.MovieViewHolder>() {

    var movies: MutableList<MovieModel> = mutableListOf()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }


    override fun getItemCount(): Int = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieCardFavesBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        with(holder.binding) {

            mvname.text = movie.nameRu
            if (!movie.genres.isEmpty())
                genre.text = movie.genres[0].genre.substring(0, 1).toUpperCase() + movie.genres[0].genre.substring(1)+" ("+movie.year+")"
            if (movie.posterUrlPreview!="")
                Glide.with(movieImage.context).load(movie.posterUrlPreview).transition(
                    DrawableTransitionOptions.withCrossFade()).into(movieImage)
            imageStar.visibility=movie.isFavs
            val bundle = Bundle()
            holder.itemView.setOnClickListener {
                bundle.putInt("id",movie.kinopoiskId)
                controller.navigate(
                    R.id.action_favoritesFragment_to_movieFragment,
                    bundle
                )
                datamodel.filmCardState.value= View.INVISIBLE
            }

            holder.itemView.setOnLongClickListener() {
                if (movie.isFavs== VISIBLE) {
                    movie.isFavs = INVISIBLE
                    GlobalScope.launch(Dispatchers.IO) { deleteFavesId(FavsDbEntity(movie.kinopoiskId)) }
                }
                else {
                    movie.isFavs = VISIBLE
                    GlobalScope.launch(Dispatchers.IO) { insertFavesId(FavsDbEntity(movie.kinopoiskId)) }
                }
                imageStar.visibility = movie.isFavs
                movies[position].isFavs = movie.isFavs
                for (i in 0 until (datamodel.movies.value!!.size)){
                    if (datamodel.movies.value?.get(i)!!.kinopoiskId == movies[position].kinopoiskId)
                        datamodel.movies.value?.get(i)!!.isFavs=movie.isFavs
                    break
                }
                movies.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, movies.size - position)
                true
            }
        }

    }


    class MovieViewHolder(
        val binding: MovieCardFavesBinding
    ) : RecyclerView.ViewHolder(binding.root)


    suspend fun insertFavesId(favesEntity: FavsDbEntity){
        service.insertFavsMoviesId(favesEntity)
    }
    suspend fun deleteFavesId(favesEntity: FavsDbEntity){
        service.deleteFavsMoviesId(favesEntity)
    }
}
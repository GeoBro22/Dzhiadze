package com.example.dzhiadze

import android.app.Activity
import android.view.View
import android.view.View.VISIBLE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dzhiadze.models.MovieModel
import com.example.dzhiadze.retrofit.RetrofitClass
import com.example.dzhiadze.retrofit.models.Movies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class ActivityViewModel : ViewModel() {
    val filmCardState:MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(VISIBLE)
    }

    val reddy:MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val internetConection:MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val movies:MutableLiveData<MutableList<MovieModel>> by lazy {
        MutableLiveData<MutableList<MovieModel>>()
    }
    val favesMovies:MutableLiveData<MutableList<MovieModel>> by lazy {
        MutableLiveData<MutableList<MovieModel>>()
    }
}
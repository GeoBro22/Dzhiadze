package com.example.dzhiadze

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class ActivityViewModel : ViewModel() {
    val filmCardState:MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    public fun setFilmCardState(state: Int) {
        filmCardState.value=state
    }
    public fun getFilmCardState(): LiveData<Int>{
        return filmCardState
    }

}
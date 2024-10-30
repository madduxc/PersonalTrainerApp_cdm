package com.workoutpage

import android.content.ClipData
import android.media.RouteListingPreference
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExerciseCardViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val mutableSelectedItem = MutableLiveData<RouteListingPreference.Item>()
    val SelectedItem: LiveData<RouteListingPreference.Item> get() = mutableSelectedItem

    fun selectedItem(item: RouteListingPreference.Item) {
        mutableSelectedItem.value = item
    }
}
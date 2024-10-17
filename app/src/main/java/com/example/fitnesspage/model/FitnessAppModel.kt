package com.example.fitnesspage.model

import androidx.lifecycle.ViewModel
import com.example.fitnesspage.data.DataSource
import com.example.fitnesspage.ui.theme.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

// ViewModel class manages UI state of the App
// in particular, it holds data that is relevant to the UI but should not be lost during configuration changes.
class FitnessAppModel : ViewModel() {
    // MutableStateFlow is a type of Flow that holds a mutable value, and
    // In this case, it holds an instance of UiState and only the ViewModel can modify the state
    private val _uiState = MutableStateFlow(UiState())
    // This is the read-only version of _uiState and no other ui components can modify it directly
    // asStateFlow() converts the mutable state into an immutable StateFlow.
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        initializeUiState()
    }
    //  Sets the initial state of _uiState.
    //  and updates _uiState.value to the new instance of UiState,
    //  with the categories property populated from DataSource.listOfCategories
    private fun initializeUiState() {
        _uiState.value = UiState(
            categories = DataSource.listOfCategories
        )
    }
}
//    fun updateCurrentCategory(category: Category) {
//        _uiState.update {
//            it.copy(
//                currentCategory = category
//            )
//        }
//    }
//
//
//    fun updateCurrentRecommendation(recommendation: Recommendation?) {
//        _uiState.update {
//            it.copy(
//                currentRecommendation = recommendation
//            )
//        }
//    }
//}

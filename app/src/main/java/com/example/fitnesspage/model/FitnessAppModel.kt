package com.example.fitnesspage.model

import androidx.lifecycle.ViewModel
import com.example.fitnesspage.data.DataSource
import com.example.fitnesspage.ui.theme.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class FitnessAppModel : ViewModel(){
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init{
        initializeUiState()
    }
    private fun initializeUiState(){
        _uiState.value = UiState(
            categories = DataSource.listOfCategories
        )
    }

    fun updateCurrentCategory(category: Category) {
        _uiState.update {
            it.copy(
                currentCategory = category
            )
        }
    }


    fun updateCurrentRecommendation(recommendation: Recommendation?) {
        _uiState.update {
            it.copy(
                currentRecommendation = recommendation
            )
        }
    }
}

//data class UiState (
//    val categories: List<Category> = DataSource.listOfCategories,
//    val currentCategory: Category? = null,
//    val currentRecommendation: Recommendation? = null,
//)
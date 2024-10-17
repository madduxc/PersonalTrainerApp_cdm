package com.example.fitnesspage.ui.theme

import com.example.fitnesspage.data.DataSource
import com.example.fitnesspage.model.Category
import com.example.fitnesspage.model.Recommendation

data class UiState (
    val categories: List<Category> = DataSource.listOfCategories,
    val currentCategory: Category? = null,
    val currentRecommendation: Recommendation? = null,
)
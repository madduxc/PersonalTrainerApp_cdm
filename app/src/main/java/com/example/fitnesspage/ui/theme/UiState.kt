package com.example.fitnesspage.ui.theme

import com.example.fitnesspage.data.DataSource
import com.example.fitnesspage.model.Category
import com.example.fitnesspage.model.Recommendation

// used to represent the UI State -- mostly serves as an example and will need incorporate database
data class UiState (
    // list of categories which gets data data source
    val categories: List<Category> = DataSource.listOfCategories,
    // current category is non-null variable (i.e. "?" initialized to null until otherwise
    val currentCategory: Category? = null,
    // same as category
    val currentRecommendation: Recommendation? = null,
)
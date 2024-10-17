package com.example.fitnesspage.model

import androidx.annotation.StringRes

data class Category(
    @StringRes val name: Int,
    val list: List<Recommendation>
)

//data class Category(val name: String)

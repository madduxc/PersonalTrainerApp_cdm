package com.example.fitnesspage.model

import androidx.annotation.StringRes

// Category data class will represent fitness categories as objects
// with a name -- string resource id
// and a list of associated recommendations -- look at recommendation class
data class Category(
    // takes in string resource ids -- needs to be change to fit database
    @StringRes val name: Int,
    // This is a list of Recommendation objects and each category
    // can have multiple recommendations
    val list: List<Recommendation>
)



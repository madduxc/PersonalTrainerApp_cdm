package com.example.fitnesspage.data

import com.example.fitnesspage.model.Category
import com.example.fitnesspage.model.Recommendation
import com.example.fitnesspage.R

object DataSource {
    private val cardioCategory = Category(
        name = R.string.category_cardio,
        list = listOf(
            Recommendation(name = R.string.recommendation_run),
            Recommendation(name = R.string.recommendation_jump_rope),
            Recommendation(name = R.string.recommendation_swimming),
            Recommendation(name = R.string.recommendation_cycling),
            Recommendation(name = R.string.recommendation_hiking)
        )
    )

    private val strengthCategory = Category(
        name = R.string.category_strength,
        list = listOf(
            Recommendation(name = R.string.recommendation_bench_press),
            Recommendation(name = R.string.recommendation_squat),
            Recommendation(name = R.string.recommendation_deadlift),
            Recommendation(name = R.string.recommendation_overhead_press),
            Recommendation(name = R.string.recommendation_pull_up)
        )
    )

    private val flexibilityCategory = Category(
        name = R.string.category_flexibility,
        list = listOf(
            Recommendation(name = R.string.recommendation_yoga),
            Recommendation(name = R.string.recommendation_stretching),
            Recommendation(name = R.string.recommendation_dynamic_stretching),
            Recommendation(name = R.string.recommendation_static_stretching),
            Recommendation(name = R.string.recommendation_pilates)
        )
    )

    // Combine all categories into a list
    val listOfCategories = listOf(cardioCategory, strengthCategory, flexibilityCategory)
}

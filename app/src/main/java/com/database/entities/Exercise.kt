package com.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Exercise(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val targetMuscleGroup: String,
    val difficultyLevel: String,
    val numberOfSets: Int? = null, // default number of sets and reps to be displayed in the UI
    val numberOfReps: Int? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)




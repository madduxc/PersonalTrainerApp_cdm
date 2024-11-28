package com.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Exercise(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val targetMuscleGroup: String,
    val difficultyLevel: String,
    val numberOfSets: Int? = null,  // default number of sets and reps to be displayed in the UI
    val numberOfReps: Int? = null,
    val speed: Float? = null,         // optional default speed attribute, used for exercises like running or cycling
    val distance: Float? = null,      // optional default distance attribute
    val weight: Float? = null,        // optional default weight attribute
    val time: Float? = null,          // optional default time attribute
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)




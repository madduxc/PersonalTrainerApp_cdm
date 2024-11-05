package com.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = WorkoutExercise::class,
            parentColumns = ["id"],
            childColumns = ["workoutExerciseId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("workoutExerciseId")]
)
data class WorkoutSet(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val workoutExerciseId: Int,
    val reps: Int,
    val weight: Float,
    val time: Int,
    val intensity: String,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

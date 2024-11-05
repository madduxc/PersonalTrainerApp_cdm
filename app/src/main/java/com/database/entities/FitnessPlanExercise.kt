package com.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    primaryKeys = ["fitnessPlanId", "exerciseId"],
    foreignKeys = [
        ForeignKey(
            entity = FitnessPlan::class,
            parentColumns = ["id"],
            childColumns = ["fitnessPlanId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Exercise::class,
            parentColumns = ["id"],
            childColumns = ["exerciseId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("fitnessPlanId"), Index("exerciseId")]
)
data class FitnessPlanExercise(
    val fitnessPlanId: Int,
    val exerciseId: Int,
    val createdAt: Long = System.currentTimeMillis()
)

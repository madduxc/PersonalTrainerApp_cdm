package com.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = FitnessPlan::class,
            parentColumns = ["id"],
            childColumns = ["fitnessPlanId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("userId"), Index("fitnessPlanId")]
)
data class Workout(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val fitnessPlanId: Int?,
    val date: Long,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)


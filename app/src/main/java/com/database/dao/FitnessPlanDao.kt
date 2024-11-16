package com.database.dao

import androidx.room.Dao
import androidx.room.*
import com.database.entities.FitnessPlan

@Dao
interface FitnessPlanDao {
    // Insert a new fitness plan
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFitnessPlan(fitnessPlan: FitnessPlan): Long

    // Update an existing fitness plan
    @Update
    suspend fun updateFitnessPlan(fitnessPlan: FitnessPlan)

    // Delete a fitness plan
    @Delete
    suspend fun deleteFitnessPlan(fitnessPlan: FitnessPlan)

    // Get all fitness plans for a user
    @Query("SELECT * FROM FitnessPlan WHERE userId = :userId")
    suspend fun getFitnessPlansByUserId(userId: Int): List<FitnessPlan>

    // Get a fitness plan by ID
    @Query("SELECT * FROM FitnessPlan WHERE id = :fitnessPlanId LIMIT 1")
    suspend fun getFitnessPlanById(fitnessPlanId: Int): FitnessPlan?
}
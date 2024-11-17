package com.database.dao

import androidx.room.Dao
import androidx.room.*
import com.database.entities.FitnessPlanExercise


@Dao
interface FitnessPlanExerciseDao {

    // Insert a new fitness plan exercise
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFitnessPlanExercise(fitnessPlanExercise: FitnessPlanExercise)

    // Insert a list of fitness plan exercises
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFitnessPlanExerciseList(fitnessPlanExercises: List<FitnessPlanExercise>)

    // Delete a fitness plan exercise by fitness plan ID and exercise ID
    @Query("DELETE FROM FitnessPlanExercise WHERE fitnessPlanId = :fitnessPlanId AND exerciseId = :exerciseId")
    suspend fun deleteFitnessPlanExercise(fitnessPlanId: Int, exerciseId: Int)

    // Get all exercises for a fitness plan
    @Query("SELECT * FROM FitnessPlanExercise WHERE fitnessPlanId = :fitnessPlanId")
    suspend fun getExercisesByFitnessPlan(fitnessPlanId: Int): List<FitnessPlanExercise>
}
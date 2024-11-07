package com.database.dao
import androidx.room.*
import com.database.entities.Exercise
import com.database.entities.FitnessPlan
import com.database.entities.FitnessPlanExercise
import com.database.entities.Profile

@Dao
interface ProfileDao {

    // Insert a new Profile linked to a user
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createProfile(profile: Profile): Long

    // Insert a new Fitness Plan linked to a user
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createFitnessPlan(fitnessPlan: FitnessPlan): Long

    // Insert exercises into FitnessPlanExercise junction table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addExercisesToFitnessPlan(fitnessPlanExercises: List<FitnessPlanExercise>)

    // Insert a single exercise into the FitnessPlanExercise junction table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSingleExerciseToFitnessPlan(fitnessPlanExercise: FitnessPlanExercise)

    // Delete a single exercise from the FitnessPlanExercise junction table
    @Query("DELETE FROM FitnessPlanExercise WHERE fitnessPlanId = :fitnessPlanId AND exerciseId = :exerciseId")
    suspend fun deleteExerciseFromFitnessPlan(fitnessPlanId: Int, exerciseId: Int)

    // Retrieve user profile data by user ID
    @Query("SELECT * FROM Profile WHERE userId = :userId LIMIT 1")
    suspend fun getUserProfile(userId: Int): Profile?

    // Retrieve a fitness plan for a user
    @Query("SELECT * FROM FitnessPlan WHERE userId = :userId LIMIT 1")
    suspend fun getFitnessPlanByUserId(userId: Int): FitnessPlan?

    // Retrieve exercises associated with a fitness plan
    @Transaction
    @Query("""
        SELECT * FROM Exercise 
        INNER JOIN FitnessPlanExercise ON Exercise.id = FitnessPlanExercise.exerciseId
        WHERE FitnessPlanExercise.fitnessPlanId = :fitnessPlanId
    """)
    suspend fun getExercisesForFitnessPlan(fitnessPlanId: Int): List<Exercise>
}

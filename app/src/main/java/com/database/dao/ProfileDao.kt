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

    // Retrieve user profile data by user ID
    @Query("SELECT * FROM Profile WHERE userId = :userId LIMIT 1")
    suspend fun getUserProfile(userId: Int): Profile?

    // Update user profile data
    @Update
    suspend fun updateProfile(profile: Profile)

    // Delete a user profile
    @Delete
    suspend fun deleteProfile(profile: Profile)
}

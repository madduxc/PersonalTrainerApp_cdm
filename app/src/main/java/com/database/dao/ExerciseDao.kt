package com.database.dao

import androidx.room.*
import com.database.entities.Exercise
import com.database.entities.FitnessPlan
import com.database.entities.FitnessPlanExercise
import com.database.entities.Profile
@Dao
interface ExerciseDao {
    // insert a list of exercises into the database (used on initial app load)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(exercises: List<Exercise>)
}
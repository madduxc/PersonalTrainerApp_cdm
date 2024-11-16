package com.database.dao

import androidx.room.*
import com.database.entities.Workout
import com.database.entities.WorkoutExercise
import com.database.entities.WorkoutSet

@Dao
interface WorkoutDao {

    // Start a new workout for a user
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun startWorkout(workout: Workout): Long

    // Insert exercises into the WorkoutExercise table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addExercisesToWorkout(workoutExercises: List<WorkoutExercise>)

    // Log each set for an exercise in the Set table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun logSet(set: WorkoutSet): Long

    // Retrieve workout exercises by workout ID
    @Transaction
    @Query("""
        SELECT * FROM WorkoutExercise
        WHERE WorkoutExercise.workoutId = :workoutId
    """)
    suspend fun getExercisesForWorkout(workoutId: Int): List<WorkoutExercise>

    // Update an existing WorkoutSet
    @Update
    suspend fun updateSet(set: WorkoutSet)

    // Delete a WorkoutSet
    @Delete
    suspend fun deleteSet(set: WorkoutSet)

    // Retrieve sets for a workout exercise
    @Query("SELECT * FROM WorkoutSet WHERE workoutExerciseId = :workoutExerciseId")
    suspend fun getSetsForWorkoutExercise(workoutExerciseId: Int): List<WorkoutSet>

    // Get all sets for a specific exercise by joining WorkoutSet and WorkoutExercise
    @Transaction
    @Query("""
        SELECT * FROM WorkoutSet
        INNER JOIN WorkoutExercise ON WorkoutSet.workoutExerciseId = WorkoutExercise.id
        WHERE WorkoutExercise.exerciseId = :exerciseId
    """)
    suspend fun getAllSetsForExercise(exerciseId: Int): List<WorkoutSet>
}

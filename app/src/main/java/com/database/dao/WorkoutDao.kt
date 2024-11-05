package com.database.dao

import androidx.room.*
import com.database.entities.Workout
import com.database.entities.WorkoutExercise
import com.database.entities.WorkoutSet

@Dao
interface WorkoutDao {

    // Insert a new workout for a user
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun startWorkout(workout: Workout): Long

    // Insert exercises into the WorkoutExercise table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addExercisesToWorkout(workoutExercises: List<WorkoutExercise>)

    // Log each set for an exercise in the Set table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun logSet(set: WorkoutSet): Long

    // Retrieve workout exercises by workout ID (to display during a workout)
    @Transaction
    @Query("""
        SELECT * FROM WorkoutExercise
        INNER JOIN Exercise ON WorkoutExercise.exerciseId = Exercise.id
        WHERE WorkoutExercise.workoutId = :workoutId
    """)
    suspend fun getExercisesForWorkout(workoutId: Int): List<WorkoutExercise>

    // Retrieve sets for a workout exercise
    @Query("SELECT * FROM WorkoutSet WHERE workoutExerciseId = :workoutExerciseId")
    suspend fun getSetsForWorkoutExercise(workoutExerciseId: Int): List<WorkoutSet>
}

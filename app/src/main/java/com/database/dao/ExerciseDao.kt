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

    // insert a single exercise into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(exercise: Exercise)

    // update an existing exercise in the database
    @Update
    suspend fun update(exercise: Exercise)

    // delete an exercise from the database
    @Delete
    suspend fun delete(exercise: Exercise)

    // get all exercises from the database
    @Query("SELECT * FROM Exercise")
    suspend fun getAllExercises(): List<Exercise>

    // get a single exercise by its id
    @Query("SELECT * FROM Exercise WHERE id = :exerciseId LIMIT 1")
    suspend fun getExerciseById(exerciseId: Int): Exercise?

    // get a single exercise by its name
    @Query("SELECT * FROM Exercise WHERE name LIKE :name LIMIT 1")
    suspend fun getExerciseByName(name: String): Exercise?

    // get a list of exercises by their target muscle group
    @Query("SELECT * FROM Exercise WHERE targetMuscleGroup LIKE :targetMuscleGroup")
    suspend fun getExercisesByTargetMuscleGroup(targetMuscleGroup: String): List<Exercise>

    // get a list of exercises by their difficulty level
    @Query("SELECT * FROM Exercise WHERE difficultyLevel LIKE :difficultyLevel")
    suspend fun getExercisesByDifficultyLevel(difficultyLevel: String): List<Exercise>

    // get a list of exercises by their target muscle group AND difficulty level
    @Query("SELECT * FROM Exercise WHERE targetMuscleGroup LIKE :targetMuscleGroup AND difficultyLevel LIKE :difficultyLevel")
    suspend fun getExercisesByTargetMuscleAndDifficulty(targetMuscleGroup: String, difficultyLevel: String): List<Exercise>

}
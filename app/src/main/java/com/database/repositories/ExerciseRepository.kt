package com.database.repositories

import com.database.dao.ExerciseDao
import com.database.entities.Exercise

class ExerciseRepository(private val exerciseDao: ExerciseDao) {

    // Insert a list of exercises into the database
    suspend fun insertAll(exercises: List<Exercise>) {
        exerciseDao.insertAll(exercises)
    }

    // Insert a single exercise into the database
    suspend fun insert(exercise: Exercise) {
        exerciseDao.insert(exercise)
    }

    // Update an existing exercise in the database
    suspend fun update(exercise: Exercise) {
        exerciseDao.update(exercise)
    }

    // Delete an exercise from the database
    suspend fun delete(exercise: Exercise) {
        exerciseDao.delete(exercise)
    }

    // Get all exercises from the database
    suspend fun getAllExercises(): List<Exercise> {
        return exerciseDao.getAllExercises()
    }

    // Get a single exercise by its ID
    suspend fun getExerciseById(exerciseId: Int): Exercise? {
        return exerciseDao.getExerciseById(exerciseId)
    }

    // Get a single exercise by its name
    suspend fun getExerciseByName(name: String): Exercise? {
        return exerciseDao.getExerciseByName(name)
    }

    // Get exercises by target muscle group
    suspend fun getExercisesByTargetMuscleGroup(targetMuscleGroup: String): List<Exercise> {
        return exerciseDao.getExercisesByTargetMuscleGroup(targetMuscleGroup)
    }

    // Get exercises by difficulty level
    suspend fun getExercisesByDifficultyLevel(difficultyLevel: String): List<Exercise> {
        return exerciseDao.getExercisesByDifficultyLevel(difficultyLevel)
    }

    // Get exercises by target muscle group and difficulty level
    suspend fun getExercisesByTargetMuscleAndDifficulty(targetMuscleGroup: String, difficultyLevel: String): List<Exercise> {
        return exerciseDao.getExercisesByTargetMuscleAndDifficulty(targetMuscleGroup, difficultyLevel)
    }
}

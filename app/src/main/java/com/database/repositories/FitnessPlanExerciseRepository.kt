package com.database.repositories

import com.database.dao.FitnessPlanExerciseDao
import com.database.entities.FitnessPlanExercise

class FitnessPlanExerciseRepository(
    private val fitnessPlanExerciseDao: FitnessPlanExerciseDao
) {

    // Insert a new fitness plan exercise
    suspend fun insertFitnessPlanExercise(fitnessPlanExercise: FitnessPlanExercise) {
        fitnessPlanExerciseDao.insertFitnessPlanExercise(fitnessPlanExercise)
    }

    // Insert a list of exercises into a fitness plan
    suspend fun insertExercisesToFitnessPlan(fitnessPlanId: Int, exercises: List<Int>) {
        val fitnessPlanExercises = exercises.map { exerciseId ->
            FitnessPlanExercise(fitnessPlanId = fitnessPlanId, exerciseId = exerciseId)
        }
        fitnessPlanExerciseDao.insertFitnessPlanExerciseList(fitnessPlanExercises)
    }

    // Delete a fitness plan exercise by fitness plan ID and exercise ID
    suspend fun deleteFitnessPlanExercise(fitnessPlanId: Int, exerciseId: Int) {
        fitnessPlanExerciseDao.deleteFitnessPlanExercise(fitnessPlanId, exerciseId)
    }

    // Get all exercises for a fitness plan
    suspend fun getExercisesByFitnessPlan(fitnessPlanId: Int): List<FitnessPlanExercise> {
        return fitnessPlanExerciseDao.getExercisesByFitnessPlan(fitnessPlanId)
    }
}

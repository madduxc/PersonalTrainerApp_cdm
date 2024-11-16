package com.database.repositories

import com.database.dao.WorkoutDao
import com.database.entities.FitnessPlanExercise
import com.database.entities.Workout
import com.database.entities.WorkoutExercise
import com.database.entities.WorkoutSet

class WorkoutRepository(private val workoutDao: WorkoutDao) {

    // Create a new workout
    suspend fun startWorkout(workout: Workout): Long {
        return workoutDao.startWorkout(workout)
    }

    // Take a list of Fitness Plan Exercises and associate them with a workout
    // Creates WorkoutExercises from FitnessPlanExercises and adds them to the database
    suspend fun createWorkoutExercisesFromFitnessPlan(fitnessPlanExercises: List<FitnessPlanExercise>, workoutId: Int) {
        val workoutExercises = fitnessPlanExercises.map { fitnessPlanExercise ->
            WorkoutExercise(
                workoutId = workoutId,
                exerciseId = fitnessPlanExercise.exerciseId
            )
        }
        workoutDao.addExercisesToWorkout(workoutExercises)
    }

    // Return list of all workout exercises associated with a workout
    suspend fun getWorkoutExercisesForWorkout(workoutId: Int): List<WorkoutExercise> {
        return workoutDao.getExercisesForWorkout(workoutId)
    }

    // Log a set of a workout exercise
    suspend fun logSet(set: WorkoutSet): Long {
        return workoutDao.logSet(set)
    }

    // Update an existing WorkoutSet
    suspend fun updateSet(set: WorkoutSet) {
        workoutDao.updateSet(set)  // Call DAO to update the set
    }

    // Delete a specific WorkoutSet
    suspend fun deleteSet(set: WorkoutSet) {
        workoutDao.deleteSet(set)  // Call DAO to delete the set
    }

    // Get all sets associated with a workout exercise
    // used for stats and workout history, each entry is a set of a workout exercise
    // each set contains # reps, weight, time?, intensity
    suspend fun getSetsForWorkoutExercise(workoutExerciseId: Int): List<WorkoutSet> {
        return workoutDao.getSetsForWorkoutExercise(workoutExerciseId)
    }


}

package com.database.repositories

import com.database.dao.WorkoutDao
import com.database.entities.Workout
import com.database.entities.WorkoutExercise
import com.database.entities.WorkoutSet

class WorkoutRepository(private val workoutDao: WorkoutDao) {

    suspend fun startWorkout(workout: Workout): Long {
        return workoutDao.startWorkout(workout)
    }

    suspend fun addExercisesToWorkout(workoutExercises: List<WorkoutExercise>) {
        workoutDao.addExercisesToWorkout(workoutExercises)
    }

    suspend fun logSet(set: WorkoutSet): Long {
        return workoutDao.logSet(set)
    }

    suspend fun getExercisesForWorkout(workoutId: Int): List<WorkoutExercise> {
        return workoutDao.getExercisesForWorkout(workoutId)
    }

    suspend fun getSetsForWorkoutExercise(workoutExerciseId: Int): List<WorkoutSet> {
        return workoutDao.getSetsForWorkoutExercise(workoutExerciseId)
    }
}

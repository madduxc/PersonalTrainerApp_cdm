package com.database

import androidx.room.Database
import androidx.room.RoomDatabase

import com.database.dao.ExerciseDao
import com.database.dao.UserDao
import com.database.dao.ProfileDao
import com.database.dao.WorkoutDao

import com.database.entities.User
import com.database.entities.Profile
import com.database.entities.FitnessPlan
import com.database.entities.Exercise
import com.database.entities.FitnessPlanExercise
import com.database.entities.Workout
import com.database.entities.WorkoutExercise
import com.database.entities.WorkoutSet

@Database(
    entities = [
        User::class, Profile::class, FitnessPlan::class,
        Exercise::class, FitnessPlanExercise::class, Workout::class,
        WorkoutExercise::class, WorkoutSet::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun profileDao(): ProfileDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun exerciseDao(): ExerciseDao
}

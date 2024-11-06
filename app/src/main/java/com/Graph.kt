package com

import android.content.Context
import androidx.room.Room
import com.database.AppDatabase
import com.database.repositories.ProfileRepository
import com.database.repositories.UserRepository
import com.database.repositories.WorkoutRepository

// singleton database class container to be used throughout the entire application
object Graph {
    lateinit var database: AppDatabase

    // lazy initialization of the repositories DAO wrappers
    // to be used in the view models throughout the application
    val profileRepository by lazy{
        ProfileRepository(profileDao = database.profileDao())
    }

    val userRepository by lazy{
        UserRepository(userDao = database.userDao())
    }

    val workoutRepository by lazy{
        WorkoutRepository(workoutDao = database.workoutDao())
    }

    // create an instance of the sqlite room app database
    // should be called from the application class on startup
    fun provide(context: Context){
        database = Room.databaseBuilder(context, AppDatabase::class.java, "personal-trainer.db").build()
    }

}
package com.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Exercise(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val equipmentType: String,
    val targetMuscleGroup: String,
    val difficultyLevel: String,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

// Please initialize the database with these exercises
// Note* : I think we can remove equipmentType

//val predefinedExercises = listOf(
//    // Upper Body - Chest
//    Exercise(name = "Push-ups", targetMuscleGroup = "UPPER", difficultyLevel = "BEGINNER"),
//    Exercise(name = "Bench Press", targetMuscleGroup = "UPPER", difficultyLevel = "INTERMEDIATE"),
//    Exercise(name = "Chest Fly", targetMuscleGroup = "UPPER", difficultyLevel = "INTERMEDIATE"),
//    Exercise(name = "Dumbbell Press", targetMuscleGroup = "UPPER", difficultyLevel = "INTERMEDIATE"),
//    Exercise(name = "Chest Dips", targetMuscleGroup = "UPPER", difficultyLevel = "BEGINNER"),
//
//    // UPPER - Back
//    Exercise(name = "Pull-ups", targetMuscleGroup = "UPPER", difficultyLevel = "BEGINNER"),
//    Exercise(name = "Rows", targetMuscleGroup = "UPPER", difficultyLevel = "INTERMEDIATE"),
//    Exercise(name = "Lat Pulldowns", targetMuscleGroup = "UPPER", difficultyLevel = "BEGINNER"),
//    Exercise(name = "Deadlifts", targetMuscleGroup = "UPPER", difficultyLevel = "ADVANCE"),
//    Exercise(name = "Back Extensions", targetMuscleGroup = "UPPER", difficultyLevel = "BEGINNER"),
//
//    // UPPER - Shoulders
//    Exercise(name = "Shoulder Press", targetMuscleGroup = "UPPER", difficultyLevel = "INTERMEDIATE"),
//    Exercise(name = "Lateral Raises", targetMuscleGroup = "UPPER", difficultyLevel = "BEGINNER"),
//    Exercise(name = "Front Raises", targetMuscleGroup = "UPPER", difficultyLevel = "BEGINNER"),
//    Exercise(name = "Face Pulls", targetMuscleGroup = "UPPER", difficultyLevel = "INTERMEDIATE"),
//    Exercise(name = "Shrugs", targetMuscleGroup = "UPPER", difficultyLevel = "BEGINNER"),
//
//    // UPPER - Arms - Biceps
//    Exercise(name = "Bicep Curls", targetMuscleGroup = "UPPER", difficultyLevel = "BEGINNER"),
//    Exercise(name = "Hammer Curls", targetMuscleGroup = "UPPER", difficultyLevel = "BEGINNER"),
//    Exercise(name = "Chin-ups", targetMuscleGroup = "UPPER", difficultyLevel = "ADVANCE"),
//
//    // UPPER - Arms - Triceps
//    Exercise(name = "Tricep Dips", targetMuscleGroup = "UPPER", difficultyLevel = "INTERMEDIATE"),
//    Exercise(name = "Tricep Pushdowns", targetMuscleGroup = "UPPER", difficultyLevel = "BEGINNER"),
//    Exercise(name = "Overhead Tricep Extensions", targetMuscleGroup = "UPPER", difficultyLevel = "INTERMEDIATE"),
//    Exercise(name = "Close-Grip Bench Press", targetMuscleGroup = "UPPER", difficultyLevel = "INTERMEDIATE"),
//
//    // Core
//    Exercise(name = "Crunches", targetMuscleGroup = "UPPER", difficultyLevel = "BEGINNER"),
//    Exercise(name = "Planks", targetMuscleGroup = "UPPER", difficultyLevel = "BEGINNER"),
//    Exercise(name = "Russian Twists", targetMuscleGroup = "UPPER", difficultyLevel = "INTERMEDIATE"),
//    Exercise(name = "Leg Raises", targetMuscleGroup = "UPPER", difficultyLevel = "INTERMEDIATE"),
//    Exercise(name = "Bicycle Crunches", targetMuscleGroup = "UPPER", difficultyLevel = "INTERMEDIATE"),
//    Exercise(name = "Mountain Climbers", targetMuscleGroup = "UPPER", difficultyLevel = "INTERMEDIATE"),
//    Exercise(name = "Hanging Leg Raises", targetMuscleGroup = "UPPER", difficultyLevel = "ADVANCE"),
//
//    // LOWER - Legs - Quadriceps
//    Exercise(name = "Squats", targetMuscleGroup = "LOWER", difficultyLevel = "BEGINNER"),
//    Exercise(name = "Lunges", targetMuscleGroup = "LOWER", difficultyLevel = "BEGINNER"),
//    Exercise(name = "Leg Press", targetMuscleGroup = "LOWER", difficultyLevel = "INTERMEDIATE"),
//    Exercise(name = "Leg Extensions", targetMuscleGroup = "LOWER", difficultyLevel = "BEGINNER"),
//
//    // LOWER - Legs - Hamstrings
//    Exercise(name = "Romanian Deadlifts", targetMuscleGroup = "LOWER", difficultyLevel = "INTERMEDIATE"),
//    Exercise(name = "Leg Curls", targetMuscleGroup = "LOWER", difficultyLevel = "BEGINNER"),
//    Exercise(name = "Glute Bridges", targetMuscleGroup = "LOWER", difficultyLevel = "BEGINNER"),
//
//    // LOWER - Glutes
//    Exercise(name = "Hip Thrusts", targetMuscleGroup = "LOWER", difficultyLevel = "INTERMEDIATE"),
//    Exercise(name = "Bulgarian Split Squats", targetMuscleGroup = "LOWER", difficultyLevel = "ADVANCE"),
//    Exercise(name = "Donkey Kicks", targetMuscleGroup = "LOWER", difficultyLevel = "BEGINNER"),
//    Exercise(name = "Step-Ups", targetMuscleGroup = "LOWER", difficultyLevel = "BEGINNER"),
//
//    // LOWER - Calves
//    Exercise(name = "Calf Raises", targetMuscleGroup = "LOWER", difficultyLevel = "BEGINNER"),
//    Exercise(name = "Seated Calf Raises", targetMuscleGroup = "LOWER", difficultyLevel = "BEGINNER"),
//    Exercise(name = "Jump Rope", targetMuscleGroup = "LOWER", difficultyLevel = "BEGINNER"),
//
//    // FULL
//    Exercise(name = "Burpees", targetMuscleGroup = "FULL", difficultyLevel = "INTERMEDIATE"),
//    Exercise(name = "Kettlebell Swings", targetMuscleGroup = "FULL", difficultyLevel = "INTERMEDIATE"),
//    Exercise(name = "Clean and Press", targetMuscleGroup = "FULL", difficultyLevel = "ADVANCE"),
//    Exercise(name = "Thrusters", targetMuscleGroup = "FULL", difficultyLevel = "ADVANCE"),
//
//
//    // CARDIO
//    Exercise(name = "Mountain Climbers", targetMuscleGroup = "CARDIO", difficultyLevel = "BEGINNER"),
//    Exercise(name = "Bear Crawls", targetMuscleGroup = "CARDIO", difficultyLevel = "INTERMEDIATE"),
//    Exercise(name = "Running", targetMuscleGroup = "CARDIO", difficultyLevel = "BEGINNER"),
//    Exercise(name = "Cycling", targetMuscleGroup = "CARDIO", difficultyLevel = "BEGINNER"),
//    Exercise(name = "Rowing", targetMuscleGroup = "CARDIO", difficultyLevel = "INTERMEDIATE"),
//    Exercise(name = "Jump Rope", targetMuscleGroup = "CARDIO", difficultyLevel = "BEGINNER"),
//    Exercise(name = "Swimming", targetMuscleGroup = "CARDIO", difficultyLevel = "INTERMEDIATE"),
//    Exercise(name = "Stair Climbing", targetMuscleGroup = "CARDIO", difficultyLevel = "INTERMEDIATE"),
//    Exercise(name = "High-Intensity Interval Training (HIIT)", targetMuscleGroup = "CARDIO", difficultyLevel = "ADVANCE"),
//
//    // FLEXIBILITY & Mobility
//    Exercise(name = "Dynamic Stretching", targetMuscleGroup = "FLEXIBILITY", difficultyLevel = "ALL"),
//    Exercise(name = "Static Stretching", targetMuscleGroup = "FLEXIBILITY", difficultyLevel = "ALL"),
//    Exercise(name = "Downward Dog", targetMuscleGroup = "FLEXIBILITY", difficultyLevel = "ALL"),
//    Exercise(name = "Child’s Pose", targetMuscleGroup = "FLEXIBILITY", difficultyLevel = "ALL"),
//    Exercise(name = "Foam Rolling", targetMuscleGroup = "FLEXIBILITY", difficultyLevel = "ALL"),


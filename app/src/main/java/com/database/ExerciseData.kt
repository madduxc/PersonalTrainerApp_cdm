package com.database

import com.database.entities.Exercise

val predefinedExercises = listOf(
    // Upper Body - Chest
    Exercise(name = "Push-ups", targetMuscleGroup = "UPPER", difficultyLevel = "BEGINNER", numberOfSets = 3, numberOfReps = 8),
    Exercise(name = "Bench Press", targetMuscleGroup = "UPPER", difficultyLevel = "INTERMEDIATE", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Chest Fly", targetMuscleGroup = "UPPER", difficultyLevel = "INTERMEDIATE", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Dumbbell Press", targetMuscleGroup = "UPPER", difficultyLevel = "INTERMEDIATE", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Chest Dips", targetMuscleGroup = "UPPER", difficultyLevel = "BEGINNER", numberOfSets = 3, numberOfReps = 12),

    // UPPER - Back
    Exercise(name = "Pull-ups", targetMuscleGroup = "UPPER", difficultyLevel = "BEGINNER", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Rows", targetMuscleGroup = "UPPER", difficultyLevel = "INTERMEDIATE", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Lat Pulldowns", targetMuscleGroup = "UPPER", difficultyLevel = "BEGINNER", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Deadlifts", targetMuscleGroup = "UPPER", difficultyLevel = "ADVANCE", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Back Extensions", targetMuscleGroup = "UPPER", difficultyLevel = "BEGINNER", numberOfSets = 3, numberOfReps = 12),

    // UPPER - Shoulders
    Exercise(name = "Shoulder Press", targetMuscleGroup = "UPPER", difficultyLevel = "INTERMEDIATE", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Lateral Raises", targetMuscleGroup = "UPPER", difficultyLevel = "BEGINNER", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Front Raises", targetMuscleGroup = "UPPER", difficultyLevel = "BEGINNER", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Face Pulls", targetMuscleGroup = "UPPER", difficultyLevel = "INTERMEDIATE", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Shrugs", targetMuscleGroup = "UPPER", difficultyLevel = "BEGINNER", numberOfSets = 3, numberOfReps = 12),

    // UPPER - Arms - Biceps
    Exercise(name = "Bicep Curls", targetMuscleGroup = "UPPER", difficultyLevel = "BEGINNER", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Hammer Curls", targetMuscleGroup = "UPPER", difficultyLevel = "BEGINNER", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Chin-ups", targetMuscleGroup = "UPPER", difficultyLevel = "ADVANCE", numberOfSets = 3, numberOfReps = 12),

    // UPPER - Arms - Triceps
    Exercise(name = "Tricep Dips", targetMuscleGroup = "UPPER", difficultyLevel = "INTERMEDIATE", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Tricep Pushdowns", targetMuscleGroup = "UPPER", difficultyLevel = "BEGINNER", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Overhead Tricep Extensions", targetMuscleGroup = "UPPER", difficultyLevel = "INTERMEDIATE", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Close-Grip Bench Press", targetMuscleGroup = "UPPER", difficultyLevel = "INTERMEDIATE", numberOfSets = 3, numberOfReps = 12),

    // Core
    Exercise(name = "Crunches", targetMuscleGroup = "UPPER", difficultyLevel = "BEGINNER", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Planks", targetMuscleGroup = "UPPER", difficultyLevel = "BEGINNER", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Russian Twists", targetMuscleGroup = "UPPER", difficultyLevel = "INTERMEDIATE", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Leg Raises", targetMuscleGroup = "UPPER", difficultyLevel = "INTERMEDIATE", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Bicycle Crunches", targetMuscleGroup = "UPPER", difficultyLevel = "INTERMEDIATE", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Mountain Climbers", targetMuscleGroup = "UPPER", difficultyLevel = "INTERMEDIATE", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Hanging Leg Raises", targetMuscleGroup = "UPPER", difficultyLevel = "ADVANCE", numberOfSets = 3, numberOfReps = 12),

    // LOWER - Legs - Quadriceps
    Exercise(name = "Squats", targetMuscleGroup = "LOWER", difficultyLevel = "BEGINNER", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Lunges", targetMuscleGroup = "LOWER", difficultyLevel = "BEGINNER", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Leg Press", targetMuscleGroup = "LOWER", difficultyLevel = "INTERMEDIATE", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Leg Extensions", targetMuscleGroup = "LOWER", difficultyLevel = "BEGINNER", numberOfSets = 3, numberOfReps = 12),

    // LOWER - Legs - Hamstrings
    Exercise(name = "Romanian Deadlifts", targetMuscleGroup = "LOWER", difficultyLevel = "INTERMEDIATE", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Leg Curls", targetMuscleGroup = "LOWER", difficultyLevel = "BEGINNER", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Glute Bridges", targetMuscleGroup = "LOWER", difficultyLevel = "BEGINNER", numberOfSets = 3, numberOfReps = 12),

    // LOWER - Glutes
    Exercise(name = "Hip Thrusts", targetMuscleGroup = "LOWER", difficultyLevel = "INTERMEDIATE", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Bulgarian Split Squats", targetMuscleGroup = "LOWER", difficultyLevel = "ADVANCE", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Donkey Kicks", targetMuscleGroup = "LOWER", difficultyLevel = "BEGINNER", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Step-Ups", targetMuscleGroup = "LOWER", difficultyLevel = "BEGINNER", numberOfSets = 3, numberOfReps = 12),

    // LOWER - Calves
    Exercise(name = "Calf Raises", targetMuscleGroup = "LOWER", difficultyLevel = "BEGINNER", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Seated Calf Raises", targetMuscleGroup = "LOWER", difficultyLevel = "BEGINNER", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Jump Rope", targetMuscleGroup = "LOWER", difficultyLevel = "BEGINNER", numberOfSets = 3, numberOfReps = 12),

    // FULL
    Exercise(name = "Burpees", targetMuscleGroup = "FULL", difficultyLevel = "INTERMEDIATE", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Kettlebell Swings", targetMuscleGroup = "FULL", difficultyLevel = "INTERMEDIATE", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Clean and Press", targetMuscleGroup = "FULL", difficultyLevel = "ADVANCE", numberOfSets = 3, numberOfReps = 12),
    Exercise(name = "Thrusters", targetMuscleGroup = "FULL", difficultyLevel = "ADVANCE", numberOfSets = 3, numberOfReps = 12),


    // CARDIO
    Exercise(name = "Mountain Climbers", targetMuscleGroup = "CARDIO", difficultyLevel = "BEGINNER"),
    Exercise(name = "Bear Crawls", targetMuscleGroup = "CARDIO", difficultyLevel = "INTERMEDIATE"),
    Exercise(name = "Running", targetMuscleGroup = "CARDIO", difficultyLevel = "BEGINNER"),
    Exercise(name = "Cycling", targetMuscleGroup = "CARDIO", difficultyLevel = "BEGINNER"),
    Exercise(name = "Rowing", targetMuscleGroup = "CARDIO", difficultyLevel = "INTERMEDIATE"),
    Exercise(name = "Jump Rope", targetMuscleGroup = "CARDIO", difficultyLevel = "BEGINNER"),
    Exercise(name = "Swimming", targetMuscleGroup = "CARDIO", difficultyLevel = "INTERMEDIATE"),
    Exercise(name = "Stair Climbing", targetMuscleGroup = "CARDIO", difficultyLevel = "INTERMEDIATE"),
    Exercise(name = "High-Intensity Interval Training (HIIT)", targetMuscleGroup = "CARDIO", difficultyLevel = "ADVANCE"),

    // FLEXIBILITY & Mobility
    Exercise(name = "Dynamic Stretching", targetMuscleGroup = "FLEXIBILITY", difficultyLevel = "ALL"),
    Exercise(name = "Static Stretching", targetMuscleGroup = "FLEXIBILITY", difficultyLevel = "ALL"),
    Exercise(name = "Downward Dog", targetMuscleGroup = "FLEXIBILITY", difficultyLevel = "ALL"),
    Exercise(name = "Child’s Pose", targetMuscleGroup = "FLEXIBILITY", difficultyLevel = "ALL"),
    Exercise(name = "Foam Rolling", targetMuscleGroup = "FLEXIBILITY", difficultyLevel = "ALL"),
)

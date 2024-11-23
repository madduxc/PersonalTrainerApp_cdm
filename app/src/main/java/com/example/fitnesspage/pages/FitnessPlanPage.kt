package com.example.fitnesspage.pages

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.Graph
import com.database.entities.Exercise

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

import kotlinx.serialization.encodeToString

import kotlinx.serialization.json.Json

// This composable displays information pulled from backend

@Composable
fun FitnessPlanPage(
    // uses the navController passed in to navigate from and to screens
    // uses passed in answers from survey screen to display data -- needs to be changed
    answers: Map<String, Set<String>>,
    navController: NavController
) {
    // Variable to control the visibility of the warning dialog
    var showWarningDialog by remember { mutableStateOf(false) }

    // States to store the queried exercises
    // these are the exercises that are displayed in the UI
    val displayedExercises = remember { mutableStateOf<List<Exercise>>(emptyList()) }
    // these are the exercises that are pulled from the database
    val allExercises = remember { mutableStateOf<List<Exercise>>(emptyList()) }
    // this calls the queries without blocking the main thread
    val coroutineScope = rememberCoroutineScope()

    // Query exercises when the page loads
    // this ensures that the code runs once
    LaunchedEffect(Unit) {
        // runs the queries
        // answers are the responses from survey,
        fetchExercises(answers, displayedExercises, coroutineScope)
        coroutineScope.launch {
            allExercises.value = Graph.database.exerciseDao().getAllExercises() // Fetch all exercises
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(color = 0xFFF5F5F5)) // Set cream background color
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Your Fitness Plan",
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,)
        Text("Here's a list of up to 6 exercises based on your selections. Feel free to remove or add more exercises!",
            fontFamily = FontFamily.SansSerif,
            )
        // Lazy column allows for a list view that conserves space
        // I used this so the "ADD" and "Navigation" buttons fit
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            // For each exercise in the displaysExercise list, call the ExerciseItem Card
            items(displayedExercises.value) { exercise ->
                ExerciseItem(
                    // the exercise to be displayed
                    exercise = exercise,
                    // On click, remove this exercise from being displayed - i.e. doesn't remove the exercise in the backend
                    onRemoveExercise = { removeExercise(it, displayedExercises) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Add Exercise Button -- this displays at the bottom
        AddExerciseButton(
            // this allows for all exercises in database to be selectable options for users
            exercises = allExercises.value,
            // on selection
            onAddExercise = { selectedExercise ->
                // Add selected exercise to displayed list if its not already there
                if (!displayedExercises.value.contains(selectedExercise)) {
                    // Note we have to create a new list, with the same name, as this ensures that
                    // the MutableState updates which triggers a recomposition of the UI.
                    displayedExercises.value = displayedExercises.value + selectedExercise
                }
            }
        )

        // navigation buttons
        NavigationButtons(
            // triggers the showWarningDialog flag
            onBackToSurveyClick = { showWarningDialog = true },
            // navigates to workout page
            onStartWorkoutClick = {
                val displayedExercisesJson = Uri.encode(Json.encodeToString(displayedExercises.value))
                navController.navigate("workout/$displayedExercisesJson")
            }
        )
}
            // This shows the warning dialog if the user presses the "Back to Survey" button
            if (showWarningDialog) {
                AlertDialog(
                    // If clicked out of the warning pop up
                    onDismissRequest = { showWarningDialog = false },
                    title = { Text("Confirm") },
                    text = { Text("Are you sure you want to go back to the survey page? Your responses will not be saved.") },
                    // Confirm button
                    confirmButton = {
                        Button(
                            onClick = {
                                // set the show warning variable back to false
                                showWarningDialog = false
                                // navigate back to survey screen
                                navController.navigate("survey") {
                                    // but remove all screens from the back stack
                                    // this allows for a true "refresh" of the survey screen
                                    popUpTo("survey") { inclusive = true }
                                }
                            }
                        ) {
                            Text("Yes")
                        }
                    },
                    // dismiss button
                    dismissButton = {
                        Button(
                            // set warning variable to false
                            onClick = { showWarningDialog = false }
                        ) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }



// navigation buttons
@Composable
fun NavigationButtons(
    onBackToSurveyClick: () -> Unit,
    onStartWorkoutClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(onClick = onBackToSurveyClick) {
            Text("Back to Survey",fontFamily = FontFamily.SansSerif,)
        }
        Button(onClick = onStartWorkoutClick) {
            Text("Start Workout",fontFamily = FontFamily.SansSerif,)
        }
    }
}

@Composable
fun AddExerciseButton(
    exercises: List<Exercise>, // Pass the list of all exercises, i.e. allExercises above
    onAddExercise: (Exercise) -> Unit // Callback when an exercise is added
) {
    // flag to show list of exercises
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Button to open the list of exercises
        Button(
            onClick = { showDialog = true },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("Add Exercise",fontFamily = FontFamily.SansSerif,)
        }

        // Dialog to display all exercises
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Add an Exercise",fontFamily = FontFamily.SansSerif,) },
                text = {
                    LazyColumn {
                        items(exercises) { exercise ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        // Adds exercise to displayedExercises list when clicked
                                        onAddExercise(exercise)
                                        showDialog = false // Close dialog
                                    }
                                    .padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = exercise.name,
                                    modifier = Modifier.weight(1f),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }
                },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("Close",fontFamily = FontFamily.SansSerif,)
                    }
                }
            )
        }
    }
}


// Exercise Card
@Composable
fun ExerciseItem(
    exercise: Exercise,
    onRemoveExercise: (Exercise) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
            .clickable { }
            .shadow(1.dp)
//            .background(Color(color = 0xFFFFFAF1))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                // displays name of exercise
                text = exercise.name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = FontFamily.SansSerif,

            )
        }
// Trashcan Icon which when clicked calls the remove exercise function below
        IconButton(onClick = { onRemoveExercise(exercise) }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Remove Exercise",
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
}


private fun fetchExercises(
    answers: Map<String, Set<String>>,
    exercises: MutableState<List<Exercise>>,
    coroutineScope: CoroutineScope
) {
    // uses the responses from survey to query the database
    coroutineScope.launch {
        // variables that hold that responses from survey
        val muscleGroup = answers["id3"]?.firstOrNull() ?: ""
        val difficultyLevel = answers["id2"]?.firstOrNull() ?: ""
        val goal = answers["id1"]?.firstOrNull() ?: ""
        // variable to hold the response back from database
        val allExercises = when (goal) {
            // if goal is cardio, pass in the goal for the targeted muscle group
            "CARDIO" -> {
//                Graph.database.exerciseDao().getExercisesByCriteria(goal, difficultyLevel)
                Graph.exerciseRepository.getExercisesByTargetMuscleAndDifficulty(goal, difficultyLevel)
            }
            // if the goal is flexibility, pass in only the goal for the target muscle group
            "FLEXIBILITY" -> {
//                Graph.database.exerciseDao().getExercisesByCriteria(goal, "ALL")
                // returns all flexibility exercises
                Graph.exerciseRepository.getExercisesByTargetMuscleGroup(goal)
            }
            // else user wants to build strength
            else -> {
                // if their at "advance" level, they can use all exercises
                if (difficultyLevel == "ADVANCE") {
                    Graph.exerciseRepository.getExercisesByTargetMuscleGroup(muscleGroup)
                } else {
                    // else only select exercises at their targeted fitness level
                Graph.exerciseRepository.getExercisesByTargetMuscleAndDifficulty(muscleGroup, difficultyLevel)}
            }
        }
        // a randomized list of up to 6 exercises
        exercises.value = allExercises.shuffled().take(6)
    }
}

private fun removeExercise(
    // This is the exercise to be removed
    exercise: Exercise,
    // This holds the argument of the mutable list of exercises that was passed in
    // -i.e., displayedExercises which triggers recomposition when updated
    exercises: MutableState<List<Exercise>>
) {
    // This creates a new list that includes only the exercises that are not equal to the removed exercise
    // I.E., filter just iterates over all elements in the list and applies the condition: "it != exercise"
    exercises.value = exercises.value.filter { it != exercise }
}








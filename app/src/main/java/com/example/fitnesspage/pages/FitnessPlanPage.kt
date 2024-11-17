package com.example.fitnesspage.pages

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.Graph
import com.database.entities.Exercise

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
    val displayedExercises = remember { mutableStateOf<List<Exercise>>(emptyList()) }
    val allExercises = remember { mutableStateOf<List<Exercise>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    // Query exercises when the page loads
    LaunchedEffect(Unit) {
        fetchExercises(answers, displayedExercises, coroutineScope)
        coroutineScope.launch {
            allExercises.value = Graph.database.exerciseDao().getAllExercises() // Fetch all exercises
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Fitness Plan Recommendations", style = MaterialTheme.typography.headlineMedium)
        Text("Here's a list of 6 exercises based on your selections. Feel free to remove or add more exercises!")
        // Exercise List with weight to allow space for buttons
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(displayedExercises.value) { exercise ->
                ExerciseItem(
                    exercise = exercise,
                    onRemoveExercise = { removeExercise(it, displayedExercises) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Add Exercise Button
        AddExerciseButton(
            exercises = allExercises.value,
            onAddExercise = { selectedExercise ->
                // Add selected exercise to displayed list
                if (!displayedExercises.value.contains(selectedExercise)) {
                    displayedExercises.value = displayedExercises.value + selectedExercise
                }
            }
        )


        NavigationButtons(
            onBackToSurveyClick = { showWarningDialog = true },
            onStartWorkoutClick = { navController.navigate("workout") }
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
            Text("Back to Survey")
        }
        Button(onClick = onStartWorkoutClick) {
            Text("Start Workout")
        }
    }
}

@Composable
fun AddExerciseButton(
    exercises: List<Exercise>, // Pass the list of all exercises
    onAddExercise: (Exercise) -> Unit // Callback when an exercise is added
) {
    var showDialog by remember { mutableStateOf(false) } // Track dialog visibility

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
            Text("Add Exercise")
        }

        // Dialog to display all exercises
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Add an Exercise") },
                text = {
                    LazyColumn {
                        items(exercises) { exercise ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onAddExercise(exercise) // Add exercise when clicked
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
                        Text("Close")
                    }
                }
            )
        }
    }
}



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
            .clickable { /* Optionally handle item clicks */ }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = exercise.name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

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
    coroutineScope.launch {
        val muscleGroup = answers["id3"]?.firstOrNull() ?: ""
        val difficultyLevel = answers["id2"]?.firstOrNull() ?: ""
        val goal = answers["id1"]?.firstOrNull() ?: ""

        val allExercises = when (goal) {
            "CARDIO" -> {
                Graph.database.exerciseDao().getExercisesByCriteria(goal, difficultyLevel)
            }
            "FLEXIBILITY" -> {
                Graph.database.exerciseDao().getExercisesByCriteria(goal, "ALL")
            }
            else -> {
                Graph.database.exerciseDao().getExercisesByCriteria(muscleGroup, difficultyLevel)
            }
        }
        exercises.value = allExercises.shuffled().take(6)
    }
}

private fun removeExercise(
    exercise: Exercise,
    exercises: MutableState<List<Exercise>>
) {
    exercises.value = exercises.value.filter { it != exercise }
}








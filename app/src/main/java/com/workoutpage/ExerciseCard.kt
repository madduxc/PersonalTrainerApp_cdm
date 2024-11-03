package com.workoutpage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ExerciseCard(navController: NavController, data: TileData) {

    Surface(
        modifier = Modifier.padding(4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(color = Color.hsv(200f, 0.3f, 0.99f))
                .border(
                    border = BorderStroke(
                        4.dp,
                        color = Color.Gray
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                ExerciseText(data)

                ExerciseInputs(data)

                // navigate back to the Home screen
                CloseButton(navController)
            }
        }
    }
}

@Composable
fun ExerciseText(data: TileData) {
    // all of this needs to be dynamically filled and sized
    Text(
        text = "Let's Do This!",
        modifier = Modifier.padding(20.dp, 8.dp),
        fontSize = 36.sp
    )
    Text(
        text = data.title, // "Leg Press Goal:",
        modifier = Modifier.padding(8.dp, 4.dp),
        fontSize = 28.sp
    )
    Text(
        text = " ${data.sets} sets of ${data.reps} at ${data.weight} lbs",
        modifier = Modifier.padding(8.dp, 4.dp),
        fontSize = 28.sp
    )
}

@Composable
fun ExerciseInputs(data: TileData) {
    //
    // Generated logic using chatGPT
    // List of MutableState for each TextField
    val repsFieldStates = remember { MutableList(data.sets) { mutableStateOf("") } }
    val weightFieldState = remember { MutableList(data.sets) { mutableStateOf("") } }
    // List to store saved inputs
    val savedInputReps = remember { mutableStateListOf<Int>() }
    val savedInputWeights = remember { mutableStateListOf<Int>() }

    for (set in 1 .. data.sets) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Set $set: ",
                modifier = Modifier.padding(8.dp, 4.dp),
                fontSize = 28.sp,
            )
            TextField(

                value = repsFieldStates[set - 1].value,
                onValueChange = {
                    repsFieldStates[set - 1].value = it
                },
                modifier = Modifier.width(44.dp)
                    .height(28.dp)
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )
            Text(
                text = " reps  ",
                modifier = Modifier.padding(0.dp, 8.dp),
                fontSize = 20.sp,
            )
            TextField(

                value = weightFieldState[set - 1].value,
                onValueChange = {
                    weightFieldState[set - 1].value = it
                },
                modifier = Modifier.width(44.dp)
                    .height(28.dp)
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )
            Text(
                text = " lbs ",
                modifier = Modifier.padding(0.dp, 8.dp),
                fontSize = 20.sp,
            )
            Button(
                modifier = Modifier.padding(4.dp, 0.dp),
                onClick = {
                    // Save current inputs to the savedInputs list
                    savedInputReps.clear()
                    savedInputReps.addAll(repsFieldStates.map { it.value.toIntOrNull() ?: 0 })
                    savedInputWeights.clear()
                    savedInputWeights.addAll(weightFieldState.map { it.value.toIntOrNull() ?: 0 })
                },
            ) {
                Text("Save")
            }
        }
        Spacer(
            modifier = Modifier.height(8.dp)
        )
    }
    // testing only
    savedInputReps.forEachIndexed { index, input ->
        Text(" Reps Entered #${index + 1}: $input")
    }
    savedInputWeights.forEachIndexed { index, input ->
        Text(" Weights Entered #${index + 1}: $input")
    }
}

@Composable
fun CloseButton (navController: NavController) {
    //
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(4.dp, 8.dp)
    ) {
        //
        Spacer(
            Modifier.width(40.dp)
        )
        Button(
            modifier = Modifier.padding(4.dp),
            elevation = ButtonDefaults.buttonElevation(8.dp),
            onClick = {
                // Pop the back stack to return to the previous screen (Home screen)
                navController.popBackStack()
            }) {
            Text(text = "Close")
        }
        Spacer(
            Modifier.width(40.dp)
        )
    }
}

// **************************************************************************

@Preview(showBackground = true)
@Composable
fun ExercisePreview() {

    Surface(
        modifier = Modifier.padding(4.dp)
    ) {
        //
        Box(
            modifier = Modifier.fillMaxSize()
                .background(color = Color.hsv(200f, 0.3f, 0.99f))
                .border(
                    border = BorderStroke(
                        4.dp,
                        color = Color.Gray
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                ExerciseText(TileData("Leg Press", 80, 3, 12))

                // for (i in 1 .. sets) {
                //     TextField(i)
                // }
            }
        }
    }
}

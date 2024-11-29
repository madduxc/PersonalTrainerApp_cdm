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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

/** ExerciseCard - create the body of the the card
 *      Inputs: navController & TileData
 *      set up the structure of the card
 *      set up scrolling
 *      fill in the values
 */
@Composable
fun ExerciseCard(navController: NavController, data: TileData) {
    // outer container - the padding makes the card fit well
    Surface(
        modifier = Modifier.padding(4.dp)
    ) {
        // inner container - for proper placement of the contents
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.hsv(200f, 0.3f, 0.99f))
                .border(
                    border = BorderStroke(
                        4.dp,
                        color = Color.Gray
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            // set up the scroll state
            val scrollState = rememberScrollState()

            // make larger lists scrollable
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
            ) {
                // this section generates the summary and gathers data to be sent the the database
                // code is somewhat repetitious, but it simplifies data handling within the loop.
                if (data.reps > 1) {
                    if (data.weight > 0) {

                        ExerciseTextResistance(data)

                        // create the individual input fields & handle data
                        ExerciseInputsResistance(data)
                    }
                    else {
                        ExerciseTextBody(data)

                        // create the individual input fields & handle data
                        ExerciseInputsBody(data)
                    }
                }
                else {
                    // text handles all combinations
                    ExerciseTextTimeSpeedDistance(data)

                    if (data.time > 0) {
                        // handle time + speed
                        if (data.speed > 0) {
                            //
                            ExerciseInputsTime(data)
                        }
                        // hande time + distance
                        else if (data.distance > 0) {
                            //
                            ExerciseInputsTime(data)
                        }
                    }
                    // handle speed + distance
                    else {
                        // create the individual input fields & handle data
                        ExerciseInputsDistance(data)
                    }
                }

                // navigate back to the Workout page
                CloseButton(navController)
            }
        }
    }
}

// **************************************************************************

/** ExerciseTextBody - offer encouragement and summarize the exercise
 *      identify the exercise
 *      outline the exercise goal
 */
@Composable
fun ExerciseTextBody(data: TileData) {
    Text(
        text = "Get Yourself Ready!",
        modifier = Modifier.padding(20.dp, 8.dp),
        fontSize = 36.sp
    )
    Text(
        text = data.name,
        modifier = Modifier.padding(8.dp, 4.dp),
        fontSize = 28.sp
    )
    Text(
        text = "Let's do ${data.sets} sets of ${data.reps}",
        modifier = Modifier.padding(8.dp, 4.dp),
        fontSize = 28.sp
    )
}

/** ExerciseTextResistance - offer encouragement and summarize the exercise
 *      identify the exercise
 *      outline the exercise goal
 */
@Composable
fun ExerciseTextResistance(data: TileData) {
    Text(
        text = "Let's Do This!",
        modifier = Modifier.padding(20.dp, 8.dp),
        fontSize = 36.sp
    )
    Text(
        text = data.name,
        modifier = Modifier.padding(8.dp, 4.dp),
        fontSize = 28.sp
    )
    Text(
        text = " ${data.sets} sets of ${data.reps} at ${data.weight} lbs",
        modifier = Modifier.padding(8.dp, 4.dp),
        fontSize = 28.sp
    )
}

/** ExerciseTextTimeSpeedDistance - offer encouragement and summarize the exercise
 *      identify the exercise
 *      outline the exercise goal
 */
@Composable
fun ExerciseTextTimeSpeedDistance(data: TileData) {
    val minutes: Double =  data.time / 60
    val seconds: Double = data.time % 60

    // greeting
    Text(
        text = "Time to Work Out!",
        modifier = Modifier.padding(20.dp, 8.dp),
        fontSize = 36.sp
    )
    // the exercise
    Text(
        text = data.name,
        modifier = Modifier.padding(8.dp, 4.dp),
        fontSize = 28.sp
    )
    // handle speed + distance
    if (data.time > 0) {
        // handle seconds only (rare)
        if (minutes < 1) {
            Text(
                text = " for $seconds seconds",
                modifier = Modifier.padding(8.dp, 4.dp),
                fontSize = 28.sp
            )
        }
        // handle time
        else {
            Text(
                text = " for ${minutes.toInt()} minutes, ${seconds.toInt()} seconds",
                modifier = Modifier.padding(8.dp, 4.dp),
                fontSize = 28.sp
            )
        }
    }

    // handle speed
    if (data.speed > 0) {
        Text(
            text = " Try to maintain ${data.speed} miles per hour",
            modifier = Modifier.padding(8.dp, 4.dp),
            fontSize = 28.sp
        )
    }
    // handle distance
    if (data.distance > 0) {
        // distance less than one mile
        if (data.distance < 1) {
            val yards = (data.distance * 1760).toInt()
            Text(
                text = " Your goal is $yards yards",
                modifier = Modifier.padding(8.dp, 4.dp),
                fontSize = 28.sp
            )
        }
        // distance greater than one mile
        else {
            Text(
                text = " Your goal is ${data.distance} miles",
                modifier = Modifier.padding(8.dp, 4.dp),
                fontSize = 28.sp
            )
        }
    }
}

// **************************************************************************

/** ExerciseInputsBody - show text fields, gather input data, and store it
 *      create lists for data storage
 *      display input fields for each rep
 *      save the data to the list
 *      temp - display the data to the screen
 *      TODO: send back to the database
 */
@Composable
fun ExerciseInputsBody(data: TileData) {
    // Generated logic using chatGPT
    // List of MutableState for each TextField
    val repsFieldStates = remember { MutableList(data.sets) { mutableStateOf("") } }

    // List to store saved inputs
    val savedInputReps = remember { mutableStateListOf<Int>() }

    // list for each input field
    for (set in 1 .. data.sets) {
        Column {
            Row(
                modifier = Modifier.align(Alignment.Start)
            )
            {
                // state the set number
                Text(
                    text = "Set $set:",
                    modifier = Modifier.padding(4.dp, 4.dp),
                    fontSize = 24.sp,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(
                    modifier = Modifier.width(8.dp)
                )
                // gather the number of reps done
                // use number pad to gather integers only
                TextField(

                    value = repsFieldStates[set - 1].value,
                    onValueChange = {
                        repsFieldStates[set - 1].value = it
                    },
                    modifier = Modifier
                        .width(48.dp)
                        .height(28.dp)
                        .padding(12.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )
                Text(
                    text = " reps  ",
                    modifier = Modifier.padding(0.dp, 8.dp),
                    fontSize = 20.sp,
                )
                // create a save button for this input
                FilledIconButton(
                    modifier = Modifier
                        .padding(8.dp, 0.dp)
                        .size(32.dp),
                    onClick = {
                        // Save current inputs to the savedInputs list
                        savedInputReps.clear()
                        savedInputReps.addAll(repsFieldStates.map { it.value.toIntOrNull() ?: 0 })
                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "checkmark"
                    )
                }
                Spacer(
                    modifier = Modifier.width(20.dp)
                )
                // create an edit button for this input
                FilledIconButton(
                    modifier = Modifier
                        .padding(8.dp, 0.dp)
                        .size(32.dp),
                    onClick = {
                        // Set the saved value to zero
                        savedInputReps[set - 1] = 0
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "edit"
                    )
                }
            }
        }
    }
    // testing only
    savedInputReps.forEachIndexed { index, input ->
        Text(" Reps Entered #${index + 1}: $input")
    }
}

/** ExerciseInputsResistance - show text fields, gather input data, and store it
 *      create lists for data storage
 *      display input fields for each rep
 *      save the data to the list
 *      temp - display the data to the screen
 *      TODO: send back to the database
 */
@Composable
fun ExerciseInputsResistance(data: TileData) {
    // Generated logic using chatGPT
    // List of MutableState for each TextField
    val repsFieldStates = remember { MutableList(data.sets) { mutableStateOf("") } }
    val weightFieldState = remember { MutableList(data.sets) { mutableStateOf("") } }
    val savedInputReps = remember { mutableStateListOf<Int>() }
    val savedInputWeights = remember { mutableStateListOf<Double>() }

    // list for each input field
    for (set in 1 .. data.sets) {
        Column {
            Row(
                modifier = Modifier.align(Alignment.Start)
            )
            {
                // state the set number
                Text(
                    text = "Set $set:",
                    modifier = Modifier.padding(4.dp, 4.dp),
                    fontSize = 24.sp,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(
                    modifier = Modifier.width(8.dp)
                )
                // gather the number of reps done
                // use number pad to gather integers only
                TextField(

                    value = repsFieldStates[set - 1].value,
                    onValueChange = {
                        repsFieldStates[set - 1].value = it
                    },
                    modifier = Modifier
                        .width(48.dp)
                        .height(28.dp)
                        .padding(12.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )
                Text(
                    text = " reps  ",
                    modifier = Modifier.padding(0.dp, 8.dp),
                    fontSize = 20.sp,
                )
                // gather the weight used
                // use number pad to gather integers/doubles only
                TextField(

                    value = weightFieldState[set - 1].value,
                    onValueChange = {
                        weightFieldState[set - 1].value = it
                    },
                    modifier = Modifier
                        .width(52.dp)
                        .height(32.dp)
                        .padding(4.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )
                Text(
                    text = " lbs ",
                    modifier = Modifier.padding(0.dp, 8.dp),
                    fontSize = 20.sp,
                )
                // create a save button for this input
                FilledIconButton(
                    modifier = Modifier
                        .padding(8.dp, 0.dp)
                        .size(32.dp),
                    onClick = {
                        // Save current inputs to the savedInputs list
                        savedInputReps.clear()
                        savedInputReps.addAll(repsFieldStates.map { it.value.toIntOrNull() ?: 0 })
                        savedInputWeights.clear()
                        savedInputWeights.addAll(weightFieldState.map { it.value.toDoubleOrNull() ?: 0.0 })
                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "checkmark"
                    )
                }
                Spacer(
                    modifier = Modifier.width(20.dp)
                )
                // create an edit button for this input
                FilledIconButton(
                    modifier = Modifier
                        .padding(8.dp, 0.dp)
                        .size(32.dp),
                    onClick = {
                        // Set the saved value to zero
                        savedInputReps[set - 1] = 0
                        savedInputWeights[set - 1] = 0.0
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "edit"
                    )
                }
            }
        }
    }
    // testing only
    savedInputReps.forEachIndexed { index, input ->
        Text(" Reps Entered #${index + 1}: $input")
    }
    savedInputWeights.forEachIndexed { index, input ->
        Text(" Weights Entered #${index + 1}: $input")
    }
}

/** ExerciseInputsTime - show text fields, gather input data, and store it
 *      create lists for data storage
 *      display input fields for each rep
 *      save the data to the list
 *      temp - display the data to the screen
 *      TODO: send back to the database
 */
@Composable
fun ExerciseInputsTime(data: TileData) {
    // save as seconds an convert to minutes/seconds for display
    val savedInputMinutes = remember { mutableStateOf("") }
    val savedInputSeconds = remember { mutableStateOf("") }
    val savedInputDistance = remember { mutableStateOf("") }
    val savedInputSpeed = remember { mutableStateOf("") }
    var savedMinutesValue by remember { mutableIntStateOf(0) }
    var savedSecondsValue by remember { mutableIntStateOf(0) }
    var savedTime by remember { mutableStateOf(0.0f) }
    var savedDistanceValue by remember { mutableStateOf(0.0f) }
    var savedSpeedValue by remember { mutableStateOf(0.0f) }

    Column {
        // intro
        Row(
            modifier = Modifier.align(Alignment.Start)
        ) {
            // state the set number
            Text(
                text = "This session:",
                modifier = Modifier.padding(4.dp, 4.dp),
                fontSize = 24.sp,
            )
        }
        // time
        if (data.time > 0) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(
                    modifier = Modifier.width(8.dp)
                )
                // gather the minutes of the exercise
                // use number pad to gather numbers only
                TextField(
                    value = savedInputMinutes.value,
                    onValueChange = {
                        savedInputMinutes.value = it
                    },
                    modifier = Modifier
                        .width(48.dp)
                        .height(28.dp)
                        .padding(12.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )
                Text(
                    text = " min.  ",
                    modifier = Modifier.padding(0.dp, 8.dp),
                    fontSize = 20.sp,
                )
                // gather the seconds of the exercise
                // use number pad to gather integers/doubles only
                TextField(

                    value = savedInputSeconds.value,
                    onValueChange = {
                        savedInputSeconds.value = it
                    },
                    modifier = Modifier
                        .width(52.dp)
                        .height(32.dp)
                        .padding(4.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )
                Text(
                    text = " sec. ",
                    modifier = Modifier.padding(0.dp, 8.dp),
                    fontSize = 20.sp,
                )
                // create a save button for this input
                FilledIconButton(
                    modifier = Modifier
                        .padding(8.dp, 0.dp)
                        .size(32.dp),
                    onClick = {
                        // Save current inputs to the savedInputs list
                        savedTime = (savedInputMinutes.value.toFloatOrNull()?: 0f) * 60 + (savedInputSeconds.value.toFloatOrNull()?: 1f)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "checkmark"
                    )
                }
                Spacer(
                    modifier = Modifier.width(20.dp)
                )
                // create an edit button for this input
                FilledIconButton(
                    modifier = Modifier
                        .padding(8.dp, 0.dp)
                        .size(32.dp),
                    onClick = {
                        // Set the saved value to zero
                        savedSecondsValue = 0
                        savedMinutesValue = 0
                        savedTime = 0f
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "edit"
                    )
                }
            }
        }
        // speed
        if (data.speed > 0) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(
                    modifier = Modifier.width(8.dp)
                )
                // gather the speed of the exercise
                // use number pad to gather numbers only
                TextField(

                    value = savedInputSpeed.value,
                    onValueChange = {
                        savedInputSpeed.value = it
                    },
                    modifier = Modifier
                        .width(48.dp)
                        .height(28.dp)
                        .padding(12.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )
                Text(
                    text = " miles per hour ",
                    modifier = Modifier.padding(0.dp, 8.dp),
                    fontSize = 20.sp,
                )
                // create a save button for this input
                FilledIconButton(
                    modifier = Modifier
                        .padding(8.dp, 0.dp)
                        .size(32.dp),
                    onClick = {
                        // Save current inputs to the savedInputs list
                        savedSpeedValue = savedInputSpeed.value.toFloatOrNull() ?: 0.0f
                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "checkmark"
                    )
                }
                Spacer(
                    modifier = Modifier.width(20.dp)
                )
                // create an edit button for this input
                FilledIconButton(
                    modifier = Modifier
                        .padding(8.dp, 0.dp)
                        .size(32.dp),
                    onClick = {
                        // Set the saved value to zero
                        savedSpeedValue = 0f
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "edit"
                    )
                }
            }
        }
        // distance
        if (data.distance > 0) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(
                    modifier = Modifier.width(8.dp)
                )
                // gather the distance travelled
                // use number pad to gather numbers only
                TextField(
                    value = savedInputDistance.value,
                    onValueChange = {
                        savedInputDistance.value = it
                    },
                    modifier = Modifier
                        .width(48.dp)
                        .height(28.dp)
                        .padding(12.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )
                Text(
                    text = " miles",
                    modifier = Modifier.padding(0.dp, 8.dp),
                    fontSize = 20.sp,
                )
                // create a save button for this input
                FilledIconButton(
                    modifier = Modifier
                        .padding(8.dp, 0.dp)
                        .size(32.dp),
                    onClick = {
                        // Save current inputs to the savedInputs list
                        savedDistanceValue = savedInputDistance.value.toFloatOrNull()?: 0f
                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "checkmark"
                    )
                }
                Spacer(
                    modifier = Modifier.width(20.dp)
                )
                // create an edit button for this input
                FilledIconButton(
                    modifier = Modifier
                        .padding(8.dp, 0.dp)
                        .size(32.dp),
                    onClick = {
                        // Set the saved value to zero
                        savedDistanceValue = 0f
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "edit"
                    )
                }
            }
        }
        // testing only
        Text(" Time Entered    : $savedTime seconds")
        Text(" Speed Entered   : $savedSpeedValue mph")
        Text(" Distance Entered: $savedDistanceValue miles")
    }
}

/** ExerciseInputsDistance - show text fields, gather input data, and store it
 *      create lists for data storage
 *      display input fields for each rep
 *      save the data to the list
 *      temp - display the data to the screen
 *      TODO: send back to the database
 */
@Composable
fun ExerciseInputsDistance(data: TileData) {
    // List to store saved inputs
    val savedInputDistance = remember { mutableStateOf("") }
    var savedDistanceValue by remember { mutableStateOf(0) }
    // list for each input field
    Column {
        Row(
            modifier = Modifier.align(Alignment.Start)
        )
        {
            // state the set number
            Text(
                text = "This session:",
                modifier = Modifier.padding(4.dp, 4.dp),
                fontSize = 24.sp,
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(
                modifier = Modifier.width(8.dp)
            )
            // gather the number of reps done
            // use number pad to gather integers only
            TextField(

                value = savedInputDistance.value,
                onValueChange = {
                    savedInputDistance.value = it
                },
                modifier = Modifier
                    .width(48.dp)
                    .height(28.dp)
                    .padding(12.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )
            Text(
                text = " miles  ",
                modifier = Modifier.padding(0.dp, 8.dp),
                fontSize = 20.sp,
            )
            // create a save button for this input
            FilledIconButton(
                modifier = Modifier
                    .padding(8.dp, 0.dp)
                    .size(32.dp),
                onClick = {
                    // Save current inputs to the savedInputs list
                    savedDistanceValue = savedInputDistance.value.toIntOrNull() ?: 1
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "checkmark"
                )
            }
            Spacer(
                modifier = Modifier.width(20.dp)
            )
            // create an edit button for this input
            FilledIconButton(
                modifier = Modifier
                    .padding(8.dp, 0.dp)
                    .size(32.dp),
                onClick = {
                    // Set the saved value to zero
                    savedDistanceValue = 0
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "edit"
                )
            }
        }
    }
}

// **************************************************************************

@Composable
fun CloseButton (navController: NavController) {
    //
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp, 8.dp)
    ) {
        Spacer(
            Modifier.width(40.dp)
        )
        Button(
            modifier = Modifier.padding(4.dp),
            elevation = ButtonDefaults.buttonElevation(8.dp),
            onClick = {  }
        ) {
            Text(text = "Save All")
        }
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


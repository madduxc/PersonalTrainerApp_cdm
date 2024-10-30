package com.workoutpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fitnesspage.R

class ExerciseCard(navController: Any?) : Fragment() {
    companion object {
        fun newInstance() = ExerciseCard(navController = NavController)
    }

    // private val viewModel: ExerciseCardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        arguments?.let {
            val value = arguments?.getString("title")

        }

        return inflater.inflate(R.layout.fragment_exercise_card, container, false)
    }
}

@Composable
fun ExerciseCard(navController: NavController) {
    var sets = 3
    var reps = 10
    var weight = 50

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
                ExerciseText(sets, reps, weight)

                for (i in 1 .. sets) {
                    //
                    TextField1(i)
                }

                // Add a button to navigate back to the Home screen
                SaveButton(navController)
            }
        }
    }
}

@Composable
fun ExerciseText(sets: Int, reps:Int, weight:Int) {
    // all of this needs to be dynamically filled and sized
    Text(
        text = "Let's Do This!",
        modifier = Modifier.padding(8.dp),
        fontSize = 36.sp
    )
    Text(
        text = "Leg Press Goal:",
        modifier = Modifier.padding(4.dp),
        fontSize = 28.sp
    )
    Text(
        text = " $sets sets of $reps at $weight lbs",
        modifier = Modifier.padding(4.dp),
        fontSize = 28.sp
    )
}

@Composable
fun TextField1(sets: Int) {
    // example provided by Google AI overview
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        //
        Text(
            text = "Set $sets: ",
            modifier = Modifier.padding(4.dp),
            fontSize = 24.sp,
        )
        var input1 by remember { mutableStateOf("") }
        var input2 by remember { mutableStateOf("") }

        TextField(
            value = input1,
            onValueChange = { input1 = it },
            modifier = Modifier.width(40.dp)
                .height(32.dp)
        )
        Text(
            text = " Reps  ",
            modifier = Modifier.padding(4.dp),
            fontSize = 20.sp,
        )
        TextField(
            value = input2,
            onValueChange = { input2 = it },
            modifier = Modifier.width(40.dp)
                .height(32.dp)
        )
        Text(
            text = " lbs ",
            modifier = Modifier.padding(4.dp),
            fontSize = 20.sp,
        )
    }
}

@Composable
fun SaveButton (navController: NavController) {
    //
    Row(
        modifier = Modifier.fillMaxWidth()
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
            Text(text = "Save")
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
    //
    var sets = 3

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
                ExerciseText(3, 12, 75)

                for (i in 1 .. sets) {
                    //
                    TextField1(i)
                }
                // Add a button to navigate back to the Home screen
            }
        }
    }
}

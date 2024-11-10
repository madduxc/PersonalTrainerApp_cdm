package com.workoutpage

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Calendar

@Composable
fun SummaryLayout() {
    val date = Calendar.getInstance().time
    val formatter = SimpleDateFormat.getDateInstance()
    val formattedDate = formatter.format(date)

    // Sample exercise data
    val exercises = listOf(
        TileData(title = "Leg", weight = 120, sets = 2, reps = 10),
        TileData(title = "Squat", weight = 150, sets = 3, reps = 10),
        TileData(title = "Chest", weight = 40, sets = 5, reps = 11),
        TileData(title = "Bicep", weight = 50, sets = 3, reps = 10),
        TileData(title = "Pull", weight = 50, sets = 3, reps = 10),
    )

    Column(modifier = Modifier.padding(8.dp)) {
        // Display date
        Text(
            text = "Date: $formattedDate",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Enable horizontal scrolling for the table layout
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            // Left header column for "Weight", "Sets", and "Reps"
            ColumnWithDivider(
                content = {
                    Text(" ", fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))
                    HorizontalDivider(color = Color.Black, thickness = 1.dp)
                    Text("Weight", fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))
                    HorizontalDivider(color = Color.Black, thickness = 1.dp)
                    Text("Sets", fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))
                    HorizontalDivider(color = Color.Black, thickness = 1.dp)
                    Text("Reps", fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))
                }
            )

            // Exercise columns for each exercise
            exercises.forEach { exercise ->
                ColumnWithDivider(
                    content = {
                        Text(exercise.title, fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))
                        HorizontalDivider(color = Color.Black, thickness = 1.dp)
                        Text(exercise.weight.toString(), modifier = Modifier.padding(8.dp))
                        HorizontalDivider(color = Color.Black, thickness = 1.dp)
                        Text(exercise.sets.toString(), modifier = Modifier.padding(8.dp))
                        HorizontalDivider(color = Color.Black, thickness = 1.dp)
                        Text(exercise.reps.toString(), modifier = Modifier.padding(8.dp))
                    }
                )
            }
        }

        // Draw a bottom border for the table
        HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color.Black)
    }
}

// Helper composable to create a column with a right vertical divider
@Composable
fun ColumnWithDivider(content: @Composable ColumnScope.() -> Unit) {
    Row {
        Column(modifier = Modifier.width(80.dp)) {
            content()
        }
        Divider(
            color = Color.Black,
            modifier = Modifier
                .height(200.dp)
                .width(1.dp)
        )
    }
}


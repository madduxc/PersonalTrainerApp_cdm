package com.workoutpage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.workoutpage.ui.theme.WorkoutPageTheme
import java.text.SimpleDateFormat
import java.util.Calendar

// **********************************************************************************

// represents tile data
data class TileData(
    val title: String,
    val weight: Int,
    val sets: Int,
    val reps: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                WorkoutPage()
            }
        }
    }
}

// **************************************************************************

@Composable
fun WorkoutPage() {
    Scaffold(
        topBar = {
            WorkoutTopBar { }
        },
        bottomBar = {
            WorkoutBottomBar()
        },
        content =     { innerPadding ->
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(innerPadding)
            ) {
                SharedContent()
                WorkoutTiles()
            }
        }

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutTopBar(onNavClick: () -> Unit) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = { },
        colors = TopAppBarDefaults.topAppBarColors(Color.hsv(200f, 0.4f, 0.95f))
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        IconButton(
            onClick = onNavClick
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Localized description"
            )
        }
        Text(
            "Avatar \nHere",
            Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            fontSize = 16.sp
        )
        Text(
            "Today's Workout",
            Modifier.padding(horizontal = 20.dp, vertical = 16.dp),
            fontSize = 24.sp
        )
    }

}

@Composable
fun WorkoutBottomBar(navController: NavController) {
    BottomAppBar(
        containerColor = BottomAppBarDefaults.bottomAppBarFabColor
    ) {
        Row(
            modifier = Modifier.padding(12.dp, 4.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            StatsButton {navController.navigate("summary") }
            EditButton { }
        }
    }
}

@Composable
fun SharedContent() {
    // https://developer.android.com/reference/java/text/DateFormat
    val date = Calendar.getInstance().time
    val formatter = SimpleDateFormat.getDateInstance()
    val formatedDate = formatter.format(date)

    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = "Here is your workout routine for \n $formatedDate",
        fontSize = 20.sp,
        lineHeight = 28.sp,
        modifier = Modifier.padding()
            .height(60.dp),

        )
}

@Composable
// Main Screen - set up the list and handle clicks
fun WorkoutTiles() {
    val navController = rememberNavController()

    // sample data
    val tiles = remember {
        listOf(
            TileData(title = "Leg Press", weight = 120, sets = 2, reps = 10),
            TileData(title = "Leg Extension", weight = 30, sets = 4, reps = 12),
            TileData(title = "Chest Press", weight = 40, sets = 5, reps = 11),
            TileData(title = "Bicep Curl", weight = 50, sets = 3, reps = 10),
            TileData(title = "Squat", weight = 150, sets = 3, reps = 10),
            TileData(title = "Lat Pull", weight = 50, sets = 3, reps = 10),
        )
    }
    // Setting up the NavHost with the destinations
    NavHost(
        navController = navController,
        startDestination = "workout",
        modifier = Modifier.fillMaxSize()
            .padding(4.dp)
    ) {
        // Home screen route
        composable("workout") { TileList(tiles, navController) }
        composable("summary"){
            SummaryLayout()
        }
        // New page route
        composable("exercise/{title}/{weight}/{sets}/{reps}") { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title")
            val weight = backStackEntry.arguments?.getString("weight")?.toIntOrNull()
            val sets = backStackEntry.arguments?.getString("sets")?.toIntOrNull()
            val reps = backStackEntry.arguments?.getString("reps")?.toIntOrNull()

            ExerciseCard(
                navController, TileData(
                    title = title?: "Exercise",
                    weight?: 0,
                    sets?: 1,
                    reps?:0)
            )
        }
    }
}

@Composable
// displays the tiles
fun TileList(tiles: List<TileData>, navController: NavController) {
    LazyColumn {
        items(tiles) { tile ->
            //
            TileItem(tile, navController)
        }
    }
}

@Composable
// represents each tile
fun TileItem(tile: TileData, navController: NavController) {
    //
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate(
                    "exercise/${tile.title}/${tile.weight}/${tile.sets}/${tile.reps}"
                )
            },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.Blue)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "${tile.title}       ${tile.weight} lbs",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(
                modifier = Modifier.height(4.dp)
            )
            Text(
                text = "${tile.sets} sets of ${tile.reps} repetitions",
                fontSize = 16.sp,
                color = Color.DarkGray
            )
        }
    }
}

@Composable
fun WorkoutText(completeMessage: String, toGoMessage: String, modifier: Modifier = Modifier) {
    //
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = completeMessage,
            fontSize = 28.sp,
            lineHeight = 30.sp,
            textAlign = TextAlign.Center,
            modifier = modifier.align(alignment = Alignment.CenterHorizontally)
        )
        Text(
            text = toGoMessage,
            fontSize = 28.sp,
            lineHeight = 30.sp,
            modifier = modifier.align(alignment = Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun StatsButton(onStatsClick: () -> Unit) {
    //
    OutlinedButton(
        modifier = Modifier.padding(12.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(Color.Blue),
        elevation = ButtonDefaults.buttonElevation(4.dp),
        onClick = onStatsClick
    ) {
        Text(
            fontSize = 16.sp,
            text = "View Progress"
        )
    }
}

@Composable
fun EditButton(onStatsClick: () -> Unit) {
    //
    OutlinedButton(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(Color.Blue),
        elevation = ButtonDefaults.buttonElevation(4.dp),
        onClick = onStatsClick,
        enabled = true
    ) {
        Text(
            text = "Change It Up",
            fontSize = 16.sp
        )
    }
}

// **************************************************************************

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WorkoutPageTheme {
        Scaffold(
            topBar = {
                WorkoutTopBar { }
            },
            bottomBar = {
                WorkoutBottomBar()
            },
        )
        { innerPadding ->
            Box(
                modifier = Modifier.padding(innerPadding)
            )
            Column(
                Modifier.safeContentPadding()
            ) {
                SharedContent()
                WorkoutText(completeMessage = "xx Down", toGoMessage = "xx To Go")
                WorkoutTiles()
            }
        }
    }
}
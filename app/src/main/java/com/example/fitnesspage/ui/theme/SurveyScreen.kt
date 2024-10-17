package com.example.fitnesspage.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.fitnesspage.model.FitnessAppModel
import com.example.fitnesspage.model.Category
import com.example.fitnesspage.R
import com.example.fitnesspage.model.Recommendation

@Composable
fun SurveyScreen(
    viewModel: FitnessAppModel,
    uiState: UiState,
    modifier: Modifier= Modifier,
    ){
    Card(modifier = modifier, shape = RoundedCornerShape(
        topEnd = 20.dp, topStart = 40.dp,
        bottomEnd = 20.dp, bottomStart = 40.dp
    ) ) {
        Column {
            Text(text = "Survey", style = MaterialTheme.typography.headlineMedium) // Adjusted for better style
            Text(text = "What are your goals for exercising?", style = MaterialTheme.typography.bodyLarge)
            LazyColumn(modifier = modifier.padding(top = dimensionResource(id = R.dimen.padding_medium))) {
                items(uiState.categories) {
                    CategoryCard(category = it, modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = dimensionResource(
                                id = R.dimen.padding_small
                            ),
                            start = dimensionResource(id = R.dimen.padding_medium),
                            end = dimensionResource(id = R.dimen.padding_medium)
                        ),
                        onClick = {
//                            visible = false
                            viewModel.updateCurrentCategory(it)
//                            navigateFunction()
                        })
                }
            }

        }

    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCard(
    category: Category,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    colors: CardColors = CardDefaults.cardColors()
) {
    Card(
        colors = colors,
        onClick = onClick,
        shape = RoundedCornerShape(
            topEnd = 20.dp, topStart = 40.dp,
            bottomEnd = 20.dp, bottomStart = 40.dp
        ),
        modifier = modifier
    ) {
        Row(
            modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = stringResource(id = category.name),
                    style = MaterialTheme.typography.titleMedium
                )

            }

        }

    }

}
// preview

fun getSampleUiState(): UiState {
    return UiState(
        categories = listOf(
            Category(name = R.string.category_cardio, list = listOf(
                Recommendation(name = R.string.recommendation_run),
                Recommendation(name = R.string.recommendation_jump_rope)
            )),
            Category(name = R.string.category_strength, list = listOf(
                Recommendation(name = R.string.recommendation_bench_press),
                Recommendation(name = R.string.recommendation_squat)
            )),
            Category(name = R.string.category_flexibility, list = listOf(
                Recommendation(name = R.string.recommendation_yoga),
                Recommendation(name = R.string.recommendation_stretching)
            ))
        )
    )
}

@Composable
@Preview(showBackground = true)
fun PreviewSurveyScreen() {
    // Create a sample ViewModel. You might want to use a mocked version if actual logic isn't needed.
    val viewModel = FitnessAppModel() // Adjust based on your implementation
    val sampleUiState = getSampleUiState()

    MaterialTheme {
        SurveyScreen(viewModel = viewModel, uiState = sampleUiState)
    }
}
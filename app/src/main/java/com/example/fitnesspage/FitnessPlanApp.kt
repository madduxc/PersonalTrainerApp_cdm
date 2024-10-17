package com.example.fitnesspage

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.example.fitnesspage.model.FitnessAppModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.viewModelFactory


@Composable
fun FitnessPlanApp(
    viewModel: FitnessAppModel = viewModel()
)
{
//    val uiState by viewModel.uiState.collectAsState()
}
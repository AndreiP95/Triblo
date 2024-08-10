package ro.andreip.triblo.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import ro.andreip.triblo.home.presenter.HomeScreenViewModel

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val homeScreenUiState = viewModel.homeScreenUIState.collectAsState()
    Content(homeScreenUiState.value)
}

@Composable
private fun Content(homeScreenUIState: HomeScreenUIState) {

    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(state = scrollState)
        ) {
            homeScreenUIState.actionList.forEach {
                Item(it)
            }

        }
    }

}
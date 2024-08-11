package ro.andreip.triblo.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.CarouselState
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ro.andreip.triblo.home.presenter.HomeScreenViewModel
import ro.andreip.triblo.home.presenter.model.ActionBo
import ro.andreip.triblo.home.presenter.model.ChallengeBo
import ro.andreip.triblo.home.presenter.model.RewardBo

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val homeScreenUiState = viewModel.homeScreenUIState.collectAsState()

    Content(homeScreenUiState.value)
}

@Composable
private fun Content(homeScreenUIState: HomeScreenUIState) {
    val scrollState = rememberScrollState()

    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(state = scrollState),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 16.dp),
                textAlign = TextAlign.End,
                text = "Points: ${homeScreenUIState.points}",
                maxLines = 1,
                style = MaterialTheme.typography.labelLarge
            )

            CarouselView(
                title = "Challenges",
                actionList = homeScreenUIState.challengesList.map { it.actionBo }
            )
            Spacer(modifier = Modifier.size(24.dp))

            CarouselView(
                title = "Rewards",
                actionList = homeScreenUIState.rewardsList.map { it.actionBo }
            )

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CarouselView(title: String, actionList: List<ActionBo>) {

    Text(
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 16.dp),
        textAlign = TextAlign.Start,
        text = title,
        maxLines = 1,
        style = MaterialTheme.typography.labelLarge
    )


    HorizontalUncontainedCarousel(
        state = CarouselState { actionList.count() },
        itemWidth = 120.dp,
        content = { index -> Item(reward = actionList[index]) },
        itemSpacing = 8.dp
    )
}

@Preview
@Composable
fun HomeScreenPreview() {
    val itemList = mutableListOf<ActionBo>()
    repeat(5) {
        itemList.add(
            ActionBo(
                name = "action",
                points = 10.0,
                description = "",
                image = ""
            )
        )
    }

    val challengeList = itemList.map { ChallengeBo(it) }
    val rewardsList = itemList.map { RewardBo(it) }

    Content(
        homeScreenUIState = HomeScreenUIState(
            points = 0.0,
            toolbarTitle = "title",
            challengesList = challengeList,
            rewardsList = rewardsList
        )
    )
}
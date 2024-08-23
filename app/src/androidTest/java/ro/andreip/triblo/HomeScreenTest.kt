package ro.andreip.triblo

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithText
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ro.andreip.triblo.home.data.model.ActionItemDto
import ro.andreip.triblo.home.data.model.ChallengeDto
import ro.andreip.triblo.home.data.model.RewardDto
import ro.andreip.triblo.home.domain.ActionsRepository
import ro.andreip.triblo.home.presenter.HomeScreenAction
import ro.andreip.triblo.home.presenter.HomeScreenViewModel
import ro.andreip.triblo.home.presenter.mappers.ChallengeDtoToChallengeBoMapper
import ro.andreip.triblo.home.presenter.mappers.RewardsDtoToRewardsBoMapper
import ro.andreip.triblo.home.ui.HomeScreen
import ro.andreip.triblo.ui.theme.TribloTheme
import javax.inject.Inject

@HiltAndroidTest
class HomeScreenTest {

    @RelaxedMockK
    lateinit var mockActionsRepository: ActionsRepository

    @Inject
    lateinit var rewardsDtoToRewardsBoMapper: RewardsDtoToRewardsBoMapper

    @Inject
    lateinit var challengeDtoToChallengeBoMapper: ChallengeDtoToChallengeBoMapper

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var viewModel: HomeScreenViewModel

    private val fakeRewards = listOf(
        RewardDto(
            ActionItemDto(
                name = "Name",
                points = 25.0,
                image = "Image",
                description = "Description"
            )
        )
    )

    private val fakeChallenges = listOf(
        ChallengeDto(
            ActionItemDto(
                name = "Name",
                points = 25.0,
                image = "Image",
                description = "Description"
            )
        )
    )

    @Before
    fun init() {
        hiltRule.inject()
        MockKAnnotations.init(this, relaxUnitFun = true)

        viewModel = HomeScreenViewModel(
            repository = mockActionsRepository,
            rewardDtoToRewardBoMapper = rewardsDtoToRewardsBoMapper,
            challengeDtoToChallengeBoMapper = challengeDtoToChallengeBoMapper
        )
    }

    @Test
    fun testTitleIsShown() {
        // Start the app
        composeRule.activity.setContent {
            TribloTheme {
                HomeScreen()
            }
        }

        composeRule.onNodeWithText("Challenges").assertExists()
        composeRule.onNodeWithText("Rewards").assertExists()
    }

    @Test
    fun testHasPopulatedData() {
        // Start the app

        coEvery { mockActionsRepository.getAvailableRewardsList() } returns fakeRewards
        coEvery { mockActionsRepository.getAvailableChallengesList() } returns fakeChallenges

        viewModel.onAction(HomeScreenAction.ScreenStarted)

        composeRule.activity.setContent {
            TribloTheme {
                HomeScreen(viewModel = viewModel)
            }
        }

        composeRule.onAllNodesWithTag(testTag = "Challenges list").assertCountEquals(1)
        composeRule.onAllNodesWithTag(testTag = "Rewards list").assertCountEquals(1)

    }

    @Test
    fun testHasOnlyRewards() {
        // Start the app

        coEvery { mockActionsRepository.getAvailableRewardsList() } returns fakeRewards
        coEvery { mockActionsRepository.getAvailableChallengesList() } returns emptyList()

        viewModel.onAction(HomeScreenAction.ScreenStarted)

        composeRule.activity.setContent {
            TribloTheme {
                HomeScreen(viewModel = viewModel)
            }
        }

        composeRule.onAllNodesWithTag(testTag = "Challenges list").assertCountEquals(0)
        composeRule.onAllNodesWithTag(testTag = "Rewards list").assertCountEquals(1)

    }

}
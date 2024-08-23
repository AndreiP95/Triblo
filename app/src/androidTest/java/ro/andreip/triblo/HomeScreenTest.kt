package ro.andreip.triblo

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ro.andreip.triblo.home.presenter.HomeScreenViewModel
import ro.andreip.triblo.home.ui.HomeScreen
import ro.andreip.triblo.ui.theme.TribloTheme

@HiltAndroidTest
class HomeScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var viewModel : HomeScreenViewModel

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun myTest() {
        // Start the app
        composeRule.activity.setContent {
            viewModel = hiltViewModel<HomeScreenViewModel>()
            TribloTheme {
                HomeScreen()
            }
        }

        composeRule.onNodeWithText("Challenges").assertExists()
        composeRule.onNodeWithText("Rewards").assertExists()
    }
}
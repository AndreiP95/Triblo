package ro.andreip.triblo.home.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ro.andreip.triblo.home.domain.RewardsRepository
import ro.andreip.triblo.home.presenter.mappers.ItemToRewardsMapper
import ro.andreip.triblo.home.ui.HomeScreenUIState
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: RewardsRepository,
    private val itemToRewardsMapper: ItemToRewardsMapper
) : ViewModel() {

    private val _homeScreenUIState = MutableStateFlow(HomeScreenUIState())
    val homeScreenUIState: StateFlow<HomeScreenUIState> = _homeScreenUIState

    init {
        onAction(HomeScreenAction.ScreenStarted)
    }


    fun onAction(action: HomeScreenAction) {
        when (action) {
            HomeScreenAction.ScreenStarted -> {
                viewModelScope.launch {
                    onResult(HomeScreenResult.RewardsResult(getRewards().map {
                        itemToRewardsMapper.map(
                            it
                        )
                    }))
                }
            }
        }
    }

    private suspend fun getRewards() = repository.getAvailableRewardsList()

    private fun onResult(result: HomeScreenResult) {
        when (result) {
            is HomeScreenResult.RewardsResult ->
                _homeScreenUIState.update { it.copy(actionList = result.rewardsList) }
        }
    }
}
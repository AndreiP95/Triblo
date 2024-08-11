package ro.andreip.triblo.home.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ro.andreip.triblo.home.domain.ActionsRepository
import ro.andreip.triblo.home.presenter.mappers.ChallengeDtoToChallengeBoMapper
import ro.andreip.triblo.home.presenter.mappers.RewardsDtoToRewardsBoMapper
import ro.andreip.triblo.home.presenter.model.ChallengeBo
import ro.andreip.triblo.home.presenter.model.RewardBo
import ro.andreip.triblo.home.ui.HomeScreenUIState
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: ActionsRepository,
    private val rewardDtoToRewardBoMapper: RewardsDtoToRewardsBoMapper,
    private val challengeDtoToChallengeBoMapper: ChallengeDtoToChallengeBoMapper,
) : ViewModel() {

    private val _homeScreenUIState = MutableStateFlow(HomeScreenUIState())
    val homeScreenUIState: StateFlow<HomeScreenUIState> = _homeScreenUIState

    init {
        onAction(HomeScreenAction.ScreenStarted)
    }

    // TODO -> Aggregate both challenges and rewards into one flow and return it as result

    fun onAction(action: HomeScreenAction) {
        when (action) {
            HomeScreenAction.ScreenStarted -> {
                viewModelScope.launch {
                    onResult(HomeScreenResult.RewardsResult(getRewards().map { rewardDtoToRewardBoMapper.map(it) }))
                    onResult(HomeScreenResult.ChallengesResult(getChallenges().map { challengeDtoToChallengeBoMapper.map(it) }))
                }
            }
        }
    }

    private suspend fun getRewards() = repository.getAvailableRewardsList()

    private suspend fun getChallenges() = repository.getAvailableChallengesList()


    private fun onResult(result: HomeScreenResult) {
        when (result) {
            is HomeScreenResult.RewardsResult ->
                _homeScreenUIState.update { it.copy(rewardsList = result.rewardsList) }

            is HomeScreenResult.ChallengesResult -> _homeScreenUIState.update {
                it.copy(challengesList = result.challengesList)
            }
        }
    }
}
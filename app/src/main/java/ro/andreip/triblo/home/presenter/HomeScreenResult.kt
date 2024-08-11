package ro.andreip.triblo.home.presenter

import ro.andreip.triblo.home.presenter.model.ChallengeBo
import ro.andreip.triblo.home.presenter.model.RewardBo

sealed class HomeScreenResult {

    data class RewardsResult(val rewardsList: List<RewardBo>) : HomeScreenResult()
    data class ChallengesResult(val challengesList: List<ChallengeBo>) : HomeScreenResult()

}
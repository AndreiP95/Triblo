package ro.andreip.triblo.home.presenter

import ro.andreip.triblo.home.presenter.model.RewardsBo

sealed class HomeScreenResult {

    data class RewardsResult(val rewardsList : List<RewardsBo>) : HomeScreenResult()

}
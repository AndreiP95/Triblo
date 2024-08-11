package ro.andreip.triblo.home.ui

import ro.andreip.triblo.home.presenter.model.ChallengeBo
import ro.andreip.triblo.home.presenter.model.RewardBo

data class HomeScreenUIState(
    val toolbarTitle: String = "",
    val points: Double = 0.0,
    val rewardsList: List<RewardBo> = emptyList(),
    val challengesList: List<ChallengeBo> = emptyList()
)
package ro.andreip.triblo.home.ui

import ro.andreip.triblo.home.presenter.model.RewardsBo

data class HomeScreenUIState(
    val toolbarTitle : String = "",
    val actionList : List<RewardsBo> = emptyList()
)
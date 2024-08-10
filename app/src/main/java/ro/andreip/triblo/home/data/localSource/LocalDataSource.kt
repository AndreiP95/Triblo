package ro.andreip.triblo.home.data.localSource

import ro.andreip.triblo.home.data.model.RewardItemDto

interface LocalDataSource {

    fun findRewardById(rewardId : String) : RewardItemDto

    suspend fun getRewards() : List<RewardItemDto>

}
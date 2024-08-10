package ro.andreip.triblo.home.domain

import ro.andreip.triblo.home.data.model.RewardItemDto

interface RewardsRepository {

    suspend fun getAvailableRewardsList() : List<RewardItemDto>

    fun addSelectedReward(rewardId : String)

    fun updateRewardQuantity(rewardId : String, quantity : Int)

    fun deleteReward(rewardId : String)

    fun clearRewards()

    fun submitRewards()

}
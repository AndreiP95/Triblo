package ro.andreip.triblo.home.domain

import ro.andreip.triblo.home.data.model.ChallengeDto
import ro.andreip.triblo.home.data.model.RewardDto

interface ActionsRepository {

    suspend fun getAvailableChallengesList() : List<ChallengeDto>

    suspend fun getAvailableRewardsList() : List<RewardDto>

    fun addSelectedReward(rewardId : String)

    fun updateRewardQuantity(rewardId : String, quantity : Int)

    fun deleteReward(rewardId : String)

    fun clearRewards()

    fun submitRewards()

}
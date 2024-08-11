package ro.andreip.triblo.home.data.localSource

import ro.andreip.triblo.home.data.model.ChallengeDto
import ro.andreip.triblo.home.data.model.RewardDto

interface LocalDataSource {

    fun findRewardById(rewardId : String) : RewardDto

    suspend fun getRewards() : List<RewardDto>

    suspend fun getChallenges() : List<ChallengeDto>


}
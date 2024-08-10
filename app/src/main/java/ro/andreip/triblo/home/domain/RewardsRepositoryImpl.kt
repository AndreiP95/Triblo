package ro.andreip.triblo.home.domain

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import ro.andreip.triblo.home.data.localSource.LocalDataSource
import ro.andreip.triblo.home.data.model.RewardItemDto
import javax.inject.Inject

class RewardsRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : RewardsRepository {

    private val selectedRewards = MutableStateFlow(emptyList<Pair<RewardItemDto, Int>>())

    override suspend fun getAvailableRewardsList(): List<RewardItemDto> = localDataSource.getRewards()

    override fun addSelectedReward(rewardId: String) {
        addRewardToSelectedRewards(rewardId)
    }

    override fun updateRewardQuantity(rewardId: String, quantity: Int) {
        updateRewardToSelectedRewards(rewardId, quantity)
    }

    override fun deleteReward(rewardId: String) {
        removeRewardFromSelectedRewards(rewardId)
    }

    override fun clearRewards() {
        selectedRewards.value = emptyList()
    }

    override fun submitRewards() {
        TODO("Not yet implemented")
    }


    // Mock BE operations
    private fun updateRewardToSelectedRewards(rewardId: String, quantity: Int) {
        selectedRewards.update { rewards ->
            rewards.map { if (it.first.name == rewardId) Pair(it.first, quantity) else it }
        }
    }

    private fun addRewardToSelectedRewards(rewardId: String) {
        val reward = localDataSource.findRewardById(rewardId)
        selectedRewards.update {
            it + Pair(reward, 1)
        }
    }

    private fun removeRewardFromSelectedRewards(rewardId: String) {
        selectedRewards.update { it.filter { it.first.name == rewardId } }
    }

}
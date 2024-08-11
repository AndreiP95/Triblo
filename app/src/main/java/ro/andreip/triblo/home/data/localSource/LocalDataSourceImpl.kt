package ro.andreip.triblo.home.data.localSource

import android.content.Context
import ro.andreip.triblo.home.data.model.ActionItemDto
import ro.andreip.triblo.home.data.model.ChallengeDto
import ro.andreip.triblo.home.data.model.RewardDto

const val REWARDS_FILE_NAME = "rewards_items.json"
const val CHALLENGES_FILE_NAME = "challenge_items.json"


class LocalDataSourceImpl(
    private val context: Context
) : LocalDataSource {

    override fun findRewardById(rewardId: String): RewardDto {
        // TODO -> Aggregate from service + find in returned list
        return RewardDto(
            ActionItemDto(
                name = "123",
                points = 15.0,
                image = "images/decent_image.png",
                description = "Lose some of your life and also your dignity"
            )
        )
    }

    override suspend fun getRewards() =
        parseJSONWithGson(context, filename = REWARDS_FILE_NAME).map { RewardDto(it) }

    override suspend fun getChallenges() =
        parseJSONWithGson(context, filename = CHALLENGES_FILE_NAME).map { ChallengeDto(it) }


}
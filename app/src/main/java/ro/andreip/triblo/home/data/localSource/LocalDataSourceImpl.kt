package ro.andreip.triblo.home.data.localSource

import android.content.Context
import ro.andreip.triblo.home.data.model.RewardItemDto

// TODO -> Has to be sent as parameter after multiple files are added
const val REWARDS_FILE_NAME = "rewards_items.json"

class LocalDataSourceImpl(
    private val context: Context
) : LocalDataSource {

    override fun findRewardById(rewardId: String): RewardItemDto {
        // TODO -> Aggregate from service + find in returned list
        return RewardItemDto(
            name = "123",
            points = 15.0,
            image = "images/decent_image.png",
            description = "Lose some of your life and also your dignity"
        )
    }

    override suspend fun getRewards() = parseJSONWithGson(context, filename = REWARDS_FILE_NAME)

}
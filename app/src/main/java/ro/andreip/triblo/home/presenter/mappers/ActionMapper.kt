package ro.andreip.triblo.home.presenter.mappers

import ro.andreip.triblo.generic.util.Mapper
import ro.andreip.triblo.home.data.model.RewardItemDto
import ro.andreip.triblo.home.presenter.model.RewardsBo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemToRewardsMapper @Inject constructor() : Mapper<RewardItemDto, RewardsBo> {

    override suspend fun map(from: RewardItemDto) = RewardsBo(
        name = from.name,
        image = from.image,
        description = from.description,
        points = from.points
    )


}
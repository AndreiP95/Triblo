package ro.andreip.triblo.home.presenter.mappers

import ro.andreip.triblo.generic.util.Mapper
import ro.andreip.triblo.home.data.model.ActionItemDto
import ro.andreip.triblo.home.data.model.ChallengeDto
import ro.andreip.triblo.home.data.model.RewardDto
import ro.andreip.triblo.home.presenter.model.ActionBo
import ro.andreip.triblo.home.presenter.model.ChallengeBo
import ro.andreip.triblo.home.presenter.model.RewardBo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RewardsDtoToRewardsBoMapper @Inject constructor() : Mapper<RewardDto, RewardBo> {

    override suspend fun map(from: RewardDto) = RewardBo(actionMapper(from.actionItemDto))

}


@Singleton
class ChallengeDtoToChallengeBoMapper @Inject constructor() : Mapper<ChallengeDto, ChallengeBo> {

    override suspend fun map(from: ChallengeDto) = ChallengeBo(actionMapper(from.actionItemDto))

}


private fun actionMapper(from: ActionItemDto) = ActionBo(
    name = from.name,
    image = from.image,
    description = from.description,
    points = from.points
)
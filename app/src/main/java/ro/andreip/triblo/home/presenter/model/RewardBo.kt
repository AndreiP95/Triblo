package ro.andreip.triblo.home.presenter.model

@JvmInline
value class ChallengeBo(val actionBo: ActionBo)

@JvmInline
value class RewardBo(val actionBo: ActionBo)

data class ActionBo(
    val name: String,
    val description: String,
    val points: Double,
    val image: String
)
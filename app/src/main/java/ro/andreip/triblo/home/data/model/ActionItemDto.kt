package ro.andreip.triblo.home.data.model

import com.google.gson.annotations.SerializedName

@JvmInline
value class RewardDto(val actionItemDto: ActionItemDto)

@JvmInline
value class ChallengeDto(val actionItemDto: ActionItemDto)

data class ActionItemDto(
    @SerializedName ("Name") val name: String,
    @SerializedName ("Points") val points: Double,
    @SerializedName ("Image") val image: String = "",
    @SerializedName ("Description") val description: String = ""
)
package ro.andreip.triblo.home.data.model

import com.google.gson.annotations.SerializedName

data class RewardItemDto(
    @SerializedName ("Name") val name: String,
    @SerializedName ("Points") val points: Double,
    @SerializedName ("Image") val image: String = "",
    @SerializedName ("Description") val description: String = ""
)
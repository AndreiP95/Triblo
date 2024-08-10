package ro.andreip.triblo.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ro.andreip.triblo.R
import ro.andreip.triblo.home.presenter.model.RewardsBo

@Composable
fun Item(
    reward: RewardsBo,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
) {
    Column(
        modifier = modifier
            .wrapContentSize()
            .fillMaxWidth()
            .border(width = 1.dp, color = if (isSelected) Color.Blue else Color.Gray)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background), // Add rewards image
            contentDescription = "Product icon"
        )
        Spacer(modifier = Modifier.size(16.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = reward.name, style = MaterialTheme.typography.bodyMedium)
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Points: ", style = MaterialTheme.typography.bodyMedium)
                Text(text = reward.points.toString(), style = MaterialTheme.typography.bodyLarge)
            }

            // TODO -> Add description
        }
    }
}


@Preview
@Composable
fun ItemPreview() {
    val rewardsBo = RewardsBo(
        name = "Product",
        points = 15.0,
        image = "",
        description = ""
    )
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(times = 4) { time -> Item(reward = rewardsBo, modifier = Modifier, time % 2 == 1) }
    }
}
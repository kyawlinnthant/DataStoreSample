package klt.mdy.jetpackdatastoresample.screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SettingsItem(
    modifier: Modifier = Modifier,
    title: String,
    chosenField: String,
    onItemClicked: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 4.dp)
            .clip(RoundedCornerShape(size = 8.dp))
            .clickable {
                onItemClicked()
            }
            .padding(
                all = 12.dp,

                ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f),
            text = title
        )
        Text(
            text = chosenField,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSurface.copy(
                alpha = 0.5f
            )
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Detail"
        )
    }
}

@Composable
fun SettingsToggleItem(
    modifier: Modifier = Modifier,
    title: String,
    isSelected: Boolean,
    onCheckedChanged: (Boolean) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f),
            text = title
        )
        Switch(
            checked = isSelected,
            onCheckedChange = onCheckedChanged
        )
    }
}

@Composable
@Preview
private fun ItemPreview() {
    Surface {
        SettingsItem(
            title = "App mode",
            chosenField = "Dark",
            onItemClicked = {}
        )
    }
}

@Composable
@Preview
private fun TogglePreview() {
    Surface {
        SettingsToggleItem(
            title = "Private Profile",
            isSelected = true,
            onCheckedChanged = {}
        )
    }
}
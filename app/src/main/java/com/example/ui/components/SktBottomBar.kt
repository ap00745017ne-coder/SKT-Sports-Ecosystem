package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import com.example.ui.viewmodel.SktNavDestination

data class NavTabItem(
    val destination: SktNavDestination,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@Composable
fun SktBottomBar(
    currentDestination: SktNavDestination,
    onNavigate: (SktNavDestination) -> Unit
) {
    val items = listOf(
        NavTabItem(
            destination = SktNavDestination.DASHBOARD,
            label = "Overview",
            selectedIcon = Icons.Filled.Dashboard,
            unselectedIcon = Icons.Outlined.Dashboard
        ),
        NavTabItem(
            destination = SktNavDestination.ATHLETE_RECORDS,
            label = "Athlete IDs",
            selectedIcon = Icons.Filled.Badge,
            unselectedIcon = Icons.Outlined.Badge
        ),
        NavTabItem(
            destination = SktNavDestination.TOURNAMENTS,
            label = "Tournaments",
            selectedIcon = Icons.Filled.EmojiEvents,
            unselectedIcon = Icons.Outlined.EmojiEvents
        ),
        NavTabItem(
            destination = SktNavDestination.FITNESS,
            label = "Fitness",
            selectedIcon = Icons.Filled.DirectionsRun,
            unselectedIcon = Icons.Outlined.DirectionsRun
        ),
        NavTabItem(
            destination = SktNavDestination.UDAAN,
            label = "Udaan",
            selectedIcon = Icons.Filled.Star,
            unselectedIcon = Icons.Outlined.Star
        ),
        NavTabItem(
            destination = SktNavDestination.PARTNERSHIPS,
            label = "Partnerships",
            selectedIcon = Icons.Filled.AccountBalance,
            unselectedIcon = Icons.Outlined.AccountBalance
        )
    )

    NavigationBar(
        containerColor = SktNavyDark,
        tonalElevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.navigationBars)
    ) {
        items.forEach { tab ->
            val isSelected = currentDestination == tab.destination
            NavigationBarItem(
                selected = isSelected,
                onClick = { onNavigate(tab.destination) },
                icon = {
                    Icon(
                        imageVector = if (isSelected) tab.selectedIcon else tab.unselectedIcon,
                        contentDescription = tab.label,
                        modifier = Modifier.size(22.dp)
                    )
                },
                label = {
                    Text(
                        text = tab.label,
                        fontSize = 10.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        maxLines = 1
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = SktNavyDark,
                    selectedTextColor = SktGold,
                    indicatorColor = SktGold,
                    unselectedIconColor = SktTextSecondary,
                    unselectedTextColor = SktTextSecondary
                )
            )
        }
    }
}

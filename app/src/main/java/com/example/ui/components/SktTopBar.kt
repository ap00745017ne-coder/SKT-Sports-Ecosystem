package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.UserRole
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SktTopBar(
    currentRole: UserRole,
    unreadNotificationCount: Int = 2,
    onRoleClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onQuickActionClick: () -> Unit
) {
    Surface(
        color = SktNavyDark,
        tonalElevation = 6.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Brand Logo and Name
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { onRoleClick() }
                ) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(SktNavyCard)
                            .border(1.dp, SktGold.copy(alpha = 0.6f), RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.skt_sports_logo_1794243758674),
                            contentDescription = "SKT Sports Logo",
                            modifier = Modifier.size(36.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "SKT SPORTS",
                                fontWeight = FontWeight.Black,
                                fontSize = 18.sp,
                                letterSpacing = 0.5.sp,
                                color = SktGold
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(SktGold.copy(alpha = 0.15f))
                                    .padding(horizontal = 5.dp, vertical = 1.dp)
                            ) {
                                Text(
                                    text = "PRO",
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SktGold
                                )
                            }
                        }
                        Text(
                            text = "SHIV KHELTANTRA PVT. LTD.",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = SktTextSecondary,
                            letterSpacing = 0.3.sp
                        )
                    }
                }

                // Actions: Role Badge & Notifications
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Role Selector Pill
                    Surface(
                        onClick = onRoleClick,
                        shape = RoundedCornerShape(20.dp),
                        color = SktNavySurface,
                        border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder),
                        modifier = Modifier.height(34.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(
                                        when (currentRole) {
                                            UserRole.SUPER_ADMIN -> SktGold
                                            UserRole.COACH -> SktGreen
                                            UserRole.STUDENT -> SktElectricBlue
                                            UserRole.PARENT -> SktPurple
                                            UserRole.SCHOOL_ADMIN -> SktOrange
                                            UserRole.COORDINATOR -> Color(0xFF14B8A6)
                                        }
                                    )
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = currentRole.displayName.take(12),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = SktTextPrimary
                            )
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Change Role",
                                tint = SktGold,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }

                    // Notification Button
                    IconButton(
                        onClick = onNotificationClick,
                        modifier = Modifier.size(36.dp)
                    ) {
                        BadgedBox(
                            badge = {
                                if (unreadNotificationCount > 0) {
                                    Badge(
                                        containerColor = SktGold,
                                        contentColor = SktNavyDark
                                    ) {
                                        Text(
                                            text = "$unreadNotificationCount",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 10.sp
                                        )
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Notifications,
                                contentDescription = "Notifications",
                                tint = SktTextPrimary,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    }
                }
            }

            // Tagline strip
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "SEARCH • SUPPORT • SUCCEED",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = SktGold.copy(alpha = 0.85f),
                    letterSpacing = 1.2.sp
                )

                Text(
                    text = currentRole.badgeTitle.uppercase(),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    color = SktElectricBlue
                )
            }
        }
    }
}

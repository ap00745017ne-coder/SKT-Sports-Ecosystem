package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.model.UserRole
import com.example.ui.theme.*

@Composable
fun RoleSelectorDialog(
    currentRole: UserRole,
    onRoleSelected: (UserRole) -> Unit,
    onDismiss: () -> Unit
) {
    val roles = listOf(
        RoleItem(
            role = UserRole.SUPER_ADMIN,
            desc = "Full company leadership, schools, revenue, tournaments & settings",
            icon = Icons.Default.AdminPanelSettings,
            accentColor = SktGold
        ),
        RoleItem(
            role = UserRole.SCHOOL_ADMIN,
            desc = "School profile, student sports categories, attendance & fee reports",
            icon = Icons.Default.School,
            accentColor = SktOrange
        ),
        RoleItem(
            role = UserRole.STUDENT,
            desc = "Personal athlete passport, Athlete ID QR, training, fitness & certificates",
            icon = Icons.Default.DirectionsRun,
            accentColor = SktElectricBlue
        ),
        RoleItem(
            role = UserRole.COACH,
            desc = "Session drills, attendance marking, student performance & fitness assessments",
            icon = Icons.Default.Sports,
            accentColor = SktGreen
        ),
        RoleItem(
            role = UserRole.PARENT,
            desc = "Child performance tracking, attendance alerts, progress reports & fee receipts",
            icon = Icons.Default.FamilyRestroom,
            accentColor = SktPurple
        ),
        RoleItem(
            role = UserRole.COORDINATOR,
            desc = "Cross-school operations, coach allocation, talent hunt trials & event schedules",
            icon = Icons.Default.CoPresent,
            accentColor = Color(0xFF14B8A6)
        )
    )

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = SktNavyCard),
            border = androidx.compose.foundation.BorderStroke(1.5.dp, SktGold.copy(alpha = 0.7f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "SWITCH USER PORTAL",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Black,
                            color = SktGold
                        )
                        Text(
                            text = "Instant 1-Click Role Switcher",
                            fontSize = 10.sp,
                            color = SktTextSecondary
                        )
                    }

                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = SktTextSecondary)
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                roles.forEach { item ->
                    val isCurrent = currentRole == item.role
                    Surface(
                        onClick = {
                            onRoleSelected(item.role)
                            onDismiss()
                        },
                        shape = RoundedCornerShape(12.dp),
                        color = if (isCurrent) SktNavySurface else SktNavyDark,
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            if (isCurrent) item.accentColor else SktBorder
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(38.dp)
                                    .clip(CircleShape)
                                    .background(item.accentColor.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    item.icon,
                                    contentDescription = null,
                                    tint = item.accentColor,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = item.role.displayName,
                                    fontSize = 13.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isCurrent) item.accentColor else SktWhite
                                )
                                Text(
                                    text = item.desc,
                                    fontSize = 9.5.sp,
                                    color = SktTextSecondary,
                                    maxLines = 2
                                )
                            }

                            if (isCurrent) {
                                Icon(
                                    Icons.Default.CheckCircle,
                                    contentDescription = "Active",
                                    tint = item.accentColor,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

private data class RoleItem(
    val role: UserRole,
    val desc: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val accentColor: Color
)

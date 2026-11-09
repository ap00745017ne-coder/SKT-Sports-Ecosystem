package com.example.ui.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.*
import com.example.ui.viewmodel.NotificationItem

@Composable
fun NotificationDialog(
    notifications: List<NotificationItem>,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = SktNavyCard),
            border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "ECOSYSTEM NOTIFICATIONS",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black,
                        color = SktGold
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = SktTextSecondary)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                notifications.forEach { notif ->
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = SktNavySurface,
                        border = androidx.compose.foundation.BorderStroke(0.5.dp, SktBorder),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(
                                        when (notif.category) {
                                            "TOURNAMENT" -> SktGold.copy(alpha = 0.2f)
                                            "UDAAN" -> SktPurple.copy(alpha = 0.2f)
                                            "FITNESS" -> SktElectricBlue.copy(alpha = 0.2f)
                                            else -> SktGreen.copy(alpha = 0.2f)
                                        }
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = when (notif.category) {
                                        "TOURNAMENT" -> Icons.Default.EmojiEvents
                                        "UDAAN" -> Icons.Default.Star
                                        "FITNESS" -> Icons.Default.Speed
                                        else -> Icons.Default.AccountBalance
                                    },
                                    contentDescription = null,
                                    tint = SktGold,
                                    modifier = Modifier.size(16.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(notif.title, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = SktWhite)
                                Text(notif.description, fontSize = 10.sp, color = SktTextSecondary)
                                Text(notif.timeAgo, fontSize = 9.sp, color = SktElectricBlue)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = SktGold, contentColor = SktNavyDark),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Close", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

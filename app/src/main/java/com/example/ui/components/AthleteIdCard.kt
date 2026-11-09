package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.Athlete
import com.example.ui.theme.*

@Composable
fun AthleteIdCard(
    athlete: Athlete,
    modifier: Modifier = Modifier,
    onVerifyClick: (() -> Unit)? = null
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(12.dp, RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = SktNavyCard),
        border = androidx.compose.foundation.BorderStroke(
            1.5.dp,
            Brush.linearGradient(
                colors = listOf(SktGold, SktBorder, SktGold.copy(alpha = 0.5f))
            )
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            SktNavySurface.copy(alpha = 0.9f),
                            SktNavyCard,
                            SktNavyDark
                        )
                    )
                )
                .padding(18.dp)
        ) {
            // Card Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.skt_sports_logo_1794243758674),
                        contentDescription = "SKT Sports",
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "SKT SPORTS ECOSYSTEM",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Black,
                            color = SktGold,
                            letterSpacing = 0.8.sp
                        )
                        Text(
                            text = "OFFICIAL DIGITAL ATHLETE PASSPORT",
                            fontSize = 8.5.sp,
                            fontWeight = FontWeight.Bold,
                            color = SktTextSecondary,
                            letterSpacing = 0.5.sp
                        )
                    }
                }

                // Verified Badge
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = SktGreen.copy(alpha = 0.18f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SktGreen.copy(alpha = 0.5f))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Verified,
                            contentDescription = "Verified",
                            tint = SktGreen,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "VERIFIED",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Black,
                            color = SktGreen
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Athlete ID Banner
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                color = SktNavyDark,
                border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "ATHLETE ID NUMBER",
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold,
                            color = SktTextSecondary
                        )
                        Text(
                            text = athlete.athleteId,
                            fontSize = 15.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Black,
                            color = SktGold
                        )
                    }

                    if (athlete.isUdaanAthlete) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = SktPurple.copy(alpha = 0.25f),
                            border = androidx.compose.foundation.BorderStroke(1.dp, SktPurple)
                        ) {
                            Text(
                                text = "SKT UDAAN ATHLETE",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Black,
                                color = SktWhite,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Body: Photo / Avatar + Info + QR Code
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                // Left: Photo & Grade
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(84.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(76.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(SktNavySurface, SktNavyDark)
                                )
                            )
                            .border(2.dp, SktGold, RoundedCornerShape(14.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.SportsHandball,
                            contentDescription = athlete.fullName,
                            tint = SktGold,
                            modifier = Modifier.size(44.dp)
                        )
                        // Gender tag
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .offset(x = (-4).dp, y = (-4).dp)
                                .clip(CircleShape)
                                .background(SktGold)
                                .size(18.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = athlete.gender.take(1),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Black,
                                color = SktNavyDark
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = SktGold.copy(alpha = 0.15f)
                    ) {
                        Text(
                            text = "FITNESS: ${athlete.fitnessGrade}",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = SktGold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Center: Details
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = athlete.fullName,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Black,
                        color = SktWhite
                    )

                    Text(
                        text = athlete.schoolName,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = SktElectricBlue
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        AthleteMetaPill("Sport", athlete.primarySport)
                        AthleteMetaPill("Grade", athlete.grade)
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        AthleteMetaPill("Age", "${athlete.age} yrs")
                        AthleteMetaPill("Blood", athlete.bloodGroup)
                    }
                }

                // Right: High-Tech QR Code
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(78.dp)
                ) {
                    AthleticQrCode(
                        code = athlete.athleteId,
                        modifier = Modifier.size(72.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "SCAN TO VERIFY",
                        fontSize = 7.5.sp,
                        fontWeight = FontWeight.Bold,
                        color = SktTextSecondary,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Footer metrics: Attendance, Tournaments, Certificates
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(SktNavyDark.copy(alpha = 0.8f))
                    .padding(vertical = 8.dp, horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                MetricColumn(title = "Attendance", value = "${athlete.attendancePercent}%", color = SktGreen)
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(28.dp)
                        .background(SktBorder)
                )
                MetricColumn(title = "Certificates", value = "${athlete.certificatesCount}", color = SktGold)
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(28.dp)
                        .background(SktBorder)
                )
                MetricColumn(title = "Achievements", value = "${athlete.achievements.size}", color = SktElectricBlue)
            }

            // Key Achievement snippet
            if (athlete.achievements.isNotEmpty()) {
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.EmojiEvents,
                        contentDescription = "Achievement",
                        tint = SktGold,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = athlete.achievements.first(),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = SktTextPrimary,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Composable
private fun AthleteMetaPill(label: String, value: String) {
    Surface(
        shape = RoundedCornerShape(4.dp),
        color = SktNavySurface,
        border = androidx.compose.foundation.BorderStroke(0.5.dp, SktBorder)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$label: ",
                fontSize = 9.sp,
                fontWeight = FontWeight.Normal,
                color = SktTextSecondary
            )
            Text(
                text = value,
                fontSize = 9.5.sp,
                fontWeight = FontWeight.Bold,
                color = SktWhite
            )
        }
    }
}

@Composable
private fun MetricColumn(title: String, value: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Black,
            color = color
        )
        Text(
            text = title,
            fontSize = 9.sp,
            fontWeight = FontWeight.Medium,
            color = SktTextSecondary
        )
    }
}

@Composable
fun AthleticQrCode(code: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(SktWhite)
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val cellCount = 9
            val cellSize = size.width / cellCount

            // Deterministic pattern based on hash
            val hash = code.hashCode()
            for (row in 0 until cellCount) {
                for (col in 0 until cellCount) {
                    // Corner positioning squares
                    val isCornerFinder =
                        (row < 3 && col < 3) ||
                        (row < 3 && col >= cellCount - 3) ||
                        (row >= cellCount - 3 && col < 3)

                    val isFinderBorder = isCornerFinder && (
                        row == 0 || row == 2 || col == 0 || col == 2 ||
                        row == 0 || row == 2 || col == cellCount - 1 || col == cellCount - 3 ||
                        row == cellCount - 1 || row == cellCount - 3 || col == 0 || col == 2
                    )

                    val isFinderCenter =
                        (row == 1 && col == 1) ||
                        (row == 1 && col == cellCount - 2) ||
                        (row == cellCount - 2 && col == 1)

                    val bit = ((hash shr ((row * 3 + col) % 31)) and 1) == 1

                    if (isFinderBorder || isFinderCenter || (!isCornerFinder && bit)) {
                        drawRect(
                            color = Color(0xFF0B1931),
                            topLeft = Offset(col * cellSize, row * cellSize),
                            size = androidx.compose.ui.geometry.Size(cellSize, cellSize)
                        )
                    }
                }
            }
        }
    }
}

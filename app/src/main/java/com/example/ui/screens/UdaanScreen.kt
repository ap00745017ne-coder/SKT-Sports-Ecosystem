package com.example.ui.screens

import androidx.compose.foundation.*
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.Athlete
import com.example.model.UdaanInitiativeItem
import com.example.ui.theme.*
import com.example.ui.viewmodel.SktSportsViewModel

@Composable
fun UdaanScreen(
    viewModel: SktSportsViewModel,
    athletes: List<Athlete>,
    udaanInitiatives: List<UdaanInitiativeItem>
) {
    val udaanAthletes = athletes.filter { it.isUdaanAthlete || it.gender.equals("Female", ignoreCase = true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Hero Udaan Banner
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = SktNavyCard),
            border = androidx.compose.foundation.BorderStroke(1.5.dp, SktPurple.copy(alpha = 0.8f))
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.skt_udaan_banner_1794243784937),
                    contentDescription = "SKT Udaan",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                )

                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "SKT UDAAN – GIRLS SPORTS DEVELOPMENT",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Black,
                                color = SktGold,
                                letterSpacing = 0.8.sp
                            )
                            Text(
                                text = "FLYING HIGH THROUGH SPORTS • EMPOWERING CHAMPIONS",
                                fontSize = 8.5.sp,
                                fontWeight = FontWeight.Bold,
                                color = SktElectricBlue
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = SktPurple.copy(alpha = 0.25f),
                            border = androidx.compose.foundation.BorderStroke(1.dp, SktPurple)
                        ) {
                            Text(
                                "UDAAN FLAGSHIP",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Black,
                                color = SktWhite,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "A dedicated sports initiative breaking socio-cultural barriers, introducing multi-sport exposure, specialized self-defense bootcamps, and competitive league pathways for young girls.",
                        fontSize = 11.sp,
                        color = SktTextSecondary
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        UdaanStatPill("Enrolled Girls", "1,240+", SktGold)
                        UdaanStatPill("Schools Reached", "32", SktElectricBlue)
                        UdaanStatPill("Self-Defense Trained", "1,850", SktPurple)
                        UdaanStatPill("State Medals", "18", SktGreen)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Quick Udaan Actions
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = { viewModel.openAddStudentDialog(true) },
                colors = ButtonDefaults.buttonColors(containerColor = SktGold, contentColor = SktNavyDark),
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(Icons.Default.PersonAdd, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Enroll in Udaan", fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { /* Camp brochure */ },
                colors = ButtonDefaults.buttonColors(containerColor = SktNavySurface, contentColor = SktWhite),
                border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder),
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(Icons.Default.Shield, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Safety & Defense", fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Active Udaan Initiatives
        Text(
            text = "ACTIVE UDAAN INITIATIVES & CAMPS",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = SktGold
        )
        Spacer(modifier = Modifier.height(8.dp))

        udaanInitiatives.forEach { init ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = SktNavyCard),
                border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(init.title, fontSize = 13.5.sp, fontWeight = FontWeight.Black, color = SktWhite)
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = SktPurple.copy(alpha = 0.2f),
                            border = androidx.compose.foundation.BorderStroke(0.5.dp, SktPurple)
                        ) {
                            Text(
                                init.status,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = SktPurple,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    Text(init.description, fontSize = 10.5.sp, color = SktTextSecondary)

                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Lead Sport: ${init.leadSport}", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = SktElectricBlue)
                        Text("${init.participantsCount} Girls Participating", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = SktGold)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Udaan Girls Athlete Showcase
        Text(
            text = "UDAAN STAR ATHLETES & AMBASSADORS",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = SktGold
        )
        Spacer(modifier = Modifier.height(8.dp))

        udaanAthletes.forEach { ath ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable { viewModel.openAthleteIdCard(ath) },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = SktNavyCard),
                border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(SktPurple.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Star, contentDescription = null, tint = SktGold, modifier = Modifier.size(20.dp))
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(ath.fullName, fontSize = 13.5.sp, fontWeight = FontWeight.Bold, color = SktWhite)
                            Text("${ath.athleteId} • ${ath.primarySport}", fontSize = 10.sp, color = SktElectricBlue)
                            Text("${ath.schoolName} • Grade ${ath.fitnessGrade}", fontSize = 9.5.sp, color = SktTextSecondary)
                        }
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = SktGreen.copy(alpha = 0.15f)
                        ) {
                            Text(
                                "Att: ${ath.attendancePercent}%",
                                fontSize = 9.5.sp,
                                fontWeight = FontWeight.Bold,
                                color = SktGreen,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("View ID", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = SktGold)
                    }
                }
            }
        }
    }
}

@Composable
private fun UdaanStatPill(title: String, value: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontSize = 14.sp, fontWeight = FontWeight.Black, color = color)
        Text(title, fontSize = 8.5.sp, color = SktTextSecondary)
    }
}

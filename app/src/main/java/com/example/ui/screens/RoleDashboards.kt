package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.*
import com.example.ui.components.AthleteIdCard
import com.example.ui.theme.*
import com.example.ui.viewmodel.SktSportsViewModel

@Composable
fun SuperAdminDashboard(
    viewModel: SktSportsViewModel,
    schools: List<School>,
    athletes: List<Athlete>,
    coaches: List<Coach>,
    tournaments: List<Tournament>,
    financials: List<FinancialRecord>,
    categories: List<SportsCategoryItem>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Hero Ecosystem Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = SktNavyCard),
            border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.skt_sports_banner_1794243772405),
                    contentDescription = "SKT Ecosystem",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .clip(RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp)),
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, SktNavyCard.copy(alpha = 0.95f))
                            )
                        )
                )

                Column(
                    modifier = Modifier
                        .padding(top = 80.dp, start = 16.dp, end = 16.dp, bottom = 14.dp)
                ) {
                    Text(
                        text = "CENTRAL OPERATIONS DASHBOARD",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = SktGold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "SHIV KHELTANTRA PRIVATE LIMITED",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Black,
                        color = SktWhite
                    )
                    Text(
                        text = "Managing sports education, coaching, tournaments & talent development across Bihar & regional schools.",
                        fontSize = 11.sp,
                        color = SktTextSecondary,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Quick KPI Stats Grid
        Text(
            text = "ECOSYSTEM VITAL METRICS",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = SktGold,
            letterSpacing = 0.8.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            KpiCard(
                title = "Partner Schools",
                value = "${schools.size + 43}",
                subtitle = "5 Active Models",
                icon = Icons.Default.School,
                accentColor = SktGold,
                modifier = Modifier.weight(1f)
            )
            KpiCard(
                title = "Total Athletes",
                value = "3,420+",
                subtitle = "Digital IDs Issued",
                icon = Icons.Default.DirectionsRun,
                accentColor = SktElectricBlue,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            KpiCard(
                title = "Coaching Staff",
                value = "${coaches.size + 82}",
                subtitle = "NIS/AFC Certified",
                icon = Icons.Default.Sports,
                accentColor = SktGreen,
                modifier = Modifier.weight(1f)
            )
            KpiCard(
                title = "Tournaments",
                value = "${tournaments.size}",
                subtitle = "State & Inter-School",
                icon = Icons.Default.EmojiEvents,
                accentColor = SktOrange,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Quick Actions Row
        Text(
            text = "QUICK OPERATIONS",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = SktTextSecondary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { viewModel.openAddStudentDialog(true) },
                colors = ButtonDefaults.buttonColors(containerColor = SktGold, contentColor = SktNavyDark),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.Default.PersonAdd, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("+ Athlete", fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { viewModel.openAddSchoolDialog(true) },
                colors = ButtonDefaults.buttonColors(containerColor = SktNavySurface, contentColor = SktWhite),
                border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.Default.AddBusiness, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("+ School", fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { viewModel.openAddFitnessDialog(true) },
                colors = ButtonDefaults.buttonColors(containerColor = SktNavySurface, contentColor = SktGold),
                border = androidx.compose.foundation.BorderStroke(1.dp, SktGold.copy(alpha = 0.5f)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.Default.Speed, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("+ Test", fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Financial Overview Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = SktNavyCard),
            border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "MONTHLY FINANCIAL STATEMENT",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = SktGold
                        )
                        Text(
                            text = "Nov 2026 Operations Ledger",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Black,
                            color = SktWhite
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = SktGreen.copy(alpha = 0.2f)
                    ) {
                        Text(
                            text = "+37% vs Oct",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = SktGreen,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Gross Revenue", fontSize = 10.sp, color = SktTextSecondary)
                        Text("₹ 18,40,000", fontSize = 16.sp, fontWeight = FontWeight.Black, color = SktGreen)
                    }
                    Column {
                        Text("Operating Expenses", fontSize = 10.sp, color = SktTextSecondary)
                        Text("₹ 11,60,000", fontSize = 16.sp, fontWeight = FontWeight.Black, color = SktRed)
                    }
                    Column {
                        Text("Net Surplus", fontSize = 10.sp, color = SktTextSecondary)
                        Text("₹ 6,80,000", fontSize = 16.sp, fontWeight = FontWeight.Black, color = SktGold)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Progress Bar of Expenses vs Revenue
                LinearProgressIndicator(
                    progress = { 11.6f / 18.4f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = SktOrange,
                    trackColor = SktNavySurface,
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Sports Categories Carousel
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "SPORTS DISCIPLINES (17 ACTIVE)",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = SktGold
            )
            Text(
                text = "View All",
                fontSize = 11.sp,
                color = SktElectricBlue,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            categories.forEach { sport ->
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = SktNavySurface,
                    border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder),
                    modifier = Modifier.width(115.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(SktGold.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.SportsBasketball,
                                contentDescription = sport.name,
                                tint = SktGold,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = sport.name,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = SktWhite,
                            maxLines = 1
                        )
                        Text(
                            text = "${sport.activeAthletesCount} Athletes",
                            fontSize = 9.sp,
                            color = SktTextSecondary
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Live Tournament Matches
        Text(
            text = "LIVE MATCH & TOURNAMENT CENTER",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = SktGold
        )
        Spacer(modifier = Modifier.height(8.dp))

        tournaments.firstOrNull()?.matches?.forEach { match ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = SktNavyCard),
                border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (match.status == MatchStatus.LIVE) {
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .clip(CircleShape)
                                        .background(SktRed)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("LIVE", fontSize = 10.sp, fontWeight = FontWeight.Black, color = SktRed)
                            } else {
                                Text(match.status.name, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = SktTextSecondary)
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(match.round, fontSize = 10.sp, color = SktElectricBlue, fontWeight = FontWeight.SemiBold)
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${match.teamA} vs ${match.teamB}",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = SktWhite
                        )
                        if (match.details.isNotBlank()) {
                            Text(
                                text = match.details,
                                fontSize = 10.sp,
                                color = SktTextSecondary
                            )
                        }
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "${match.scoreA} - ${match.scoreB}",
                            fontSize = 18.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Black,
                            color = SktGold
                        )
                        IconButton(
                            onClick = { viewModel.openScoreUpdateDialog(match) },
                            modifier = Modifier.size(28.dp)
                        ) {
                            Icon(Icons.Default.Edit, contentDescription = "Edit Score", tint = SktGold, modifier = Modifier.size(16.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SchoolAdminDashboard(
    viewModel: SktSportsViewModel,
    schools: List<School>,
    athletes: List<Athlete>,
    tournaments: List<Tournament>
) {
    val school = schools.firstOrNull() ?: return

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // School Identity Header
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = SktNavyCard),
            border = androidx.compose.foundation.BorderStroke(1.5.dp, SktGold.copy(alpha = 0.7f))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("PARTNER SCHOOL PORTAL", fontSize = 9.5.sp, fontWeight = FontWeight.Bold, color = SktGold)
                        Text(school.name, fontSize = 17.sp, fontWeight = FontWeight.Black, color = SktWhite)
                        Text("${school.city}, ${school.state} • Code: ${school.code}", fontSize = 11.sp, color = SktElectricBlue)
                    }
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = SktNavySurface,
                        border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder)
                    ) {
                        Text(
                            text = school.partnershipModel.code,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Black,
                            color = SktGold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Partnership: ${school.partnershipModel.title}",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SktWhite
                )
                Text(
                    text = school.partnershipModel.description,
                    fontSize = 9.5.sp,
                    color = SktTextSecondary
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    MetricText("Enrolled Athletes", "${school.totalStudentsEnrolled}")
                    MetricText("Active Sports", "${school.activeSports.size}")
                    MetricText("Infrastructure", "${school.infrastructureRating} ★")
                    MetricText("Valid Until", school.contractValidUntil)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Student Roster Header & Add Button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("STUDENT SPORTS ROSTER", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = SktGold)
            Button(
                onClick = { viewModel.openAddStudentDialog(true) },
                colors = ButtonDefaults.buttonColors(containerColor = SktGold, contentColor = SktNavyDark),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(Icons.Default.PersonAdd, contentDescription = null, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Register Student", fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        athletes.forEach { ath ->
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
                                .clip(RoundedCornerShape(8.dp))
                                .background(SktNavySurface),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = ath.fullName.take(2).uppercase(),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Black,
                                color = SktGold
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(ath.fullName, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = SktWhite)
                            Text("${ath.athleteId} • ${ath.primarySport}", fontSize = 10.sp, color = SktTextSecondary)
                        }
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = SktGold.copy(alpha = 0.15f)
                        ) {
                            Text(
                                text = "Grade ${ath.fitnessGrade}",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = SktGold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(Icons.Default.ChevronRight, contentDescription = null, tint = SktTextSecondary)
                    }
                }
            }
        }
    }
}

@Composable
fun StudentDashboard(
    viewModel: SktSportsViewModel,
    athlete: Athlete,
    fitnessAssessments: List<FitnessAssessment>,
    certificates: List<DigitalCertificate>,
    sessions: List<TrainingSession>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Digital Athlete ID Card
        AthleteIdCard(
            athlete = athlete,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Quick Training Schedule
        Text("UPCOMING TRAINING SESSIONS", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = SktGold)
        Spacer(modifier = Modifier.height(8.dp))

        sessions.take(2).forEach { session ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = SktNavyCard),
                border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(session.sport.uppercase(), fontSize = 10.sp, fontWeight = FontWeight.Bold, color = SktElectricBlue)
                        Text(session.date + " • " + session.time, fontSize = 10.sp, color = SktGold)
                    }
                    Text(session.title, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = SktWhite)
                    Text("Coach: ${session.coachName}", fontSize = 11.sp, color = SktTextSecondary)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Student Fitness Radar / Scores
        val latestFit = fitnessAssessments.firstOrNull { it.athleteId == athlete.athleteId }
        if (latestFit != null) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SktNavyCard),
                border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("PHYSICAL FITNESS BENCHMARKS", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SktGold)
                        Surface(shape = RoundedCornerShape(6.dp), color = SktGreen.copy(alpha = 0.2f)) {
                            Text(
                                "Grade ${latestFit.overallGrade}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = SktGreen,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        FitnessScorePill("30m Sprint", "${latestFit.sprint30mSeconds}s")
                        FitnessScorePill("Beep Level", "${latestFit.beepTestScore}")
                        FitnessScorePill("Broad Jump", "${latestFit.broadJumpMeters}m")
                        FitnessScorePill("Push-ups", "${latestFit.pushupsOneMin}")
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Evaluator: ${latestFit.evaluatorCoach} • ${latestFit.assessmentDate}",
                        fontSize = 9.sp,
                        color = SktTextSecondary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Digital Certificates Locker
        Text("VERIFIED MERIT CERTIFICATES", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = SktGold)
        Spacer(modifier = Modifier.height(8.dp))

        certificates.take(2).forEach { cert ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable { viewModel.openCertificateDetail(cert) },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = SktNavyCard),
                border = androidx.compose.foundation.BorderStroke(1.dp, SktGold.copy(alpha = 0.5f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.WorkspacePremium, contentDescription = null, tint = SktGold, modifier = Modifier.size(28.dp))
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(cert.achievementRank, fontSize = 12.5.sp, fontWeight = FontWeight.Bold, color = SktWhite)
                            Text(cert.eventOrProgram, fontSize = 10.sp, color = SktTextSecondary, maxLines = 1)
                        }
                    }
                    Text("View", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SktGold)
                }
            }
        }
    }
}

@Composable
fun CoachDashboard(
    viewModel: SktSportsViewModel,
    coaches: List<Coach>,
    sessions: List<TrainingSession>,
    athletes: List<Athlete>
) {
    val coach = coaches.firstOrNull() ?: return

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Coach Profile Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = SktNavyCard),
            border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("CERTIFIED SPORTS COACH", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = SktGold)
                        Text(coach.name, fontSize = 16.sp, fontWeight = FontWeight.Black, color = SktWhite)
                        Text(coach.certification, fontSize = 11.sp, color = SktElectricBlue)
                    }

                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(SktGold),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.SportsScore, contentDescription = null, tint = SktNavyDark, modifier = Modifier.size(24.dp))
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    MetricText("Experience", "${coach.experienceYears} Years")
                    MetricText("Active Batches", "${coach.activeBatches}")
                    MetricText("Students Trained", "${coach.studentsTrained}+")
                    MetricText("Rating", "${coach.rating} ★")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Quick Coach Actions
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = { viewModel.openAddFitnessDialog(true) },
                colors = ButtonDefaults.buttonColors(containerColor = SktGold, contentColor = SktNavyDark),
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(Icons.Default.Speed, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Fitness Assessment", fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { viewModel.openAddStudentDialog(true) },
                colors = ButtonDefaults.buttonColors(containerColor = SktNavySurface, contentColor = SktWhite),
                border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder),
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(Icons.Default.PersonAdd, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Enroll Student", fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Training Sessions & Quick Attendance
        Text("TRAINING SESSIONS & ATTENDANCE", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = SktGold)
        Spacer(modifier = Modifier.height(8.dp))

        sessions.forEach { session ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = SktNavyCard),
                border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(session.sport, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SktElectricBlue)
                        Text("${session.date} • ${session.time}", fontSize = 10.sp, color = SktGold)
                    }
                    Text(session.title, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = SktWhite)
                    Text("Venue: ${session.schoolName}", fontSize = 11.sp, color = SktTextSecondary)

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Attendance: ${session.attendancePresent} / ${session.attendanceTotal}",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (session.isCompleted) SktGreen else SktOrange
                        )

                        if (!session.isCompleted) {
                            Button(
                                onClick = { viewModel.markAttendance(session.id, session.attendanceTotal) },
                                colors = ButtonDefaults.buttonColors(containerColor = SktGreen, contentColor = SktWhite),
                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text("Mark Attendance", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            }
                        } else {
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = SktGreen.copy(alpha = 0.2f)
                            ) {
                                Text("COMPLETED", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = SktGreen, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ParentDashboard(
    viewModel: SktSportsViewModel,
    athlete: Athlete,
    fitnessAssessments: List<FitnessAssessment>,
    certificates: List<DigitalCertificate>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Parent Header
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = SktNavyCard),
            border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("PARENT & GUARDIAN PORTAL", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = SktGold)
                Text("Welcome, ${athlete.parentName}", fontSize = 16.sp, fontWeight = FontWeight.Black, color = SktWhite)
                Text("Monitoring Athlete: ${athlete.fullName} (${athlete.athleteId})", fontSize = 11.sp, color = SktElectricBlue)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Child's ID Card
        AthleteIdCard(athlete = athlete, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(16.dp))

        // Attendance & Fee Status Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = SktNavyCard),
            border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text("MONTHLY ATTENDANCE & ACADEMY FEE", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SktGold)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Current Attendance", fontSize = 10.sp, color = SktTextSecondary)
                        Text("${athlete.attendancePercent}%", fontSize = 16.sp, fontWeight = FontWeight.Black, color = SktGreen)
                    }
                    Column {
                        Text("Sports Training Fee", fontSize = 10.sp, color = SktTextSecondary)
                        Text("PAID • ₹1,200/mo", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = SktGreen)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Notifications for parent
        Text("RECENT ACTIVITY ALERTS", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = SktGold)
        Spacer(modifier = Modifier.height(6.dp))

        listOf(
            "Present in morning football session (06:30 AM)",
            "Quarter final match scheduled this Saturday at Patliputra Sports Complex",
            "Fitness Grade upgraded to A+ by Coach Rajesh Kumar"
        ).forEach { msg ->
            Surface(
                shape = RoundedCornerShape(8.dp),
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
                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = SktGreen, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(msg, fontSize = 11.sp, color = SktWhite)
                }
            }
        }
    }
}

@Composable
fun CoordinatorDashboard(
    viewModel: SktSportsViewModel,
    schools: List<School>,
    coaches: List<Coach>,
    talentHunt: List<TalentHuntTrial>,
    tournaments: List<Tournament>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = SktNavyCard),
            border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("FIELD OPERATIONS & TALENT SCOUTING", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = SktGold)
                Text("Sports Coordinator Command", fontSize = 16.sp, fontWeight = FontWeight.Black, color = SktWhite)
                Text("Cross-school tournaments, coach scheduling & talent scouting trials.", fontSize = 11.sp, color = SktTextSecondary)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Talent Hunt Scouting Trials
        Text("SKT TALENT HUNT – SCOUTING PIPELINE", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = SktGold)
        Spacer(modifier = Modifier.height(8.dp))

        talentHunt.forEach { trial ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = SktNavyCard),
                border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(trial.athleteName, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = SktWhite)
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = SktGold.copy(alpha = 0.2f)
                        ) {
                            Text(
                                "Scout Score: ${trial.scoutScore}/100",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Black,
                                color = SktGold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Text("${trial.athleteId} • ${trial.sport} (${trial.ageGroup}) • ${trial.schoolName}", fontSize = 10.sp, color = SktElectricBlue)
                    Text("Recommendation: ${trial.scoutRecommendation}", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SktGreen)
                    Text("Strengths: " + trial.keyStrengths.joinToString(", "), fontSize = 9.5.sp, color = SktTextSecondary)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Coach Deployment Tracker
        Text("COACH DEPLOYMENTS & RATINGS", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = SktGold)
        Spacer(modifier = Modifier.height(8.dp))

        coaches.forEach { c ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = SktNavyCard),
                border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(c.name, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = SktWhite)
                        Text("${c.primarySport} • ${c.rating} ★", fontSize = 11.sp, color = SktGold)
                        Text("Assigned: " + c.assignedSchools.joinToString(", "), fontSize = 9.5.sp, color = SktTextSecondary)
                    }

                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = SktGreen.copy(alpha = 0.15f)
                    ) {
                        Text(
                            "${c.activeBatches} Batches",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = SktGreen,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun KpiCard(
    title: String,
    value: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    accentColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = SktNavyCard),
        border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(title, fontSize = 10.5.sp, color = SktTextSecondary, fontWeight = FontWeight.SemiBold)
                Icon(icon, contentDescription = null, tint = accentColor, modifier = Modifier.size(18.dp))
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(value, fontSize = 20.sp, fontWeight = FontWeight.Black, color = SktWhite)
            Text(subtitle, fontSize = 9.sp, color = accentColor, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
private fun MetricText(title: String, value: String) {
    Column {
        Text(title, fontSize = 9.sp, color = SktTextSecondary)
        Text(value, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = SktWhite)
    }
}

@Composable
private fun FitnessScorePill(title: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontSize = 15.sp, fontWeight = FontWeight.Black, color = SktGold)
        Text(title, fontSize = 9.sp, color = SktTextSecondary)
    }
}

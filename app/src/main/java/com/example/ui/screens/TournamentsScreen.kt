package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.MatchStatus
import com.example.model.Tournament
import com.example.model.TournamentStatus
import com.example.ui.theme.*
import com.example.ui.viewmodel.SktSportsViewModel

@Composable
fun TournamentsScreen(
    viewModel: SktSportsViewModel,
    tournaments: List<Tournament>
) {
    var selectedTournamentId by remember { mutableStateOf(tournaments.firstOrNull()?.id ?: "") }
    var selectedTab by remember { mutableStateOf(0) } // 0: Fixtures/Scores, 1: Points Table, 2: Certificates

    val activeTournament = tournaments.firstOrNull { it.id == selectedTournamentId } ?: tournaments.firstOrNull()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Screen Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "TOURNAMENT MANAGEMENT",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black,
                    color = SktGold
                )
                Text(
                    text = "Fixtures, live scorekeeping & rankings",
                    fontSize = 10.sp,
                    color = SktTextSecondary
                )
            }

            Surface(
                shape = RoundedCornerShape(8.dp),
                color = SktGold.copy(alpha = 0.15f)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.EmojiEvents, contentDescription = null, tint = SktGold, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("${tournaments.size} Events", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = SktGold)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Tournament Carousel Picker
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            tournaments.forEach { tour ->
                val isSel = tour.id == selectedTournamentId
                Surface(
                    onClick = { selectedTournamentId = tour.id },
                    shape = RoundedCornerShape(12.dp),
                    color = if (isSel) SktNavySurface else SktNavyCard,
                    border = androidx.compose.foundation.BorderStroke(1.dp, if (isSel) SktGold else SktBorder),
                    modifier = Modifier.width(220.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = tour.sport.uppercase(),
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = SktElectricBlue
                            )
                            StatusBadge(tour.status)
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = tour.title,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = SktWhite,
                            maxLines = 2
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${tour.participatingTeamsCount} Teams • ${tour.categoryAge}",
                            fontSize = 9.5.sp,
                            color = SktTextSecondary
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Sub Tabs: Fixtures, Standings, Certificates
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = SktNavyCard,
            contentColor = SktGold,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                    color = SktGold
                )
            }
        ) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = { Text("Match Fixtures", fontSize = 11.sp, fontWeight = FontWeight.Bold) }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = { Text("Points Table", fontSize = 11.sp, fontWeight = FontWeight.Bold) }
            )
            Tab(
                selected = selectedTab == 2,
                onClick = { selectedTab = 2 },
                text = { Text("Awards & Honors", fontSize = 11.sp, fontWeight = FontWeight.Bold) }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (activeTournament != null) {
            when (selectedTab) {
                0 -> {
                    // Match Fixtures & Live Scorekeeper
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(activeTournament.matches) { match ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
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
                                        Text(match.round, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SktElectricBlue)
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            if (match.status == MatchStatus.LIVE) {
                                                Box(
                                                    modifier = Modifier
                                                        .size(8.dp)
                                                        .clip(CircleShape)
                                                        .background(SktRed)
                                                )
                                                Spacer(modifier = Modifier.width(4.dp))
                                                Text("LIVE NOW", fontSize = 9.sp, fontWeight = FontWeight.Black, color = SktRed)
                                            } else {
                                                Text(match.timeSlot, fontSize = 9.5.sp, color = SktGold)
                                            }
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = match.teamA,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = SktWhite,
                                            modifier = Modifier.weight(1f)
                                        )

                                        Surface(
                                            shape = RoundedCornerShape(8.dp),
                                            color = SktNavyDark,
                                            border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder),
                                            modifier = Modifier.padding(horizontal = 12.dp)
                                        ) {
                                            Text(
                                                text = "${match.scoreA}  :  ${match.scoreB}",
                                                fontSize = 16.sp,
                                                fontFamily = FontFamily.Monospace,
                                                fontWeight = FontWeight.Black,
                                                color = SktGold,
                                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                            )
                                        }

                                        Text(
                                            text = match.teamB,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = SktWhite,
                                            modifier = Modifier.weight(1f),
                                            textAlign = androidx.compose.ui.text.style.TextAlign.End
                                        )
                                    }

                                    if (match.details.isNotBlank()) {
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Text(
                                            text = match.details,
                                            fontSize = 9.5.sp,
                                            color = SktTextSecondary
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Button(
                                        onClick = { viewModel.openScoreUpdateDialog(match) },
                                        colors = ButtonDefaults.buttonColors(containerColor = SktNavySurface, contentColor = SktGold),
                                        border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder),
                                        modifier = Modifier.fillMaxWidth(),
                                        shape = RoundedCornerShape(8.dp),
                                        contentPadding = PaddingValues(vertical = 4.dp)
                                    ) {
                                        Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(14.dp))
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text("Update Match Score & Officials", fontSize = 10.5.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }
                    }
                }
                1 -> {
                    // Points Table & Rankings
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = SktNavyCard),
                        border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text("LEAGUE POINTS TABLE", fontSize = 12.sp, fontWeight = FontWeight.Black, color = SktGold)
                            Spacer(modifier = Modifier.height(10.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("TEAM", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = SktTextSecondary, modifier = Modifier.weight(2f))
                                Text("P", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = SktTextSecondary, modifier = Modifier.weight(0.5f))
                                Text("W", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = SktTextSecondary, modifier = Modifier.weight(0.5f))
                                Text("GD", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = SktTextSecondary, modifier = Modifier.weight(0.7f))
                                Text("PTS", fontSize = 10.sp, fontWeight = FontWeight.Black, color = SktGold, modifier = Modifier.weight(0.7f))
                            }

                            Divider(color = SktBorder, thickness = 1.dp)

                            val standings = listOf(
                                StandingsRow("St. Xavier's Strikers", 3, 3, "+6", 9),
                                StandingsRow("DPS Super Falcons", 3, 2, "+3", 6),
                                StandingsRow("Loyola Warriors", 3, 1, "0", 3),
                                StandingsRow("Vikramshila Knights", 3, 1, "-2", 3),
                                StandingsRow("Buddha Heritage Titans", 3, 0, "-7", 0)
                            )

                            standings.forEachIndexed { index, row ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "${index + 1}. ${row.team}",
                                        fontSize = 11.5.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (index < 2) SktWhite else SktTextSecondary,
                                        modifier = Modifier.weight(2f)
                                    )
                                    Text("${row.played}", fontSize = 11.sp, color = SktWhite, modifier = Modifier.weight(0.5f))
                                    Text("${row.won}", fontSize = 11.sp, color = SktGreen, modifier = Modifier.weight(0.5f))
                                    Text(row.gd, fontSize = 11.sp, color = SktTextSecondary, modifier = Modifier.weight(0.7f))
                                    Text("${row.points}", fontSize = 12.sp, fontWeight = FontWeight.Black, color = SktGold, modifier = Modifier.weight(0.7f))
                                }
                                if (index < standings.lastIndex) Divider(color = SktBorder.copy(alpha = 0.5f), thickness = 0.5.dp)
                            }
                        }
                    }
                }
                2 -> {
                    // Awards & Digital Certificates
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = SktNavyCard),
                        border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text("AWARDS & PODIUM RECOGNITION", fontSize = 12.sp, fontWeight = FontWeight.Black, color = SktGold)
                            Spacer(modifier = Modifier.height(10.dp))

                            AwardItem(title = "Winner & State Cup Champions", team = activeTournament.winnerTeam ?: "Final Underway (St. Xavier's vs DPS)", icon = Icons.Default.EmojiEvents, color = SktGold)
                            Spacer(modifier = Modifier.height(8.dp))
                            AwardItem(title = "Runner-Up Trophy", team = activeTournament.runnerUpTeam ?: "To be decided in Grand Final", icon = Icons.Default.MilitaryTech, color = SktWhite)
                            Spacer(modifier = Modifier.height(8.dp))
                            AwardItem(title = "Golden Boot / Best Performer", team = "Aarav Sharma (4 Goals)", icon = Icons.Default.Star, color = SktElectricBlue)

                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                onClick = { /* open certificate sample */ },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(containerColor = SktGold, contentColor = SktNavyDark),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Icon(Icons.Default.CardMembership, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Generate Tournament Certificates", fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}

private data class StandingsRow(val team: String, val played: Int, val won: Int, val gd: String, val points: Int)

@Composable
private fun StatusBadge(status: TournamentStatus) {
    val (color, text) = when (status) {
        TournamentStatus.ONGOING -> SktGreen to "ONGOING"
        TournamentStatus.UPCOMING -> SktGold to "UPCOMING"
        TournamentStatus.COMPLETED -> SktTextSecondary to "CONCLUDED"
    }
    Surface(
        shape = RoundedCornerShape(4.dp),
        color = color.copy(alpha = 0.2f)
    ) {
        Text(
            text = text,
            fontSize = 8.5.sp,
            fontWeight = FontWeight.Black,
            color = color,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
        )
    }
}

@Composable
private fun AwardItem(title: String, team: String, icon: androidx.compose.ui.graphics.vector.ImageVector, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(title, fontSize = 10.sp, color = SktTextSecondary)
            Text(team, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = SktWhite)
        }
    }
}

package com.example.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Athlete
import com.example.model.FitnessAssessment
import com.example.ui.theme.*
import com.example.ui.viewmodel.SktSportsViewModel

@Composable
fun FitnessScreen(
    viewModel: SktSportsViewModel,
    fitnessAssessments: List<FitnessAssessment>,
    athletes: List<Athlete>
) {
    var selectedTestCategory by remember { mutableStateOf("All Tests") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "FITNESS & PERFORMANCE TRACKER",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black,
                    color = SktGold
                )
                Text(
                    text = "Standardized Athletic Testing & Benchmarking",
                    fontSize = 10.sp,
                    color = SktTextSecondary
                )
            }

            Button(
                onClick = { viewModel.openAddFitnessDialog(true) },
                colors = ButtonDefaults.buttonColors(containerColor = SktGold, contentColor = SktNavyDark),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Record Test", fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Performance Graph: Benchmark Metrics vs State Athletic Standards
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
                            text = "SKT ATHLETIC READINESS BENCHMARK",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = SktGold
                        )
                        Text(
                            text = "Squad Average vs State Youth Standard (%)",
                            fontSize = 9.sp,
                            color = SktTextSecondary
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = SktGreen.copy(alpha = 0.2f)
                    ) {
                        Text(
                            text = "+14.8% Above Norm",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = SktGreen,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Custom Bar Chart
                FitnessBarChart(
                    metrics = listOf(
                        ChartMetric("Sprint 30m", 92, SktGold),
                        ChartMetric("Beep Test", 88, SktElectricBlue),
                        ChartMetric("Broad Jump", 84, SktGreen),
                        ChartMetric("Push-ups", 86, SktOrange),
                        ChartMetric("Agility", 94, SktPurple),
                        ChartMetric("Flexibility", 90, Color(0xFF14B8A6))
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    LegendItem("Gold", "Speed / Power", SktGold)
                    LegendItem("Blue", "Cardio Endurance", SktElectricBlue)
                    LegendItem("Purple", "Agility / Reflex", SktPurple)
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Core 6 Testing Disciplines
        Text(
            text = "STANDARDIZED ASSESSMENT MODULES",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = SktGold
        )
        Spacer(modifier = Modifier.height(8.dp))

        val disciplines = listOf(
            DisciplineItem("30m Acceleration Sprint", "Measures explosive burst, stride frequency & peak velocity", "Target: < 4.4s", Icons.Default.DirectionsRun, SktGold),
            DisciplineItem("Multi-Stage Shuttle (Beep Test)", "Maximal aerobic cardiovascular endurance & recovery", "Target: Level 9+", Icons.Default.Favorite, SktRed),
            DisciplineItem("Standing Broad Jump", "Explosive lower body power & kinetic chain drive", "Target: > 2.10m", Icons.Default.NorthEast, SktGreen),
            DisciplineItem("1-Minute Push-up Test", "Upper body muscular endurance & shoulder girdle stability", "Target: > 30 reps", Icons.Default.FitnessCenter, SktOrange),
            DisciplineItem("Illinois Agility Run", "Deceleration, change of direction & footwork mobility", "Target: < 15.2s", Icons.Default.AltRoute, SktPurple),
            DisciplineItem("Sit & Reach Flexibility", "Hamstring & lower back range of motion for injury prevention", "Target: > 28cm", Icons.Default.SelfImprovement, Color(0xFF14B8A6))
        )

        disciplines.forEach { disc ->
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
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(disc.accentColor.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(disc.icon, contentDescription = null, tint = disc.accentColor, modifier = Modifier.size(20.dp))
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(disc.title, fontSize = 12.5.sp, fontWeight = FontWeight.Bold, color = SktWhite)
                        Text(disc.desc, fontSize = 10.sp, color = SktTextSecondary, maxLines = 1)
                    }

                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = SktNavySurface,
                        border = androidx.compose.foundation.BorderStroke(0.5.dp, SktBorder)
                    ) {
                        Text(
                            text = disc.target,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = disc.accentColor,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Recent Assessment History
        Text(
            text = "RECENT EVALUATIONS & CERTIFIED LOGS",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = SktGold
        )
        Spacer(modifier = Modifier.height(8.dp))

        fitnessAssessments.forEach { fit ->
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
                        Column {
                            Text(fit.athleteName, fontSize = 13.5.sp, fontWeight = FontWeight.Black, color = SktWhite)
                            Text("${fit.athleteId} • ${fit.sport}", fontSize = 10.sp, color = SktElectricBlue)
                        }

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = SktGold.copy(alpha = 0.2f),
                            border = androidx.compose.foundation.BorderStroke(1.dp, SktGold.copy(alpha = 0.5f))
                        ) {
                            Text(
                                "Grade ${fit.overallGrade}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Black,
                                color = SktGold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("30m Sprint: ${fit.sprint30mSeconds}s", fontSize = 10.sp, color = SktWhite, fontWeight = FontWeight.SemiBold)
                        Text("Beep: ${fit.beepTestScore}", fontSize = 10.sp, color = SktWhite, fontWeight = FontWeight.SemiBold)
                        Text("Broad Jump: ${fit.broadJumpMeters}m", fontSize = 10.sp, color = SktWhite, fontWeight = FontWeight.SemiBold)
                        Text("Pushups: ${fit.pushupsOneMin}", fontSize = 10.sp, color = SktWhite, fontWeight = FontWeight.SemiBold)
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "“${fit.notes}”",
                        fontSize = 10.sp,
                        color = SktTextSecondary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Evaluator: ${fit.evaluatorCoach} • ${fit.assessmentDate}",
                        fontSize = 9.sp,
                        color = SktGold.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}

private data class ChartMetric(val label: String, val value: Int, val color: Color)
private data class DisciplineItem(val title: String, val desc: String, val target: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val accentColor: Color)

@Composable
private fun FitnessBarChart(metrics: List<ChartMetric>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        metrics.forEach { metric ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = metric.label,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = SktTextSecondary,
                    modifier = Modifier.width(80.dp)
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(16.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(SktNavyDark)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(metric.value / 100f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(metric.color)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "${metric.value}%",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Black,
                    color = metric.color,
                    modifier = Modifier.width(36.dp)
                )
            }
        }
    }
}

@Composable
private fun LegendItem(label: String, desc: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(desc, fontSize = 9.sp, color = SktTextSecondary)
    }
}

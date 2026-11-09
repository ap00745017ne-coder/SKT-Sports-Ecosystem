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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Athlete
import com.example.ui.components.AthleticQrCode
import com.example.ui.theme.*
import com.example.ui.viewmodel.SktSportsViewModel

@Composable
fun AthleteRecordsScreen(
    viewModel: SktSportsViewModel,
    athletes: List<Athlete>
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedSport by remember { mutableStateOf("All") }
    var showVerifierSheet by remember { mutableStateOf(false) }
    var scanInput by remember { mutableStateOf("SKT-2026-BIH-00042") }
    val verifiedAthlete by viewModel.verifiedAthleteResult.collectAsState()

    val filtered = athletes.filter {
        (selectedSport == "All" || it.primarySport == selectedSport) &&
        (searchQuery.isBlank() ||
            it.fullName.contains(searchQuery, ignoreCase = true) ||
            it.athleteId.contains(searchQuery, ignoreCase = true) ||
            it.schoolName.contains(searchQuery, ignoreCase = true))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
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
                    text = "DIGITAL ATHLETE REGISTRY",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black,
                    color = SktGold
                )
                Text(
                    text = "Searchable records & QR verification",
                    fontSize = 10.sp,
                    color = SktTextSecondary
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                // QR Scanner / Verifier Button
                Button(
                    onClick = { showVerifierSheet = true },
                    colors = ButtonDefaults.buttonColors(containerColor = SktNavySurface, contentColor = SktGold),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SktGold.copy(alpha = 0.6f)),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Icon(Icons.Default.QrCodeScanner, contentDescription = "Verify QR", modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Verify ID", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = { viewModel.openAddStudentDialog(true) },
                    colors = ButtonDefaults.buttonColors(containerColor = SktGold, contentColor = SktNavyDark),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Register", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search by Athlete ID (e.g. SKT-2026...), Name, School", fontSize = 12.sp) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = SktGold) },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(Icons.Default.Close, contentDescription = null, tint = SktTextSecondary)
                    }
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = SktGold,
                unfocusedBorderColor = SktBorder,
                focusedTextColor = SktWhite,
                unfocusedTextColor = SktWhite,
                focusedContainerColor = SktNavyCard,
                unfocusedContainerColor = SktNavyCard
            ),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Sports Filter Chips
        val sports = listOf("All", "Football", "Badminton", "Cricket", "Kabaddi", "Basketball", "Athletics", "Volleyball")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            sports.forEach { s ->
                val isSel = selectedSport == s
                FilterChip(
                    selected = isSel,
                    onClick = { selectedSport = s },
                    label = { Text(s, fontSize = 11.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = SktGold,
                        selectedLabelColor = SktNavyDark,
                        containerColor = SktNavyCard,
                        labelColor = SktTextPrimary
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // List of Athletes
        Text(
            text = "ATHLETES FOUND (${filtered.size})",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = SktTextSecondary
        )

        Spacer(modifier = Modifier.height(6.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filtered, key = { it.id }) { athlete ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.openAthleteIdCard(athlete) },
                    shape = RoundedCornerShape(14.dp),
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
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            AthleticQrCode(
                                code = athlete.athleteId,
                                modifier = Modifier.size(46.dp)
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = athlete.fullName,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Black,
                                        color = SktWhite
                                    )
                                    if (athlete.isUdaanAthlete) {
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(4.dp))
                                                .background(SktPurple.copy(alpha = 0.3f))
                                                .padding(horizontal = 4.dp, vertical = 1.dp)
                                        ) {
                                            Text("UDAAN", fontSize = 8.sp, fontWeight = FontWeight.Black, color = SktWhite)
                                        }
                                    }
                                }

                                Text(
                                    text = athlete.athleteId,
                                    fontSize = 11.sp,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Bold,
                                    color = SktGold
                                )

                                Text(
                                    text = "${athlete.schoolName} • Grade ${athlete.fitnessGrade}",
                                    fontSize = 10.sp,
                                    color = SktTextSecondary
                                )
                            }
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = SktNavySurface,
                                border = androidx.compose.foundation.BorderStroke(0.5.dp, SktBorder)
                            ) {
                                Text(
                                    text = athlete.primarySport,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SktElectricBlue,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "View ID Card",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = SktGold
                            )
                        }
                    }
                }
            }
        }
    }

    // QR Verification Sheet / Dialog
    if (showVerifierSheet) {
        AlertDialog(
            onDismissRequest = {
                showVerifierSheet = false
                viewModel.clearVerifiedResult()
            },
            containerColor = SktNavyCard,
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.QrCodeScanner, contentDescription = null, tint = SktGold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("QR ATHLETE ID VERIFIER", fontSize = 14.sp, fontWeight = FontWeight.Black, color = SktGold)
                }
            },
            text = {
                Column {
                    Text(
                        text = "Scan or enter any SKT Athlete ID to verify authenticity against Shiv Kheltantra Private Limited central register.",
                        fontSize = 11.sp,
                        color = SktTextSecondary
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = scanInput,
                        onValueChange = { scanInput = it },
                        label = { Text("Athlete ID / Code", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = SktGold,
                            unfocusedBorderColor = SktBorder,
                            focusedTextColor = SktWhite,
                            unfocusedTextColor = SktWhite
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = { viewModel.verifyAthleteId(scanInput) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = SktGold, contentColor = SktNavyDark)
                    ) {
                        Text("Verify Authenticity", fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    if (verifiedAthlete != null) {
                        val ath = verifiedAthlete!!
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = SktGreen.copy(alpha = 0.15f),
                            border = androidx.compose.foundation.BorderStroke(1.dp, SktGreen),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = SktGreen, modifier = Modifier.size(18.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("AUTHENTIC ATHLETE RECORD", fontSize = 11.sp, fontWeight = FontWeight.Black, color = SktGreen)
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(ath.fullName, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = SktWhite)
                                Text("${ath.athleteId} • ${ath.schoolName}", fontSize = 10.sp, color = SktTextSecondary)
                                Text("Sport: ${ath.primarySport} • Fitness Grade: ${ath.fitnessGrade}", fontSize = 10.sp, color = SktGold)
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    showVerifierSheet = false
                    viewModel.clearVerifiedResult()
                }) {
                    Text("Close", color = SktGold)
                }
            }
        )
    }
}

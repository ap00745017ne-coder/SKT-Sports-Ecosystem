package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.model.*
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStudentDialog(
    schools: List<School>,
    sports: List<SportsCategoryItem>,
    onDismiss: () -> Unit,
    onSubmit: (name: String, age: Int, gender: String, school: String, grade: String, primarySport: String, secondarySport: String, isUdaan: Boolean) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var ageText by remember { mutableStateOf("14") }
    var gender by remember { mutableStateOf("Female") }
    var selectedSchool by remember { mutableStateOf(schools.firstOrNull()?.name ?: "St. Xavier's High School") }
    var grade by remember { mutableStateOf("Grade 9") }
    var primarySport by remember { mutableStateOf("Football") }
    var secondarySport by remember { mutableStateOf("Athletics") }
    var isUdaan by remember { mutableStateOf(true) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = SktNavyCard),
            border = androidx.compose.foundation.BorderStroke(1.5.dp, SktBorder)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "REGISTER NEW ATHLETE",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Black,
                            color = SktGold
                        )
                        Text(
                            text = "Generates Unique Digital Athlete ID",
                            fontSize = 10.sp,
                            color = SktTextSecondary
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = SktTextSecondary)
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Full Name", fontSize = 12.sp) },
                    placeholder = { Text("e.g. Riya Verma") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = SktGold,
                        unfocusedBorderColor = SktBorder,
                        focusedTextColor = SktWhite,
                        unfocusedTextColor = SktWhite
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedTextField(
                        value = ageText,
                        onValueChange = { ageText = it },
                        label = { Text("Age", fontSize = 12.sp) },
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = SktGold,
                            unfocusedBorderColor = SktBorder,
                            focusedTextColor = SktWhite,
                            unfocusedTextColor = SktWhite
                        ),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = grade,
                        onValueChange = { grade = it },
                        label = { Text("Grade / Class", fontSize = 12.sp) },
                        modifier = Modifier.weight(1.5f),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = SktGold,
                            unfocusedBorderColor = SktBorder,
                            focusedTextColor = SktWhite,
                            unfocusedTextColor = SktWhite
                        ),
                        singleLine = true
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Gender Selection
                Text("Gender", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = SktTextSecondary)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf("Female", "Male", "Other").forEach { g ->
                        val isSelected = gender == g
                        FilterChip(
                            selected = isSelected,
                            onClick = {
                                gender = g
                                if (g == "Female") isUdaan = true
                            },
                            label = { Text(g, fontSize = 11.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = SktGold,
                                selectedLabelColor = SktNavyDark,
                                containerColor = SktNavySurface,
                                labelColor = SktTextPrimary
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // School Picker
                Text("School / Academy", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = SktTextSecondary)
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    schools.take(4).forEach { school ->
                        val isSel = selectedSchool == school.name
                        Surface(
                            onClick = { selectedSchool = school.name },
                            shape = RoundedCornerShape(8.dp),
                            color = if (isSel) SktNavySurface else SktNavyDark,
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (isSel) SktGold else SktBorder
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = isSel,
                                    onClick = { selectedSchool = school.name },
                                    colors = RadioButtonDefaults.colors(selectedColor = SktGold)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = school.name,
                                    fontSize = 12.sp,
                                    color = if (isSel) SktGold else SktTextPrimary,
                                    fontWeight = if (isSel) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Sports pickers
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = primarySport,
                        onValueChange = { primarySport = it },
                        label = { Text("Primary Sport", fontSize = 11.sp) },
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = SktGold,
                            unfocusedBorderColor = SktBorder,
                            focusedTextColor = SktWhite,
                            unfocusedTextColor = SktWhite
                        )
                    )
                    OutlinedTextField(
                        value = secondarySport,
                        onValueChange = { secondarySport = it },
                        label = { Text("Secondary Sport", fontSize = 11.sp) },
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = SktGold,
                            unfocusedBorderColor = SktBorder,
                            focusedTextColor = SktWhite,
                            unfocusedTextColor = SktWhite
                        )
                    )
                }

                if (gender == "Female") {
                    Spacer(modifier = Modifier.height(12.dp))
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = SktPurple.copy(alpha = 0.2f),
                        border = androidx.compose.foundation.BorderStroke(1.dp, SktPurple)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text("SKT UDAAN Enrollment", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = SktWhite)
                                Text("Include in Girls Sports Development Initiative", fontSize = 9.sp, color = SktTextSecondary)
                            }
                            Switch(
                                checked = isUdaan,
                                onCheckedChange = { isUdaan = it },
                                colors = SwitchDefaults.colors(checkedThumbColor = SktPurple, checkedTrackColor = SktGold)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                Button(
                    onClick = {
                        if (name.isNotBlank()) {
                            val age = ageText.toIntOrNull() ?: 14
                            onSubmit(name, age, gender, selectedSchool, grade, primarySport, secondarySport, isUdaan)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = SktGold, contentColor = SktNavyDark),
                    shape = RoundedCornerShape(10.dp),
                    enabled = name.isNotBlank()
                ) {
                    Icon(Icons.Default.Badge, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Register & Generate Athlete ID", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
            }
        }
    }
}

@Composable
fun AddSchoolDialog(
    onDismiss: () -> Unit,
    onSubmit: (name: String, city: String, model: PartnershipModel, count: Int, sports: List<String>, principal: String, coord: String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("Patna") }
    var selectedModel by remember { mutableStateOf(PartnershipModel.FIFTY_FIFTY_INVESTMENT) }
    var studentCountText by remember { mutableStateOf("350") }
    var principal by remember { mutableStateOf("") }
    var coordinator by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = SktNavyCard),
            border = androidx.compose.foundation.BorderStroke(1.5.dp, SktBorder)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "NEW PARTNER SCHOOL",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Black,
                        color = SktGold
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = SktTextSecondary)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("School Name", fontSize = 12.sp) },
                    placeholder = { Text("e.g. Carmel Academy") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = SktGold,
                        unfocusedBorderColor = SktBorder,
                        focusedTextColor = SktWhite,
                        unfocusedTextColor = SktWhite
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = city,
                        onValueChange = { city = it },
                        label = { Text("City / District", fontSize = 12.sp) },
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = SktGold,
                            unfocusedBorderColor = SktBorder,
                            focusedTextColor = SktWhite,
                            unfocusedTextColor = SktWhite
                        )
                    )
                    OutlinedTextField(
                        value = studentCountText,
                        onValueChange = { studentCountText = it },
                        label = { Text("Target Students", fontSize = 12.sp) },
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = SktGold,
                            unfocusedBorderColor = SktBorder,
                            focusedTextColor = SktWhite,
                            unfocusedTextColor = SktWhite
                        )
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text("Partnership Model (5 Models)", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SktTextSecondary)
                PartnershipModel.values().forEach { model ->
                    val isSel = selectedModel == model
                    Surface(
                        onClick = { selectedModel = model },
                        shape = RoundedCornerShape(8.dp),
                        color = if (isSel) SktNavySurface else SktNavyDark,
                        border = androidx.compose.foundation.BorderStroke(1.dp, if (isSel) SktGold else SktBorder),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(
                                    selected = isSel,
                                    onClick = { selectedModel = model },
                                    colors = RadioButtonDefaults.colors(selectedColor = SktGold)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = model.title,
                                    fontSize = 11.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSel) SktGold else SktWhite
                                )
                            }
                            Text(
                                text = model.description,
                                fontSize = 9.sp,
                                color = SktTextSecondary,
                                modifier = Modifier.padding(start = 32.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Button(
                    onClick = {
                        if (name.isNotBlank()) {
                            val count = studentCountText.toIntOrNull() ?: 200
                            onSubmit(
                                name,
                                city,
                                selectedModel,
                                count,
                                listOf("Football", "Athletics", "Kabaddi", "Badminton"),
                                if (principal.isNotBlank()) principal else "School Principal",
                                if (coordinator.isNotBlank()) coordinator else "Sports In-charge"
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = SktGold, contentColor = SktNavyDark),
                    shape = RoundedCornerShape(10.dp),
                    enabled = name.isNotBlank()
                ) {
                    Text("Add School Agreement", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
            }
        }
    }
}

@Composable
fun RecordFitnessDialog(
    athletes: List<Athlete>,
    onDismiss: () -> Unit,
    onSubmit: (athlete: Athlete, sprint: Float, beep: Float, broadJump: Float, pushups: Int, coachName: String, notes: String) -> Unit
) {
    var selectedAthlete by remember { mutableStateOf(athletes.firstOrNull()) }
    var sprintText by remember { mutableStateOf("4.25") }
    var beepText by remember { mutableStateOf("9.6") }
    var broadJumpText by remember { mutableStateOf("2.20") }
    var pushupsText by remember { mutableStateOf("34") }
    var coachName by remember { mutableStateOf("Coach Rajesh Kumar") }
    var notes by remember { mutableStateOf("Excellent cardiovascular stamina and explosive reaction time.") }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = SktNavyCard),
            border = androidx.compose.foundation.BorderStroke(1.5.dp, SktBorder)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "RECORD FITNESS ASSESSMENT",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black,
                        color = SktGold
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = SktTextSecondary)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text("Select Athlete", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SktTextSecondary)
                athletes.take(4).forEach { ath ->
                    val isSel = selectedAthlete?.athleteId == ath.athleteId
                    Surface(
                        onClick = { selectedAthlete = ath },
                        shape = RoundedCornerShape(8.dp),
                        color = if (isSel) SktNavySurface else SktNavyDark,
                        border = androidx.compose.foundation.BorderStroke(1.dp, if (isSel) SktGold else SktBorder),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = isSel,
                                onClick = { selectedAthlete = ath },
                                colors = RadioButtonDefaults.colors(selectedColor = SktGold)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Column {
                                Text(ath.fullName, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = SktWhite)
                                Text("${ath.athleteId} • ${ath.primarySport}", fontSize = 10.sp, color = SktTextSecondary)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = sprintText,
                        onValueChange = { sprintText = it },
                        label = { Text("30m Sprint (s)", fontSize = 11.sp) },
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = SktGold, unfocusedBorderColor = SktBorder, focusedTextColor = SktWhite, unfocusedTextColor = SktWhite)
                    )
                    OutlinedTextField(
                        value = beepText,
                        onValueChange = { beepText = it },
                        label = { Text("Beep Level", fontSize = 11.sp) },
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = SktGold, unfocusedBorderColor = SktBorder, focusedTextColor = SktWhite, unfocusedTextColor = SktWhite)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = broadJumpText,
                        onValueChange = { broadJumpText = it },
                        label = { Text("Broad Jump (m)", fontSize = 11.sp) },
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = SktGold, unfocusedBorderColor = SktBorder, focusedTextColor = SktWhite, unfocusedTextColor = SktWhite)
                    )
                    OutlinedTextField(
                        value = pushupsText,
                        onValueChange = { pushupsText = it },
                        label = { Text("Push-ups (1m)", fontSize = 11.sp) },
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = SktGold, unfocusedBorderColor = SktBorder, focusedTextColor = SktWhite, unfocusedTextColor = SktWhite)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Coach Assessment Notes", fontSize = 11.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 2,
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = SktGold, unfocusedBorderColor = SktBorder, focusedTextColor = SktWhite, unfocusedTextColor = SktWhite)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val ath = selectedAthlete ?: return@Button
                        val sprint = sprintText.toFloatOrNull() ?: 4.5f
                        val beep = beepText.toFloatOrNull() ?: 9.0f
                        val broad = broadJumpText.toFloatOrNull() ?: 2.1f
                        val pushups = pushupsText.toIntOrNull() ?: 30
                        onSubmit(ath, sprint, beep, broad, pushups, coachName, notes)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = SktGold, contentColor = SktNavyDark),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(Icons.Default.DirectionsRun, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Submit Assessment & Grade", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
            }
        }
    }
}

@Composable
fun UpdateScoreDialog(
    match: TournamentMatch,
    onDismiss: () -> Unit,
    onSubmit: (scoreA: Int, scoreB: Int, isFinal: Boolean) -> Unit
) {
    var scoreA by remember { mutableStateOf(match.scoreA.toString()) }
    var scoreB by remember { mutableStateOf(match.scoreB.toString()) }
    var isFinal by remember { mutableStateOf(match.status == MatchStatus.COMPLETED) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = SktNavyCard),
            border = androidx.compose.foundation.BorderStroke(1.5.dp, SktBorder)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "LIVE MATCH SCOREKEEPER",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Black,
                    color = SktGold
                )
                Text(
                    text = match.round,
                    fontSize = 11.sp,
                    color = SktTextSecondary
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(match.teamA, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = SktWhite, maxLines = 1)
                        Spacer(modifier = Modifier.height(6.dp))
                        OutlinedTextField(
                            value = scoreA,
                            onValueChange = { scoreA = it },
                            modifier = Modifier.width(70.dp),
                            textStyle = LocalTextStyle.current.copy(textAlign = androidx.compose.ui.text.style.TextAlign.Center, fontSize = 20.sp, fontWeight = FontWeight.Black),
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = SktGold, unfocusedBorderColor = SktBorder, focusedTextColor = SktGold, unfocusedTextColor = SktWhite),
                            singleLine = true
                        )
                    }

                    Text("VS", fontSize = 14.sp, fontWeight = FontWeight.Black, color = SktElectricBlue)

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(match.teamB, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = SktWhite, maxLines = 1)
                        Spacer(modifier = Modifier.height(6.dp))
                        OutlinedTextField(
                            value = scoreB,
                            onValueChange = { scoreB = it },
                            modifier = Modifier.width(70.dp),
                            textStyle = LocalTextStyle.current.copy(textAlign = androidx.compose.ui.text.style.TextAlign.Center, fontSize = 20.sp, fontWeight = FontWeight.Black),
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = SktGold, unfocusedBorderColor = SktBorder, focusedTextColor = SktGold, unfocusedTextColor = SktWhite),
                            singleLine = true
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { isFinal = !isFinal }
                ) {
                    Checkbox(
                        checked = isFinal,
                        onCheckedChange = { isFinal = it },
                        colors = CheckboxDefaults.colors(checkedColor = SktGreen)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Mark as Match Concluded (Final Result)", fontSize = 12.sp, color = SktTextPrimary)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = SktTextPrimary)
                    ) {
                        Text("Cancel")
                    }

                    Button(
                        onClick = {
                            val sA = scoreA.toIntOrNull() ?: 0
                            val sB = scoreB.toIntOrNull() ?: 0
                            onSubmit(sA, sB, isFinal)
                        },
                        modifier = Modifier.weight(1.3f),
                        colors = ButtonDefaults.buttonColors(containerColor = SktGold, contentColor = SktNavyDark)
                    ) {
                        Text("Save Score", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

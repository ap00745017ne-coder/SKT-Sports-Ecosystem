package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.model.*
import com.example.ui.components.*
import com.example.ui.screens.*
import com.example.ui.theme.*
import com.example.ui.viewmodel.SktNavDestination
import com.example.ui.viewmodel.SktSportsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                SktSportsApp()
            }
        }
    }
}

@Composable
fun SktSportsApp(viewModel: SktSportsViewModel = viewModel()) {
    val currentRole by viewModel.currentRole.collectAsState()
    val currentDestination by viewModel.currentDestination.collectAsState()
    val selectedAthlete by viewModel.currentSelectedAthlete.collectAsState()

    val schools by viewModel.schools.collectAsState()
    val athletes by viewModel.athletes.collectAsState()
    val coaches by viewModel.coaches.collectAsState()
    val tournaments by viewModel.tournaments.collectAsState()
    val fitnessAssessments by viewModel.fitnessAssessments.collectAsState()
    val trainingSessions by viewModel.trainingSessions.collectAsState()
    val certificates by viewModel.certificates.collectAsState()
    val financials by viewModel.financials.collectAsState()
    val talentHunt by viewModel.talentHunt.collectAsState()
    val udaanInitiatives by viewModel.udaanInitiatives.collectAsState()
    val sportsCategories by viewModel.sportsCategories.collectAsState()
    val notifications by viewModel.notifications.collectAsState()

    // Dialog States
    val showAddStudent by viewModel.showAddStudentDialog.collectAsState()
    val showAddSchool by viewModel.showAddSchoolDialog.collectAsState()
    val showAddFitness by viewModel.showAddFitnessDialog.collectAsState()
    val showScoreMatch by viewModel.showScoreUpdateDialog.collectAsState()
    val showCertificate by viewModel.showCertificateDetail.collectAsState()
    val showAthleteCard by viewModel.showAthleteIdCard.collectAsState()

    var showRoleSelector by remember { mutableStateOf(false) }
    var showNotificationDialog by remember { mutableStateOf(false) }

    // System BackHandler
    BackHandler(enabled = currentDestination != SktNavDestination.DASHBOARD) {
        viewModel.navigateTo(SktNavDestination.DASHBOARD)
    }

    Scaffold(
        containerColor = SktNavyDark,
        topBar = {
            SktTopBar(
                currentRole = currentRole,
                unreadNotificationCount = notifications.count { !it.isRead },
                onRoleClick = { showRoleSelector = true },
                onNotificationClick = { showNotificationDialog = true },
                onQuickActionClick = { viewModel.openAddStudentDialog(true) }
            )
        },
        bottomBar = {
            SktBottomBar(
                currentDestination = currentDestination,
                onNavigate = { viewModel.navigateTo(it) }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(SktNavyDark)
        ) {
            when (currentDestination) {
                SktNavDestination.DASHBOARD -> {
                    // Render Role-specific Dashboard
                    when (currentRole) {
                        UserRole.SUPER_ADMIN -> {
                            SuperAdminDashboard(
                                viewModel = viewModel,
                                schools = schools,
                                athletes = athletes,
                                coaches = coaches,
                                tournaments = tournaments,
                                financials = financials,
                                categories = sportsCategories
                            )
                        }
                        UserRole.SCHOOL_ADMIN -> {
                            SchoolAdminDashboard(
                                viewModel = viewModel,
                                schools = schools,
                                athletes = athletes,
                                tournaments = tournaments
                            )
                        }
                        UserRole.STUDENT -> {
                            selectedAthlete?.let { ath ->
                                StudentDashboard(
                                    viewModel = viewModel,
                                    athlete = ath,
                                    fitnessAssessments = fitnessAssessments,
                                    certificates = certificates,
                                    sessions = trainingSessions
                                )
                            }
                        }
                        UserRole.COACH -> {
                            CoachDashboard(
                                viewModel = viewModel,
                                coaches = coaches,
                                sessions = trainingSessions,
                                athletes = athletes
                            )
                        }
                        UserRole.PARENT -> {
                            selectedAthlete?.let { ath ->
                                ParentDashboard(
                                    viewModel = viewModel,
                                    athlete = ath,
                                    fitnessAssessments = fitnessAssessments,
                                    certificates = certificates
                                )
                            }
                        }
                        UserRole.COORDINATOR -> {
                            CoordinatorDashboard(
                                viewModel = viewModel,
                                schools = schools,
                                coaches = coaches,
                                talentHunt = talentHunt,
                                tournaments = tournaments
                            )
                        }
                    }
                }
                SktNavDestination.ATHLETE_RECORDS -> {
                    AthleteRecordsScreen(
                        viewModel = viewModel,
                        athletes = athletes
                    )
                }
                SktNavDestination.TOURNAMENTS -> {
                    TournamentsScreen(
                        viewModel = viewModel,
                        tournaments = tournaments
                    )
                }
                SktNavDestination.FITNESS -> {
                    FitnessScreen(
                        viewModel = viewModel,
                        fitnessAssessments = fitnessAssessments,
                        athletes = athletes
                    )
                }
                SktNavDestination.UDAAN -> {
                    UdaanScreen(
                        viewModel = viewModel,
                        athletes = athletes,
                        udaanInitiatives = udaanInitiatives
                    )
                }
                SktNavDestination.PARTNERSHIPS -> {
                    PartnershipFinanceScreen(
                        viewModel = viewModel,
                        schools = schools,
                        financials = financials
                    )
                }
                SktNavDestination.ROLE_SWITCHER -> {
                    showRoleSelector = true
                }
            }
        }
    }

    // Role Selector Dialog
    if (showRoleSelector) {
        RoleSelectorDialog(
            currentRole = currentRole,
            onRoleSelected = { role -> viewModel.switchRole(role) },
            onDismiss = { showRoleSelector = false }
        )
    }

    // Notifications Dialog
    if (showNotificationDialog) {
        NotificationDialog(
            notifications = notifications,
            onDismiss = { showNotificationDialog = false }
        )
    }

    // Add Student / Athlete Registration Dialog
    if (showAddStudent) {
        AddStudentDialog(
            schools = schools,
            sports = sportsCategories,
            onDismiss = { viewModel.openAddStudentDialog(false) },
            onSubmit = { name, age, gender, school, grade, pSport, sSport, isUdaan ->
                viewModel.registerStudent(name, age, gender, school, grade, pSport, sSport, isUdaan)
            }
        )
    }

    // Add School Affiliation Dialog
    if (showAddSchool) {
        AddSchoolDialog(
            onDismiss = { viewModel.openAddSchoolDialog(false) },
            onSubmit = { name, city, model, count, sports, principal, coord ->
                viewModel.registerSchool(name, city, model, count, sports, principal, coord)
            }
        )
    }

    // Record Fitness Test Dialog
    if (showAddFitness) {
        RecordFitnessDialog(
            athletes = athletes,
            onDismiss = { viewModel.openAddFitnessDialog(false) },
            onSubmit = { ath, sprint, beep, broad, pushups, coach, notes ->
                viewModel.recordFitnessAssessment(ath, sprint, beep, broad, pushups, coach, notes)
            }
        )
    }

    // Match Scorekeeper Dialog
    showScoreMatch?.let { match ->
        UpdateScoreDialog(
            match = match,
            onDismiss = { viewModel.openScoreUpdateDialog(null) },
            onSubmit = { scoreA, scoreB, isFinal ->
                val tournamentId = tournaments.firstOrNull { tour -> tour.matches.any { it.id == match.id } }?.id ?: "tour-1"
                viewModel.updateScore(tournamentId, match.id, scoreA, scoreB, isFinal)
            }
        )
    }

    // Digital Certificate Viewer Dialog
    showCertificate?.let { cert ->
        DigitalCertificateDialog(
            certificate = cert,
            onDismiss = { viewModel.openCertificateDetail(null) }
        )
    }

    // Full Digital Athlete ID Card Viewer Dialog
    showAthleteCard?.let { athlete ->
        AlertDialog(
            onDismissRequest = { viewModel.openAthleteIdCard(null) },
            containerColor = Color.Transparent,
            text = {
                AthleteIdCard(
                    athlete = athlete,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                Button(
                    onClick = { viewModel.openAthleteIdCard(null) },
                    colors = ButtonDefaults.buttonColors(containerColor = SktGold, contentColor = SktNavyDark),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Done", fontWeight = FontWeight.Bold)
                }
            }
        )
    }
}

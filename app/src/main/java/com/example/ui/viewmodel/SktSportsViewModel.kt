package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.SktSportsRepository
import com.example.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

enum class SktNavDestination {
    DASHBOARD,
    ATHLETE_RECORDS,
    TOURNAMENTS,
    FITNESS,
    UDAAN,
    PARTNERSHIPS,
    ROLE_SWITCHER
}

data class NotificationItem(
    val id: String,
    val title: String,
    val description: String,
    val timeAgo: String,
    val isRead: Boolean = false,
    val category: String
)

class SktSportsViewModel(
    private val repository: SktSportsRepository = SktSportsRepository()
) : ViewModel() {

    val currentRole = repository.currentRole
    val selectedAthleteId = repository.selectedAthleteId
    val sportsCategories = repository.sportsCategories
    val schools = repository.schools
    val athletes = repository.athletes
    val coaches = repository.coaches
    val tournaments = repository.tournaments
    val fitnessAssessments = repository.fitnessAssessments
    val trainingSessions = repository.trainingSessions
    val certificates = repository.certificates
    val financials = repository.financials
    val talentHunt = repository.talentHunt
    val udaanInitiatives = repository.udaanInitiatives

    // UI Navigation State
    private val _currentDestination = MutableStateFlow(SktNavDestination.DASHBOARD)
    val currentDestination: StateFlow<SktNavDestination> = _currentDestination.asStateFlow()

    // Search query
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // Selected sport filter
    private val _selectedSportFilter = MutableStateFlow("All")
    val selectedSportFilter: StateFlow<String> = _selectedSportFilter.asStateFlow()

    // Verified Athlete lookup state (for Digital QR Scanner verification)
    private val _verifiedAthleteResult = MutableStateFlow<Athlete?>(null)
    val verifiedAthleteResult: StateFlow<Athlete?> = _verifiedAthleteResult.asStateFlow()

    // Notifications
    private val _notifications = MutableStateFlow(
        listOf(
            NotificationItem("notif-1", "Match Result Live", "St. Xavier's Strikers won 2-1 against DPS Falcons!", "10m ago", false, "TOURNAMENT"),
            NotificationItem("notif-2", "Udaan Camp Tomorrow", "Special Speed & Agility bootcamp at 03:45 PM for registered girls.", "1h ago", false, "UDAAN"),
            NotificationItem("notif-3", "Fitness Assessment Uploaded", "Coach Rajesh recorded Q4 Sprint and Aerobic metrics for U-14 squad.", "3h ago", true, "FITNESS"),
            NotificationItem("notif-4", "Partnership Disbursed", "Monthly 50:50 revenue share calculated for St. Xavier's High School.", "1d ago", true, "FINANCE")
        )
    )
    val notifications: StateFlow<List<NotificationItem>> = _notifications.asStateFlow()

    // Dialog & Interaction states
    private val _showAddStudentDialog = MutableStateFlow(false)
    val showAddStudentDialog: StateFlow<Boolean> = _showAddStudentDialog.asStateFlow()

    private val _showAddSchoolDialog = MutableStateFlow(false)
    val showAddSchoolDialog: StateFlow<Boolean> = _showAddSchoolDialog.asStateFlow()

    private val _showAddFitnessDialog = MutableStateFlow(false)
    val showAddFitnessDialog: StateFlow<Boolean> = _showAddFitnessDialog.asStateFlow()

    private val _showAddSportDialog = MutableStateFlow(false)
    val showAddSportDialog: StateFlow<Boolean> = _showAddSportDialog.asStateFlow()

    private val _showScoreUpdateDialog = MutableStateFlow<TournamentMatch?>(null)
    val showScoreUpdateDialog: StateFlow<TournamentMatch?> = _showScoreUpdateDialog.asStateFlow()

    private val _showCertificateDetail = MutableStateFlow<DigitalCertificate?>(null)
    val showCertificateDetail: StateFlow<DigitalCertificate?> = _showCertificateDetail.asStateFlow()

    private val _showAthleteIdCard = MutableStateFlow<Athlete?>(null)
    val showAthleteIdCard: StateFlow<Athlete?> = _showAthleteIdCard.asStateFlow()

    // Selected Athlete object for Student / Parent views
    val currentSelectedAthlete: StateFlow<Athlete?> = combine(athletes, selectedAthleteId) { list, id ->
        list.firstOrNull { it.athleteId == id } ?: list.firstOrNull()
    }.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    fun navigateTo(destination: SktNavDestination) {
        _currentDestination.value = destination
    }

    fun switchRole(role: UserRole) {
        repository.setRole(role)
        _currentDestination.value = SktNavDestination.DASHBOARD
    }

    fun selectAthlete(athleteId: String) {
        repository.setSelectedAthleteId(athleteId)
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setSportFilter(sport: String) {
        _selectedSportFilter.value = sport
    }

    fun verifyAthleteId(idOrCode: String) {
        val found = athletes.value.firstOrNull {
            it.athleteId.equals(idOrCode.trim(), ignoreCase = true) ||
            it.fullName.contains(idOrCode.trim(), ignoreCase = true)
        }
        _verifiedAthleteResult.value = found
    }

    fun clearVerifiedResult() {
        _verifiedAthleteResult.value = null
    }

    // Dialog triggers
    fun openAddStudentDialog(open: Boolean) { _showAddStudentDialog.value = open }
    fun openAddSchoolDialog(open: Boolean) { _showAddSchoolDialog.value = open }
    fun openAddFitnessDialog(open: Boolean) { _showAddFitnessDialog.value = open }
    fun openAddSportDialog(open: Boolean) { _showAddSportDialog.value = open }
    fun openScoreUpdateDialog(match: TournamentMatch?) { _showScoreUpdateDialog.value = match }
    fun openCertificateDetail(cert: DigitalCertificate?) { _showCertificateDetail.value = cert }
    fun openAthleteIdCard(athlete: Athlete?) { _showAthleteIdCard.value = athlete }

    // Actions
    fun registerStudent(
        name: String,
        age: Int,
        gender: String,
        school: String,
        grade: String,
        primarySport: String,
        secondarySport: String,
        isUdaan: Boolean
    ) {
        val newAthlete = repository.registerAthlete(
            name = name,
            age = age,
            gender = gender,
            schoolName = school,
            grade = grade,
            primarySport = primarySport,
            secondarySport = secondarySport,
            isUdaan = isUdaan
        )
        _showAddStudentDialog.value = false
        // auto select for preview
        _showAthleteIdCard.value = newAthlete
    }

    fun registerSchool(
        name: String,
        city: String,
        model: PartnershipModel,
        studentsCount: Int,
        sports: List<String>,
        principal: String,
        coordinator: String
    ) {
        repository.registerSchool(
            name = name,
            city = city,
            model = model,
            studentsCount = studentsCount,
            sports = sports,
            principal = principal,
            coordinator = coordinator
        )
        _showAddSchoolDialog.value = false
    }

    fun recordFitnessAssessment(
        athlete: Athlete,
        sprint: Float,
        beep: Float,
        broadJump: Float,
        pushups: Int,
        coachName: String,
        notes: String
    ) {
        val grade = when {
            sprint <= 4.25f && beep >= 9.5f -> "A+"
            sprint <= 4.5f && beep >= 8.5f -> "A"
            sprint <= 5.0f -> "B+"
            else -> "B"
        }
        val assessment = FitnessAssessment(
            id = "fit-${System.currentTimeMillis()}",
            athleteId = athlete.athleteId,
            athleteName = athlete.fullName,
            sport = athlete.primarySport,
            assessmentDate = "Today",
            sprint30mSeconds = sprint,
            beepTestScore = beep,
            broadJumpMeters = broadJump,
            pushupsOneMin = pushups,
            shuttleRunSec = 10.0f,
            sitAndReachCm = 30.0f,
            overallGrade = grade,
            evaluatorCoach = coachName,
            notes = notes
        )
        repository.addFitnessAssessment(assessment)
        _showAddFitnessDialog.value = false
    }

    fun updateScore(tournamentId: String, matchId: String, scoreA: Int, scoreB: Int, isCompleted: Boolean) {
        val currentMatch = tournaments.value.firstOrNull { it.id == tournamentId }?.matches?.firstOrNull { it.id == matchId }
        val winner = if (scoreA > scoreB) {
            currentMatch?.teamA ?: "Team A"
        } else if (scoreB > scoreA) {
            currentMatch?.teamB ?: "Team B"
        } else "Draw"

        val status = if (isCompleted) MatchStatus.COMPLETED else MatchStatus.LIVE
        repository.updateMatchScore(tournamentId, matchId, scoreA, scoreB, status, winner)
        _showScoreUpdateDialog.value = null
    }

    fun markAttendance(sessionId: String, count: Int) {
        repository.markSessionAttendance(sessionId, count)
    }

    fun addSport(name: String, group: String) {
        repository.addNewSportsCategory(name, group)
        _showAddSportDialog.value = false
    }
}

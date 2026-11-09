package com.example.model

data class SportsCategoryItem(
    val id: String,
    val name: String,
    val categoryGroup: String, // Team Sport, Individual, Combat, Mind & Fitness, Racket
    val iconName: String,
    val activeAthletesCount: Int,
    val isOlympicSport: Boolean = true
)

data class Athlete(
    val id: String,
    val athleteId: String, // e.g. "SKT-2026-BIH-00042"
    val fullName: String,
    val age: Int,
    val gender: String, // "Male", "Female", "Other"
    val schoolId: String,
    val schoolName: String,
    val grade: String,
    val primarySport: String,
    val secondarySport: String,
    val bloodGroup: String = "O+",
    val contactPhone: String = "+91 98765 43210",
    val parentName: String = "Suresh Sharma",
    val fitnessGrade: String = "A+",
    val isUdaanAthlete: Boolean = false,
    val attendancePercent: Int = 92,
    val achievements: List<String> = emptyList(),
    val certificatesCount: Int = 3,
    val qrCodeHash: String = "SKT-AUTH-2026-VERIFIED",
    val registrationDate: String = "2026-01-15"
)

data class School(
    val id: String,
    val code: String, // e.g. "SCH-PAT-01"
    val name: String,
    val city: String,
    val state: String = "Bihar",
    val partnershipModel: PartnershipModel,
    val totalStudentsEnrolled: Int,
    val activeSports: List<String>,
    val principalName: String,
    val sportsCoordinator: String,
    val infrastructureRating: Float, // 1 to 5 stars
    val contractValidUntil: String,
    val monthlyRevenue: Long = 125000L
)

data class Coach(
    val id: String,
    val coachId: String, // e.g. "SKT-COACH-104"
    val name: String,
    val primarySport: String,
    val certification: String, // e.g. "AFC B-License", "NIS Certified Coach"
    val assignedSchools: List<String>,
    val experienceYears: Int,
    val phone: String,
    val rating: Float = 4.8f,
    val activeBatches: Int = 4,
    val studentsTrained: Int = 160
)

data class TournamentMatch(
    val id: String,
    val tournamentId: String,
    val round: String, // "Quarter Final", "Semi Final", "Grand Final", "Group A"
    val teamA: String,
    val teamB: String,
    val scoreA: Int = 0,
    val scoreB: Int = 0,
    val details: String = "",
    val timeSlot: String,
    val status: MatchStatus = MatchStatus.SCHEDULED,
    val winner: String? = null
)

data class Tournament(
    val id: String,
    val title: String,
    val sport: String,
    val categoryAge: String, // "Under-14", "Under-17", "Open Inter-School"
    val venue: String,
    val dates: String,
    val status: TournamentStatus,
    val participatingSchoolsCount: Int,
    val participatingTeamsCount: Int,
    val matches: List<TournamentMatch> = emptyList(),
    val winnerTeam: String? = null,
    val runnerUpTeam: String? = null,
    val prizePool: String = "Trophies + Merit Certificates"
)

data class FitnessAssessment(
    val id: String,
    val athleteId: String,
    val athleteName: String,
    val sport: String,
    val assessmentDate: String,
    val sprint30mSeconds: Float, // e.g. 4.35s
    val beepTestScore: Float, // e.g. 9.4
    val broadJumpMeters: Float, // e.g. 2.15m
    val pushupsOneMin: Int, // e.g. 34
    val shuttleRunSec: Float, // e.g. 10.2s
    val sitAndReachCm: Float, // e.g. 28.5cm
    val enduranceScore: Int = 88,
    val agilityScore: Int = 92,
    val strengthScore: Int = 85,
    val overallGrade: String = "A+",
    val evaluatorCoach: String = "Coach Rajesh Kumar",
    val notes: String = "Exceptional acceleration and cardiovascular recovery rate."
)

data class TrainingSession(
    val id: String,
    val title: String,
    val sport: String,
    val date: String,
    val time: String,
    val schoolName: String,
    val coachName: String,
    val focusDrills: List<String>,
    val attendancePresent: Int,
    val attendanceTotal: Int,
    val isCompleted: Boolean = false
)

data class DigitalCertificate(
    val id: String,
    val certificateNumber: String,
    val recipientName: String,
    val athleteId: String,
    val schoolName: String,
    val eventOrProgram: String,
    val achievementRank: String, // "Gold Medalist", "1st Runner Up", "Certificate of Merit", "High Performance Athlete"
    val sport: String,
    val dateIssued: String,
    val signedByTitle: String = "Sports Director, SHIV KHELTANTRA PVT. LTD."
)

data class FinancialRecord(
    val id: String,
    val title: String,
    val isRevenue: Boolean,
    val amount: Long,
    val date: String,
    val category: String, // "School Contract Fee", "Student Coaching Fee", "Coach Payroll", "Sports Equipment", "Tournament Revenue"
    val relatedEntity: String, // School or Vendor Name
    val invoiceNumber: String
)

data class TalentHuntTrial(
    val id: String,
    val athleteName: String,
    val athleteId: String,
    val schoolName: String,
    val sport: String,
    val ageGroup: String,
    val scoutScore: Int, // out of 100
    val scoutRecommendation: String, // "Fast Track to State Academy", "Advanced Training", "Developing"
    val scoutName: String,
    val keyStrengths: List<String>
)

data class UdaanInitiativeItem(
    val id: String,
    val title: String,
    val description: String,
    val schoolTarget: String,
    val participantsCount: Int,
    val leadSport: String,
    val status: String // "ACTIVE BATCH", "UPCOMING CAMP", "LEAGUE COMPLETED"
)

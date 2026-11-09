package com.example.data

import com.example.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SktSportsRepository {

    // Current active role for seamless role switching
    private val _currentRole = MutableStateFlow(UserRole.SUPER_ADMIN)
    val currentRole: StateFlow<UserRole> = _currentRole.asStateFlow()

    // Active athlete for Student & Parent portals
    private val _selectedAthleteId = MutableStateFlow("SKT-2026-BIH-00042")
    val selectedAthleteId: StateFlow<String> = _selectedAthleteId.asStateFlow()

    // 17 Standard sports specified by SHIV KHELTANTRA
    private val _sportsCategories = MutableStateFlow(
        listOf(
            SportsCategoryItem("sp-1", "Football", "Team Sport", "sports_soccer", 640),
            SportsCategoryItem("sp-2", "Cricket", "Team Sport", "sports_cricket", 820),
            SportsCategoryItem("sp-3", "Basketball", "Team Sport", "sports_basketball", 410),
            SportsCategoryItem("sp-4", "Volleyball", "Team Sport", "sports_volleyball", 320),
            SportsCategoryItem("sp-5", "Badminton", "Racket", "sports_tennis", 510),
            SportsCategoryItem("sp-6", "Table Tennis", "Racket", "table_tennis", 280),
            SportsCategoryItem("sp-7", "Athletics", "Individual", "directions_run", 580),
            SportsCategoryItem("sp-8", "Kabaddi", "Combat & Tag", "sports_kabaddi", 460),
            SportsCategoryItem("sp-9", "Kho-Kho", "Team Sport", "run_circle", 390),
            SportsCategoryItem("sp-10", "Chess", "Mind Sport", "chess", 210, isOlympicSport = false),
            SportsCategoryItem("sp-11", "Carrom", "Mind Sport", "sports_esports", 180, isOlympicSport = false),
            SportsCategoryItem("sp-12", "Yoga", "Mind & Fitness", "self_improvement", 340, isOlympicSport = false),
            SportsCategoryItem("sp-13", "Judo", "Combat", "sports_martial_arts", 150),
            SportsCategoryItem("sp-14", "Karate", "Combat", "sports_martial_arts", 220),
            SportsCategoryItem("sp-15", "Taekwondo", "Combat", "sports_martial_arts", 190),
            SportsCategoryItem("sp-16", "Self-Defense", "Combat & Safety", "shield", 480, isOlympicSport = false),
            SportsCategoryItem("sp-17", "Swimming", "Individual", "pool", 260)
        )
    )
    val sportsCategories: StateFlow<List<SportsCategoryItem>> = _sportsCategories.asStateFlow()

    // Partner Schools covering all 5 partnership models
    private val _schools = MutableStateFlow(
        listOf(
            School(
                id = "sch-1",
                code = "SCH-PAT-001",
                name = "St. Xavier's High School",
                city = "Patna",
                state = "Bihar",
                partnershipModel = PartnershipModel.FIFTY_FIFTY_INVESTMENT,
                totalStudentsEnrolled = 480,
                activeSports = listOf("Football", "Basketball", "Badminton", "Athletics", "Yoga"),
                principalName = "Fr. James Rodrigues",
                sportsCoordinator = "Ramanuj Verma",
                infrastructureRating = 4.8f,
                contractValidUntil = "31 Mar 2028",
                monthlyRevenue = 285000L
            ),
            School(
                id = "sch-2",
                code = "SCH-PAT-002",
                name = "Delhi Public School (DPS)",
                city = "Patna",
                state = "Bihar",
                partnershipModel = PartnershipModel.DIRECT_CONTRACT,
                totalStudentsEnrolled = 650,
                activeSports = listOf("Cricket", "Football", "Swimming", "Table Tennis", "Taekwondo"),
                principalName = "Dr. B. K. Sharma",
                sportsCoordinator = "Priya Nambiar",
                infrastructureRating = 4.9f,
                contractValidUntil = "30 Jun 2027",
                monthlyRevenue = 390000L
            ),
            School(
                id = "sch-3",
                code = "SCH-MUZ-003",
                name = "Loyola International Academy",
                city = "Muzaffarpur",
                state = "Bihar",
                partnershipModel = PartnershipModel.PER_STUDENT_MIN_GUARANTEE,
                totalStudentsEnrolled = 320,
                activeSports = listOf("Kabaddi", "Kho-Kho", "Volleyball", "Athletics", "Self-Defense"),
                principalName = "Sister Teresa Paul",
                sportsCoordinator = "Alok Ranjan",
                infrastructureRating = 4.2f,
                contractValidUntil = "31 Dec 2026",
                monthlyRevenue = 180000L
            ),
            School(
                id = "sch-4",
                code = "SCH-GAY-004",
                name = "Buddha Heritage Model School",
                city = "Gaya",
                state = "Bihar",
                partnershipModel = PartnershipModel.DIRECT_STUDENT_FEE,
                totalStudentsEnrolled = 290,
                activeSports = listOf("Badminton", "Karate", "Chess", "Yoga", "Self-Defense"),
                principalName = "Prof. Arvind Sinha",
                sportsCoordinator = "Sunita Kumari",
                infrastructureRating = 4.0f,
                contractValidUntil = "15 Oct 2027",
                monthlyRevenue = 145000L
            ),
            School(
                id = "sch-5",
                code = "SCH-BHA-005",
                name = "Vikramshila Vidya Mandir",
                city = "Bhagalpur",
                state = "Bihar",
                partnershipModel = PartnershipModel.FLEXIBLE_REV_SHARE,
                totalStudentsEnrolled = 410,
                activeSports = listOf("Cricket", "Kabaddi", "Athletics", "Judo", "Volleyball"),
                principalName = "R. K. Choudhary",
                sportsCoordinator = "Manish Tiwari",
                infrastructureRating = 4.3f,
                contractValidUntil = "31 Mar 2028",
                monthlyRevenue = 210000L
            )
        )
    )
    val schools: StateFlow<List<School>> = _schools.asStateFlow()

    // Athletes repository
    private val _athletes = MutableStateFlow(
        listOf(
            Athlete(
                id = "ath-1",
                athleteId = "SKT-2026-BIH-00042",
                fullName = "Aarav Sharma",
                age = 14,
                gender = "Male",
                schoolId = "sch-1",
                schoolName = "St. Xavier's High School",
                grade = "Grade 9-A",
                primarySport = "Football",
                secondarySport = "Athletics",
                bloodGroup = "B+",
                contactPhone = "+91 94310 12890",
                parentName = "Suresh Sharma",
                fitnessGrade = "A+",
                isUdaanAthlete = false,
                attendancePercent = 94,
                achievements = listOf(
                    "Top Scorer - Inter-School Cup 2025",
                    "District Sprint 100m Silver Medalist",
                    "SKT Elite Star Scholar 2026"
                ),
                certificatesCount = 4
            ),
            Athlete(
                id = "ath-2",
                athleteId = "SKT-2026-BIH-00108",
                fullName = "Ananya Singh",
                age = 15,
                gender = "Female",
                schoolId = "sch-1",
                schoolName = "St. Xavier's High School",
                grade = "Grade 10-C",
                primarySport = "Badminton",
                secondarySport = "Self-Defense",
                bloodGroup = "O+",
                contactPhone = "+91 98350 44120",
                parentName = "Meera Singh",
                fitnessGrade = "A+",
                isUdaanAthlete = true,
                attendancePercent = 96,
                achievements = listOf(
                    "SKT UDAAN Girls Singles Champion 2026",
                    "State Youth Badminton Quarter-Finalist",
                    "Self-Defense Black Belt Level 1"
                ),
                certificatesCount = 5
            ),
            Athlete(
                id = "ath-3",
                athleteId = "SKT-2026-BIH-00215",
                fullName = "Rohan Verma",
                age = 13,
                gender = "Male",
                schoolId = "sch-2",
                schoolName = "Delhi Public School (DPS)",
                grade = "Grade 8-B",
                primarySport = "Cricket",
                secondarySport = "Table Tennis",
                bloodGroup = "A+",
                contactPhone = "+91 91234 56789",
                parentName = "Dinesh Verma",
                fitnessGrade = "A",
                isUdaanAthlete = false,
                attendancePercent = 89,
                achievements = listOf(
                    "Best Wicketkeeper U-14 Trophy 2025",
                    "Century in State Interschool Qualifiers"
                ),
                certificatesCount = 3
            ),
            Athlete(
                id = "ath-4",
                athleteId = "SKT-2026-BIH-00334",
                fullName = "Priya Kumari",
                age = 16,
                gender = "Female",
                schoolId = "sch-3",
                schoolName = "Loyola International Academy",
                grade = "Grade 11-Arts",
                primarySport = "Kabaddi",
                secondarySport = "Athletics",
                bloodGroup = "AB+",
                contactPhone = "+91 97710 33211",
                parentName = "Kamlesh Kumari",
                fitnessGrade = "A+",
                isUdaanAthlete = true,
                attendancePercent = 98,
                achievements = listOf(
                    "Best Raider - Udaan Bihar Women's Cup",
                    "State High Jump Bronze Medalist",
                    "SKT Udaan Youth Sports Ambassador"
                ),
                certificatesCount = 6
            ),
            Athlete(
                id = "ath-5",
                athleteId = "SKT-2026-BIH-00412",
                fullName = "Devansh Narayan",
                age = 12,
                gender = "Male",
                schoolId = "sch-4",
                schoolName = "Buddha Heritage Model School",
                grade = "Grade 7-A",
                primarySport = "Basketball",
                secondarySport = "Swimming",
                bloodGroup = "B+",
                contactPhone = "+91 94700 88291",
                parentName = "Sunil Narayan",
                fitnessGrade = "B+",
                isUdaanAthlete = false,
                attendancePercent = 91,
                achievements = listOf(
                    "Most Improved Player - SKT Winter Camp",
                    "3-Point Shootout Finalist"
                ),
                certificatesCount = 2
            ),
            Athlete(
                id = "ath-6",
                athleteId = "SKT-2026-BIH-00507",
                fullName = "Sneha Roy",
                age = 14,
                gender = "Female",
                schoolId = "sch-5",
                schoolName = "Vikramshila Vidya Mandir",
                grade = "Grade 9-B",
                primarySport = "Volleyball",
                secondarySport = "Yoga",
                bloodGroup = "O+",
                contactPhone = "+91 93040 11928",
                parentName = "Prashant Roy",
                fitnessGrade = "A",
                isUdaanAthlete = true,
                attendancePercent = 93,
                achievements = listOf(
                    "Captain - Girls Volleyball Team",
                    "District Yoga Asana Gold Medalist"
                ),
                certificatesCount = 4
            )
        )
    )
    val athletes: StateFlow<List<Athlete>> = _athletes.asStateFlow()

    // Coaches
    private val _coaches = MutableStateFlow(
        listOf(
            Coach(
                id = "c-1",
                coachId = "SKT-COACH-101",
                name = "Rajesh Kumar",
                primarySport = "Football",
                certification = "AFC 'B' License & NIS Certified",
                assignedSchools = listOf("St. Xavier's High School", "Delhi Public School (DPS)"),
                experienceYears = 9,
                phone = "+91 98350 99881",
                rating = 4.9f,
                activeBatches = 4,
                studentsTrained = 180
            ),
            Coach(
                id = "c-2",
                coachId = "SKT-COACH-102",
                name = "Sunita Soren",
                primarySport = "Athletics & Udaan Lead",
                certification = "World Athletics Level 2 / SAI Coach",
                assignedSchools = listOf("Loyola International Academy", "St. Xavier's High School"),
                experienceYears = 7,
                phone = "+91 97711 55442",
                rating = 5.0f,
                activeBatches = 5,
                studentsTrained = 210
            ),
            Coach(
                id = "c-3",
                coachId = "SKT-COACH-103",
                name = "Vikramaditya Roy",
                primarySport = "Cricket",
                certification = "BCCI Level 1 & State High Performance",
                assignedSchools = listOf("Delhi Public School (DPS)", "Vikramshila Vidya Mandir"),
                experienceYears = 11,
                phone = "+91 94310 77665",
                rating = 4.8f,
                activeBatches = 3,
                studentsTrained = 145
            ),
            Coach(
                id = "c-4",
                coachId = "SKT-COACH-104",
                name = "Pooja Deshmukh",
                primarySport = "Badminton & Self-Defense",
                certification = "BWF Level 2 & National Gold Medalist",
                assignedSchools = listOf("Buddha Heritage Model School", "Loyola International Academy"),
                experienceYears = 6,
                phone = "+91 93041 33221",
                rating = 4.9f,
                activeBatches = 4,
                studentsTrained = 165
            )
        )
    )
    val coaches: StateFlow<List<Coach>> = _coaches.asStateFlow()

    // Tournaments
    private val _tournaments = MutableStateFlow(
        listOf(
            Tournament(
                id = "tour-1",
                title = "SKT Champions Trophy 2026 - Inter-School Football",
                sport = "Football",
                categoryAge = "Under-17 Boys & Girls",
                venue = "Patliputra Sports Complex, Kankarbagh",
                dates = "15 Nov - 20 Nov 2026",
                status = TournamentStatus.ONGOING,
                participatingSchoolsCount = 16,
                participatingTeamsCount = 24,
                matches = listOf(
                    TournamentMatch(
                        id = "m-101",
                        tournamentId = "tour-1",
                        round = "Semi Final 1",
                        teamA = "St. Xavier's Strikers",
                        teamB = "DPS Super Falcons",
                        scoreA = 2,
                        scoreB = 1,
                        details = "Aarav Sharma 34', 68' / Neil Roy 52'",
                        timeSlot = "Today, 04:30 PM",
                        status = MatchStatus.LIVE,
                        winner = null
                    ),
                    TournamentMatch(
                        id = "m-102",
                        tournamentId = "tour-1",
                        round = "Semi Final 2",
                        teamA = "Loyola Warriors",
                        teamB = "Vikramshila Knights",
                        scoreA = 0,
                        scoreB = 0,
                        details = "Match starts post first semi-final",
                        timeSlot = "Today, 06:00 PM",
                        status = MatchStatus.SCHEDULED,
                        winner = null
                    ),
                    TournamentMatch(
                        id = "m-103",
                        tournamentId = "tour-1",
                        round = "Quarter Final 1",
                        teamA = "St. Xavier's Strikers",
                        teamB = "Buddha Heritage Titans",
                        scoreA = 3,
                        scoreB = 0,
                        details = "Clean sheet win for St. Xavier's",
                        timeSlot = "Yesterday",
                        status = MatchStatus.COMPLETED,
                        winner = "St. Xavier's Strikers"
                    )
                ),
                winnerTeam = null,
                runnerUpTeam = null
            ),
            Tournament(
                id = "tour-2",
                title = "SKT UDAAN Girls State Kabaddi & Athletics Meet",
                sport = "Kabaddi",
                categoryAge = "Under-16 Girls All-Bihar",
                venue = "Moin-ul-Haq Sports Pavilion, Patna",
                dates = "02 Dec - 05 Dec 2026",
                status = TournamentStatus.UPCOMING,
                participatingSchoolsCount = 20,
                participatingTeamsCount = 20,
                matches = listOf(
                    TournamentMatch(
                        id = "m-201",
                        tournamentId = "tour-2",
                        round = "Inaugural Match",
                        teamA = "Loyola Udaan Lions",
                        teamB = "St. Xavier's Queens",
                        scoreA = 0,
                        scoreB = 0,
                        details = "Opening Ceremony followed by Match",
                        timeSlot = "02 Dec, 10:00 AM",
                        status = MatchStatus.SCHEDULED
                    )
                ),
                winnerTeam = null,
                runnerUpTeam = null
            ),
            Tournament(
                id = "tour-3",
                title = "Bihar Inter-School Badminton Super Series",
                sport = "Badminton",
                categoryAge = "Under-14 & Under-17 Singles/Doubles",
                venue = "Indoor Badminton Stadium, Rajvanshi Nagar",
                dates = "10 Oct - 14 Oct 2026",
                status = TournamentStatus.COMPLETED,
                participatingSchoolsCount = 28,
                participatingTeamsCount = 56,
                matches = emptyList(),
                winnerTeam = "Ananya Singh (St. Xavier's)",
                runnerUpTeam = "Kavya Menon (DPS Patna)"
            )
        )
    )
    val tournaments: StateFlow<List<Tournament>> = _tournaments.asStateFlow()

    // Fitness Assessments
    private val _fitnessAssessments = MutableStateFlow(
        listOf(
            FitnessAssessment(
                id = "fit-1",
                athleteId = "SKT-2026-BIH-00042",
                athleteName = "Aarav Sharma",
                sport = "Football",
                assessmentDate = "28 Oct 2026",
                sprint30mSeconds = 4.18f,
                beepTestScore = 9.8f,
                broadJumpMeters = 2.22f,
                pushupsOneMin = 36,
                shuttleRunSec = 9.9f,
                sitAndReachCm = 31.0f,
                enduranceScore = 94,
                agilityScore = 92,
                strengthScore = 88,
                overallGrade = "A+",
                evaluatorCoach = "Coach Rajesh Kumar",
                notes = "Elite pace & explosive leap. Aerobic capacity is in the 95th percentile for U-15 national standards."
            ),
            FitnessAssessment(
                id = "fit-2",
                athleteId = "SKT-2026-BIH-00108",
                athleteName = "Ananya Singh",
                sport = "Badminton",
                assessmentDate = "25 Oct 2026",
                sprint30mSeconds = 4.35f,
                beepTestScore = 10.2f,
                broadJumpMeters = 2.05f,
                pushupsOneMin = 32,
                shuttleRunSec = 9.4f,
                sitAndReachCm = 36.5f,
                enduranceScore = 96,
                agilityScore = 98,
                strengthScore = 86,
                overallGrade = "A+",
                evaluatorCoach = "Coach Sunita Soren",
                notes = "Unrivaled agility and flexibility. Rapid lateral court recovery."
            )
        )
    )
    val fitnessAssessments: StateFlow<List<FitnessAssessment>> = _fitnessAssessments.asStateFlow()

    // Training Sessions
    private val _trainingSessions = MutableStateFlow(
        listOf(
            TrainingSession(
                id = "tr-1",
                title = "Football High-Intensity Pressing & Tactical Transition",
                sport = "Football",
                date = "Today",
                time = "06:30 AM - 08:00 AM",
                schoolName = "St. Xavier's High School",
                coachName = "Coach Rajesh Kumar",
                focusDrills = listOf("Dynamic Warmup", "4v2 Rondo Pressing", "Overload Counters", "Penalty Spot Shootouts"),
                attendancePresent = 26,
                attendanceTotal = 28,
                isCompleted = true
            ),
            TrainingSession(
                id = "tr-2",
                title = "SKT UDAAN: Speed Agility & Fast Footwork Circuit",
                sport = "Athletics",
                date = "Today",
                time = "03:45 PM - 05:15 PM",
                schoolName = "St. Xavier's High School",
                coachName = "Coach Sunita Soren",
                focusDrills = listOf("Ladder Drills", "Cone Acceleration 30m", "Core Planks", "Stretching Routine"),
                attendancePresent = 24,
                attendanceTotal = 25,
                isCompleted = false
            ),
            TrainingSession(
                id = "tr-3",
                title = "Cricket Bowling Precision & Batting Footwork",
                sport = "Cricket",
                date = "Tomorrow",
                time = "07:00 AM - 08:30 AM",
                schoolName = "Delhi Public School (DPS)",
                coachName = "Coach Vikramaditya Roy",
                focusDrills = listOf("Spot Bowling", "Target Batting vs Spin", "Slips Catching Practice"),
                attendancePresent = 0,
                attendanceTotal = 22,
                isCompleted = false
            )
        )
    )
    val trainingSessions: StateFlow<List<TrainingSession>> = _trainingSessions.asStateFlow()

    // Digital Certificates
    private val _certificates = MutableStateFlow(
        listOf(
            DigitalCertificate(
                id = "cert-1",
                certificateNumber = "SKT-CERT-2026-00412",
                recipientName = "Aarav Sharma",
                athleteId = "SKT-2026-BIH-00042",
                schoolName = "St. Xavier's High School",
                eventOrProgram = "Inter-School Football Championship 2025",
                achievementRank = "Top Scorer & Golden Boot",
                sport = "Football",
                dateIssued = "15 Jan 2026"
            ),
            DigitalCertificate(
                id = "cert-2",
                certificateNumber = "SKT-CERT-2026-00891",
                recipientName = "Ananya Singh",
                athleteId = "SKT-2026-BIH-00108",
                schoolName = "St. Xavier's High School",
                eventOrProgram = "SKT UDAAN Girls Badminton Super League",
                achievementRank = "Champion - Gold Medalist",
                sport = "Badminton",
                dateIssued = "28 Feb 2026"
            ),
            DigitalCertificate(
                id = "cert-3",
                certificateNumber = "SKT-CERT-2026-01043",
                recipientName = "Aarav Sharma",
                athleteId = "SKT-2026-BIH-00042",
                schoolName = "St. Xavier's High School",
                eventOrProgram = "State Level Youth Physical Fitness Testing",
                achievementRank = "Grade A+ High Performance Athlete",
                sport = "Athletics",
                dateIssued = "29 Oct 2026"
            )
        )
    )
    val certificates: StateFlow<List<DigitalCertificate>> = _certificates.asStateFlow()

    // Financial Records
    private val _financials = MutableStateFlow(
        listOf(
            FinancialRecord("fin-1", "Monthly School Sports Management Fee", true, 285000L, "01 Nov 2026", "School Contract Fee", "St. Xavier's High School", "INV-SKT-2026-1101"),
            FinancialRecord("fin-2", "Direct Student Sports Academy Fee", true, 390000L, "02 Nov 2026", "Student Coaching Fee", "Delhi Public School (DPS)", "INV-SKT-2026-1102"),
            FinancialRecord("fin-3", "Certified Coach Payroll (86 Coaches)", false, 645000L, "05 Nov 2026", "Coach Payroll", "Shiv Kheltantra Coaching Staff", "PAY-SKT-2026-1101"),
            FinancialRecord("fin-4", "Sports Equipment & Tournament Kits Purchase", false, 185000L, "06 Nov 2026", "Sports Equipment", "Cosco & Nivia Sports India", "PO-SKT-8891"),
            FinancialRecord("fin-5", "Tournament Sponsorship & Entry Registration", true, 160000L, "07 Nov 2026", "Tournament Revenue", "Patliputra Cup Sponsors", "INV-SKT-2026-1105"),
            FinancialRecord("fin-6", "Per-Student Service Fee with Minimum Guarantee", true, 180000L, "08 Nov 2026", "School Contract Fee", "Loyola International Academy", "INV-SKT-2026-1108")
        )
    )
    val financials: StateFlow<List<FinancialRecord>> = _financials.asStateFlow()

    // Talent Hunt Trials
    private val _talentHunt = MutableStateFlow(
        listOf(
            TalentHuntTrial("th-1", "Aarav Sharma", "SKT-2026-BIH-00042", "St. Xavier's High School", "Football", "U-14", 95, "Fast Track to State Academy", "Senior Scout K. P. Singh", listOf("Explosive acceleration", "Spatial awareness", "Left-foot precision")),
            TalentHuntTrial("th-2", "Priya Kumari", "SKT-2026-BIH-00334", "Loyola International Academy", "Kabaddi", "U-16", 96, "Fast Track to State Academy", "National Scout Rekha Devi", listOf("Agile toe-touch", "Exceptional escape technique", "High match IQ")),
            TalentHuntTrial("th-3", "Rohan Verma", "SKT-2026-BIH-00215", "Delhi Public School (DPS)", "Cricket", "U-14", 88, "Advanced Training Batch", "Scout Vikramaditya Roy", listOf("Clean glove-work", "Solid defensive posture"))
        )
    )
    val talentHunt: StateFlow<List<TalentHuntTrial>> = _talentHunt.asStateFlow()

    // Udaan Initiatives
    private val _udaanInitiatives = MutableStateFlow(
        listOf(
            UdaanInitiativeItem("ud-1", "Udaan Multi-Sports Girls Exposure Camp", "Introducing 450+ girls across 8 schools to structured sports, breaking barriers and building confidence.", "St. Xavier's & Loyola", 450, "Multi-Sports (Volleyball, Athletics, Football)", "ACTIVE BATCH"),
            UdaanInitiativeItem("ud-2", "SKT Udaan Self-Defense & Safety Bootcamp", "Equipping school girls with practical martial arts, situational awareness, and physical courage.", "All 5 Partner Schools", 720, "Self-Defense & Taekwondo", "ACTIVE BATCH"),
            UdaanInitiativeItem("ud-3", "Udaan Girls Inter-School Football League", "Exclusive competitive league with scout panels from state sports associations.", "Central Patna Division", 280, "Football", "UPCOMING CAMP")
        )
    )
    val udaanInitiatives: StateFlow<List<UdaanInitiativeItem>> = _udaanInitiatives.asStateFlow()

    // Role switching
    fun setRole(role: UserRole) {
        _currentRole.value = role
    }

    fun setSelectedAthleteId(id: String) {
        _selectedAthleteId.value = id
    }

    // Actions
    fun registerAthlete(
        name: String,
        age: Int,
        gender: String,
        schoolName: String,
        grade: String,
        primarySport: String,
        secondarySport: String,
        isUdaan: Boolean
    ): Athlete {
        val count = _athletes.value.size + 1
        val newAthleteId = "SKT-2026-BIH-${String.format("%05d", count)}"
        val newAthlete = Athlete(
            id = "ath-${System.currentTimeMillis()}",
            athleteId = newAthleteId,
            fullName = name,
            age = age,
            gender = gender,
            schoolId = "sch-1",
            schoolName = schoolName,
            grade = grade,
            primarySport = primarySport,
            secondarySport = secondarySport,
            isUdaanAthlete = isUdaan,
            fitnessGrade = "A",
            attendancePercent = 100,
            achievements = listOf("SKT Registered Athlete 2026"),
            certificatesCount = 1
        )
        _athletes.update { listOf(newAthlete) + it }
        return newAthlete
    }

    fun registerSchool(
        name: String,
        city: String,
        model: PartnershipModel,
        studentsCount: Int,
        sports: List<String>,
        principal: String,
        coordinator: String
    ): School {
        val count = _schools.value.size + 1
        val newSchool = School(
            id = "sch-${System.currentTimeMillis()}",
            code = "SCH-${city.take(3).uppercase()}-${String.format("%03d", count)}",
            name = name,
            city = city,
            partnershipModel = model,
            totalStudentsEnrolled = studentsCount,
            activeSports = sports,
            principalName = principal,
            sportsCoordinator = coordinator,
            infrastructureRating = 4.5f,
            contractValidUntil = "31 Mar 2028",
            monthlyRevenue = (studentsCount * 600L).coerceAtLeast(150000L)
        )
        _schools.update { listOf(newSchool) + it }
        return newSchool
    }

    fun addFitnessAssessment(assessment: FitnessAssessment) {
        _fitnessAssessments.update { listOf(assessment) + it }
        // Update athlete's overall fitness grade
        _athletes.update { list ->
            list.map { if (it.athleteId == assessment.athleteId) it.copy(fitnessGrade = assessment.overallGrade) else it }
        }
    }

    fun markSessionAttendance(sessionId: String, presentCount: Int) {
        _trainingSessions.update { list ->
            list.map { if (it.id == sessionId) it.copy(attendancePresent = presentCount, isCompleted = true) else it }
        }
    }

    fun updateMatchScore(tournamentId: String, matchId: String, scoreA: Int, scoreB: Int, status: MatchStatus, winner: String?) {
        _tournaments.update { tourList ->
            tourList.map { tour ->
                if (tour.id == tournamentId) {
                    val updatedMatches = tour.matches.map { match ->
                        if (match.id == matchId) {
                            match.copy(scoreA = scoreA, scoreB = scoreB, status = status, winner = winner)
                        } else match
                    }
                    tour.copy(matches = updatedMatches)
                } else tour
            }
        }
    }

    fun addNewSportsCategory(name: String, group: String) {
        val newCat = SportsCategoryItem(
            id = "sp-${System.currentTimeMillis()}",
            name = name,
            categoryGroup = group,
            iconName = "sports",
            activeAthletesCount = 0
        )
        _sportsCategories.update { it + newCat }
    }
}

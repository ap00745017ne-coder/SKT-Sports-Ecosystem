package com.example

import com.example.data.SktSportsRepository
import com.example.model.*
import com.example.ui.viewmodel.SktSportsViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SktSportsEcosystemTest {

    private lateinit var repository: SktSportsRepository
    private lateinit var viewModel: SktSportsViewModel

    @Before
    fun setUp() {
        repository = SktSportsRepository()
        viewModel = SktSportsViewModel(repository)
    }

    @Test
    fun testInitialSportsAndSchoolsLoaded() {
        assertEquals(17, repository.sportsCategories.value.size)
        assertTrue(repository.schools.value.isNotEmpty())
        assertTrue(repository.athletes.value.isNotEmpty())
        assertTrue(repository.coaches.value.isNotEmpty())
        assertTrue(repository.tournaments.value.isNotEmpty())
    }

    @Test
    fun testRegisterAthleteGeneratesUniqueId() {
        val initialCount = repository.athletes.value.size
        val newAthlete = repository.registerAthlete(
            name = "Kavita Singh",
            age = 15,
            gender = "Female",
            schoolName = "St. Xavier's High School",
            grade = "Grade 10",
            primarySport = "Athletics",
            secondarySport = "Badminton",
            isUdaan = true
        )

        assertEquals(initialCount + 1, repository.athletes.value.size)
        assertTrue(newAthlete.athleteId.startsWith("SKT-2026-BIH-"))
        assertTrue(newAthlete.isUdaanAthlete)
    }

    @Test
    fun testRoleSwitching() {
        assertEquals(UserRole.SUPER_ADMIN, viewModel.currentRole.value)
        viewModel.switchRole(UserRole.COACH)
        assertEquals(UserRole.COACH, viewModel.currentRole.value)
        viewModel.switchRole(UserRole.STUDENT)
        assertEquals(UserRole.STUDENT, viewModel.currentRole.value)
    }

    @Test
    fun testAthleteVerification() {
        val targetId = "SKT-2026-BIH-00042"
        viewModel.verifyAthleteId(targetId)
        val result = viewModel.verifiedAthleteResult.value
        assertNotNull(result)
        assertEquals(targetId, result?.athleteId)
        assertEquals("Aarav Sharma", result?.fullName)
    }

    @Test
    fun testFitnessAssessmentGrading() {
        val athlete = repository.athletes.value.first()
        viewModel.recordFitnessAssessment(
            athlete = athlete,
            sprint = 4.15f,
            beep = 10.0f,
            broadJump = 2.25f,
            pushups = 38,
            coachName = "Coach Rajesh Kumar",
            notes = "Top tier sprint burst"
        )

        val latest = repository.fitnessAssessments.value.first()
        assertEquals("A+", latest.overallGrade)
        assertEquals(athlete.athleteId, latest.athleteId)
    }

    @Test
    fun testScoreUpdate() {
        val tournament = repository.tournaments.value.first()
        val match = tournament.matches.first()

        viewModel.updateScore(tournament.id, match.id, 3, 1, isCompleted = true)

        val updatedTour = repository.tournaments.value.first { it.id == tournament.id }
        val updatedMatch = updatedTour.matches.first { it.id == match.id }
        assertEquals(3, updatedMatch.scoreA)
        assertEquals(1, updatedMatch.scoreB)
        assertEquals(MatchStatus.COMPLETED, updatedMatch.status)
        assertEquals(match.teamA, updatedMatch.winner)
    }
}

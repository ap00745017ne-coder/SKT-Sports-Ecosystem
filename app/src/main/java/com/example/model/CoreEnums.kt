package com.example.model

enum class UserRole(val displayName: String, val badgeTitle: String) {
    SUPER_ADMIN("Super Admin", "Central Leadership"),
    SCHOOL_ADMIN("School Admin", "Partner School"),
    STUDENT("Student / Athlete", "Registered Athlete"),
    COACH("Coach", "Certified Trainer"),
    PARENT("Parent / Guardian", "Guardian Portal"),
    COORDINATOR("Sports Coordinator", "Field Operations")
}

enum class PartnershipModel(val title: String, val code: String, val description: String) {
    DIRECT_CONTRACT(
        "Direct School Contract Model",
        "MODEL-1",
        "Direct annual/monthly contract with school for turnkey sports education & training infrastructure."
    ),
    FIFTY_FIFTY_INVESTMENT(
        "50:50 Investment & Profit-Sharing",
        "MODEL-2",
        "Joint venture model sharing capital equipment, coach deployment, and academy fee profits 50:50."
    ),
    DIRECT_STUDENT_FEE(
        "Direct Student Fee Collection",
        "MODEL-3",
        "SKT Sports collects sports coaching fees directly from participating students with school facility access."
    ),
    FLEXIBLE_REV_SHARE(
        "Flexible Revenue-Sharing Model",
        "MODEL-4",
        "Tiered percentage sharing based on student enrollment milestones and tournament hosting revenues."
    ),
    PER_STUDENT_MIN_GUARANTEE(
        "Per-Student Fee with Minimum Guarantee",
        "MODEL-5",
        "Fixed monthly per-student fee backed by guaranteed minimum student enrollment count."
    )
}

enum class MatchStatus {
    SCHEDULED, LIVE, COMPLETED, POSTPONED
}

enum class TournamentStatus {
    UPCOMING, ONGOING, COMPLETED
}

enum class AttendanceStatus {
    PRESENT, ABSENT, EXCUSED, LATE
}

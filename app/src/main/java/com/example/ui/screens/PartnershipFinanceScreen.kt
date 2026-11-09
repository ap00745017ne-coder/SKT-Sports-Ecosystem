package com.example.ui.screens

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
import com.example.model.FinancialRecord
import com.example.model.PartnershipModel
import com.example.model.School
import com.example.ui.theme.*
import com.example.ui.viewmodel.SktSportsViewModel

@Composable
fun PartnershipFinanceScreen(
    viewModel: SktSportsViewModel,
    schools: List<School>,
    financials: List<FinancialRecord>
) {
    var selectedTab by remember { mutableStateOf(0) } // 0: 5 Partnership Models, 1: Partner Schools & Contracts, 2: Financial Ledger

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
                    text = "PARTNERSHIPS & FINANCIALS",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black,
                    color = SktGold
                )
                Text(
                    text = "5 Strategic School Models & Operations Ledger",
                    fontSize = 10.sp,
                    color = SktTextSecondary
                )
            }

            Button(
                onClick = { viewModel.openAddSchoolDialog(true) },
                colors = ButtonDefaults.buttonColors(containerColor = SktGold, contentColor = SktNavyDark),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("New Contract", fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Tabs
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
                text = { Text("5 Business Models", fontSize = 11.sp, fontWeight = FontWeight.Bold) }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = { Text("School Agreements", fontSize = 11.sp, fontWeight = FontWeight.Bold) }
            )
            Tab(
                selected = selectedTab == 2,
                onClick = { selectedTab = 2 },
                text = { Text("P&L Ledger", fontSize = 11.sp, fontWeight = FontWeight.Bold) }
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        when (selectedTab) {
            0 -> {
                // The 5 Partnership Models
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    PartnershipModel.values().forEach { model ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = SktNavyCard),
                            border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder)
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = SktGold.copy(alpha = 0.2f),
                                        border = androidx.compose.foundation.BorderStroke(1.dp, SktGold.copy(alpha = 0.5f))
                                    ) {
                                        Text(
                                            model.code,
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Black,
                                            color = SktGold,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                        )
                                    }

                                    Text("Contract Framework", fontSize = 9.sp, color = SktTextSecondary)
                                }

                                Spacer(modifier = Modifier.height(6.dp))
                                Text(model.title, fontSize = 13.5.sp, fontWeight = FontWeight.Black, color = SktWhite)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(model.description, fontSize = 10.5.sp, color = SktTextSecondary)

                                Spacer(modifier = Modifier.height(10.dp))
                                val schoolsUsing = schools.filter { it.partnershipModel == model }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "${schoolsUsing.size} Active Partner Schools",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = SktElectricBlue
                                    )
                                    Text(
                                        text = "Avg Yield: ₹${(schoolsUsing.sumOf { it.monthlyRevenue } / schoolsUsing.size.coerceAtLeast(1))} /mo",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = SktGreen
                                    )
                                }
                            }
                        }
                    }
                }
            }
            1 -> {
                // Partner Schools List
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(schools) { school ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
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
                                        Text(school.name, fontSize = 14.sp, fontWeight = FontWeight.Black, color = SktWhite)
                                        Text("${school.city}, ${school.state} • ${school.code}", fontSize = 10.sp, color = SktElectricBlue)
                                    }
                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = SktGreen.copy(alpha = 0.15f)
                                    ) {
                                        Text(
                                            "ACTIVE CONTRACT",
                                            fontSize = 8.5.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = SktGreen,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(8.dp))
                                Text("Model: ${school.partnershipModel.title}", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SktGold)
                                Text("Principal: ${school.principalName} • Coordinator: ${school.sportsCoordinator}", fontSize = 9.5.sp, color = SktTextSecondary)

                                Spacer(modifier = Modifier.height(10.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text("Students: ${school.totalStudentsEnrolled}", fontSize = 10.sp, color = SktWhite)
                                    Text("Sports: ${school.activeSports.joinToString(", ")}", fontSize = 9.5.sp, color = SktTextSecondary, maxLines = 1, modifier = Modifier.weight(1f).padding(horizontal = 8.dp))
                                    Text("₹${school.monthlyRevenue}/mo", fontSize = 11.sp, fontWeight = FontWeight.Black, color = SktGreen)
                                }
                            }
                        }
                    }
                }
            }
            2 -> {
                // Financial Ledger
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(financials) { fin ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = SktNavyCard),
                            border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(
                                        modifier = Modifier
                                            .size(36.dp)
                                            .clip(CircleShape)
                                            .background(if (fin.isRevenue) SktGreen.copy(alpha = 0.2f) else SktRed.copy(alpha = 0.2f)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = if (fin.isRevenue) Icons.Default.ArrowDownward else Icons.Default.ArrowUpward,
                                            contentDescription = null,
                                            tint = if (fin.isRevenue) SktGreen else SktRed,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(10.dp))

                                    Column {
                                        Text(fin.title, fontSize = 12.5.sp, fontWeight = FontWeight.Bold, color = SktWhite)
                                        Text("${fin.relatedEntity} • ${fin.category}", fontSize = 10.sp, color = SktTextSecondary)
                                        Text("${fin.date} • ${fin.invoiceNumber}", fontSize = 9.sp, fontFamily = FontFamily.Monospace, color = SktElectricBlue)
                                    }
                                }

                                Text(
                                    text = "${if (fin.isRevenue) "+" else "-"} ₹${fin.amount}",
                                    fontSize = 13.5.sp,
                                    fontWeight = FontWeight.Black,
                                    color = if (fin.isRevenue) SktGreen else SktRed
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

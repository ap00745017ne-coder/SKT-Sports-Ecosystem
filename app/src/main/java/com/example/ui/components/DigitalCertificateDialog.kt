package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.R
import com.example.model.DigitalCertificate
import com.example.ui.theme.*

@Composable
fun DigitalCertificateDialog(
    certificate: DigitalCertificate,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = SktNavyCard),
            border = androidx.compose.foundation.BorderStroke(
                2.dp,
                Brush.linearGradient(
                    colors = listOf(SktGold, SktGoldLight, SktGoldDark, SktGold)
                )
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF13233D),
                                SktNavyCard,
                                SktNavyDark
                            )
                        )
                    )
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header with Logo and Company Name
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.skt_sports_logo_1794243758674),
                        contentDescription = "SKT Sports",
                        modifier = Modifier.size(44.dp)
                    )

                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "SHIV KHELTANTRA PVT. LTD.",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = SktGold,
                            letterSpacing = 0.5.sp
                        )
                        Text(
                            text = "OFFICIAL SPORTS MERIT REGISTRY",
                            fontSize = 8.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = SktTextSecondary
                        )
                        Text(
                            text = certificate.certificateNumber,
                            fontSize = 9.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                            color = SktElectricBlue
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Certificate Title
                Text(
                    text = "CERTIFICATE OF EXCELLENCE",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Black,
                    color = SktGold,
                    letterSpacing = 1.2.sp,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "THIS IS PROUDLY PRESENTED TO",
                    fontSize = 9.5.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SktTextSecondary,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Recipient Name
                Text(
                    text = certificate.recipientName,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Black,
                    color = SktWhite,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "ATHLETE ID: ${certificate.athleteId} • ${certificate.schoolName}",
                    fontSize = 10.5.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SktElectricBlue,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Achievement Box
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = SktNavyDark,
                    border = androidx.compose.foundation.BorderStroke(1.dp, SktGold.copy(alpha = 0.4f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "FOR OUTSTANDING PERFORMANCE AS",
                            fontSize = 8.5.sp,
                            fontWeight = FontWeight.Bold,
                            color = SktTextSecondary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = certificate.achievementRank,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Black,
                            color = SktGold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "${certificate.eventOrProgram} • ${certificate.sport}",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = SktTextPrimary,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Signatory and QR Seal
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column(horizontalAlignment = Alignment.Start) {
                        Text(
                            text = "Date Issued: ${certificate.dateIssued}",
                            fontSize = 10.sp,
                            color = SktTextSecondary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Digitally Signed",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = SktGreen
                        )
                        Text(
                            text = certificate.signedByTitle,
                            fontSize = 9.sp,
                            color = SktTextSecondary
                        )
                    }

                    // QR Seal
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        AthleticQrCode(
                            code = certificate.certificateNumber,
                            modifier = Modifier.size(54.dp)
                        )
                        Text(
                            text = "VERIFIED",
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold,
                            color = SktGold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Actions
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = SktTextPrimary),
                        border = androidx.compose.foundation.BorderStroke(1.dp, SktBorder)
                    ) {
                        Text("Close", fontSize = 12.sp)
                    }

                    Button(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1.3f),
                        colors = ButtonDefaults.buttonColors(containerColor = SktGold, contentColor = SktNavyDark)
                    ) {
                        Icon(imageVector = Icons.Default.Download, contentDescription = "Download", modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Save PDF", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

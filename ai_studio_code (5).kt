package com.elitetradechat.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elitetradechat.ui.components.TraderAvatar
import com.elitetradechat.ui.theme.*

// ---------------- 5. PROFILE SCREEN ----------------
@Composable
fun ProfileScreen(onNavigateToSettings: () -> Unit = {}) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        TraderAvatar(initials = "ET", size = 88, isVip = true, isOnline = true)
        Spacer(modifier = Modifier.height(12.dp))
        
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Satoshi's Ghost", color = TextPrimary, fontWeight = FontWeight.Black, fontSize = 20.sp)
            Spacer(modifier = Modifier.width(6.dp))
            Icon(Icons.Default.Verified, contentDescription = "Verified Pro Desk", tint = GoldPrimary, modifier = Modifier.size(18.dp))
        }
        Text("0x71C8...4b29 • Lead Desk Alpha Provider", color = GoldAccent, fontSize = 12.sp)

        Spacer(modifier = Modifier.height(20.dp))

        // Performance Metrics Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MetricBox(modifier = Modifier.weight(1f), label = "Win Rate", value = "78.4%", valueColor = NeonProfit)
            MetricBox(modifier = Modifier.weight(1f), label = "Total ROI", value = "+412%", valueColor = GoldPrimary)
            MetricBox(modifier = Modifier.weight(1f), label = "Desk Tier", value = "VIP Black", valueColor = GoldAccent)
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Desk Bio Card
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceCharcoal),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, CardBorder, RoundedCornerShape(12.dp))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Desk Bio & Strategy", color = TextSecondary, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    "Algorithmic Orderflow, Macro Liquidity Hunter & CVD Divergence specialist. " +
                    "All signal setups include pre-computed R:R, invalidation criteria, and slippage buffers.",
                    color = TextPrimary,
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Trader Badges / Verifications
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceCharcoal),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, CardBorder, RoundedCornerShape(12.dp))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                ProfileOptionRow(icon = Icons.Default.Security, title = "Hardware Key Attached", value = "YubiKey 5C")
                HorizontalDivider(color = CardBorder.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 8.dp))
                ProfileOptionRow(icon = Icons.Default.Speed, title = "Execution Latency", value = "12ms (Direct Colocation)")
                HorizontalDivider(color = CardBorder.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 8.dp))
                ProfileOptionRow(icon = Icons.Default.Group, title = "Copied By", value = "1,842 Active Accounts")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onNavigateToSettings,
            colors = ButtonDefaults.buttonColors(containerColor = SurfaceCharcoal),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, GoldPrimary, RoundedCornerShape(10.dp))
        ) {
            Icon(Icons.Default.Tune, contentDescription = null, tint = GoldPrimary, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Adjust Terminal Preferences", color = GoldPrimary, fontWeight = FontWeight.Bold, fontSize = 13.sp)
        }
    }
}

@Composable
fun MetricBox(modifier: Modifier = Modifier, label: String, value: String, valueColor: Color) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceCharcoal),
        modifier = modifier.border(1.dp, CardBorder, RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(label, color = TextSecondary, fontSize = 11.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(value, color = valueColor, fontWeight = FontWeight.Black, fontSize = 15.sp)
        }
    }
}

@Composable
fun ProfileOptionRow(icon: ImageVector, title: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = GoldPrimary, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(10.dp))
            Text(title, color = TextPrimary, fontSize = 13.sp)
        }
        Text(value, color = TextSecondary, fontSize = 12.sp, fontWeight = FontWeight.Medium)
    }
}

// ---------------- 6. SETTINGS & PRIVACY SCREEN ----------------
@Composable
fun SettingsScreen() {
    var hideBalance by remember { mutableStateOf(true) }
    var allowSignalsBroadcast by remember { mutableStateOf(true) }
    var readReceipts by remember { mutableStateOf(false) }
    var biometricLock by remember { mutableStateOf(true) }
    var pushNotifications by remember { mutableStateOf(true) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text("Terminal Security & Preferences", color = TextPrimary, fontWeight = FontWeight.Black, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(14.dp))

        Text("PRIVACY CONTROLS", color = GoldPrimary, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
        Spacer(modifier = Modifier.height(8.dp))

        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceCharcoal),
            modifier = Modifier.fillMaxWidth().border(1.dp, CardBorder, RoundedCornerShape(12.dp))
        ) {
            Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)) {
                SettingsToggleRow(
                    title = "Mask Trade Balances",
                    subtitle = "Automatically blur wallet balances in screenshots",
                    checked = hideBalance,
                    onCheckedChange = { hideBalance = it }
                )
                HorizontalDivider(color = CardBorder.copy(alpha = 0.5f))
                SettingsToggleRow(
                    title = "Execution Receipts",
                    subtitle = "Send double gold checkmarks upon order confirmation",
                    checked = readReceipts,
                    onCheckedChange = { readReceipts = it }
                )
                HorizontalDivider(color = CardBorder.copy(alpha = 0.5f))
                SettingsToggleRow(
                    title = "Allow Signal Forwarding",
                    subtitle = "Permit subscribers to mirror your calls to sub-desks",
                    checked = allowSignalsBroadcast,
                    onCheckedChange = { allowSignalsBroadcast = it }
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))
        Text("SECURITY & HARDWARE KEYS", color = GoldPrimary, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
        Spacer(modifier = Modifier.height(8.dp))

        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceCharcoal),
            modifier = Modifier.fillMaxWidth().border(1.dp, CardBorder, RoundedCornerShape(12.dp))
        ) {
            Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)) {
                SettingsToggleRow(
                    title = "Biometric Kill-Switch",
                    subtitle = "Require FaceID/Fingerprint before broadcasting trades",
                    checked = biometricLock,
                    onCheckedChange = { biometricLock = it }
                )
                HorizontalDivider(color = CardBorder.copy(alpha = 0.5f))
                SettingsToggleRow(
                    title = "High Priority Audio Alerts",
                    subtitle = "Override do-not-disturb for Take-Profit and Stop-Loss triggers",
                    checked = pushNotifications,
                    onCheckedChange = { pushNotifications = it }
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))
        Text("ENCRYPTION TERMINAL", color = GoldPrimary, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
        Spacer(modifier = Modifier.height(8.dp))

        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceCharcoal),
            modifier = Modifier.fillMaxWidth().border(1.dp, CardBorder, RoundedCornerShape(12.dp))
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.VpnKey, contentDescription = null, tint = GoldPrimary, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("PGP & Ephemeral Chat Keys", color = TextPrimary, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "All desk messages and voice calls are protected with ratcheted end-to-end encryption.",
                    color = TextSecondary,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun SettingsToggleRow(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    
package com.elitetradechat.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elitetradechat.data.TradeDirection
import com.elitetradechat.data.TradingSignal
import com.elitetradechat.ui.theme.*

@Composable
fun TraderAvatar(
    initials: String,
    isOnline: Boolean = false,
    size: Int = 48,
    isVip: Boolean = false
) {
    Box(contentAlignment = Alignment.BottomEnd) {
        Box(
            modifier = Modifier
                .size(size.dp)
                .clip(CircleShape)
                .background(
                    Brush.radialGradient(
                        colors = listOf(Color(0xFF283042), Color(0xFF141824))
                    )
                )
                .border(
                    width = if (isVip) 1.5.dp else 1.dp,
                    color = if (isVip) GoldPrimary else CardBorder,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = initials,
                color = if (isVip) GoldPrimary else TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = (size * 0.35).sp
            )
        }
        if (isOnline) {
            Box(
                modifier = Modifier
                    .size((size * 0.28).dp)
                    .clip(CircleShape)
                    .background(NeonProfit)
                    .border(2.dp, DarkBg, CircleShape)
            )
        }
    }
}

@Composable
fun TradingSignalCard(
    signal: TradingSignal,
    onCopyTrade: () -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceCharcoal),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .border(1.dp, CardBorder, RoundedCornerShape(14.dp))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = signal.pair,
                        fontWeight = FontWeight.Black,
                        fontSize = 16.sp,
                        color = TextPrimary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(
                                if (signal.direction == TradeDirection.LONG) NeonProfit.copy(alpha = 0.15f)
                                else NeonLoss.copy(alpha = 0.15f)
                            )
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "${signal.direction.name} ${signal.leverage}",
                            color = if (signal.direction == TradeDirection.LONG) NeonProfit else NeonLoss,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    }
                }
                Text(
                    text = signal.timestamp,
                    color = TextSecondary,
                    fontSize = 11.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("ENTRY", color = TextSecondary, fontSize = 10.sp)
                    Text(signal.entryPrice, color = TextPrimary, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                }
                Column {
                    Text("TARGET", color = TextSecondary, fontSize = 10.sp)
                    Text(signal.targetPrice, color = NeonProfit, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                }
                Column {
                    Text("STOP LOSS", color = TextSecondary, fontSize = 10.sp)
                    Text(signal.stopLoss, color = NeonLoss, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                }
                Column {
                    Text("EST. ROI", color = TextSecondary, fontSize = 10.sp)
                    Text(signal.estimatedRoi, color = GoldPrimary, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Desk Lead",
                        tint = GoldPrimary,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = signal.author,
                        color = TextSecondary,
                        fontSize = 11.sp
                    )
                }

                Button(
                    onClick = onCopyTrade,
                    colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 2.dp),
                    modifier = Modifier.height(32.dp)
                ) {
                    Text("Copy Trade", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                }
            }
        }
    }
}
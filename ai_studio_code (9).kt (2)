package com.elitetradechat.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.elitetradechat.app.data.models.*
import com.elitetradechat.app.ui.components.HoldToRecordInputBar
import com.elitetradechat.app.ui.components.VoiceMessageBubble
import com.elitetradechat.app.ui.screens.*
import com.elitetradechat.app.ui.theme.DarkBackground
import com.elitetradechat.app.ui.theme.DarkSurface
import com.elitetradechat.app.ui.theme.GoldPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EliteTradeChatApp() {
    var selectedTabIndex by remember { mutableIntStateOf(1) }
    val tabs = listOf("SIGNALS", "CHATS", "STATUS", "CALLS")

    // Active Call Coordinator State
    var currentCallState by remember {
        mutableStateOf(CallState(status = CallStatus.IDLE))
    }

    // Dynamic Mock Chat Messages
    var chatMessages by remember {
        mutableStateOf(
            listOf(
                ChatMessage(id = "1", senderId = "peer", content = "Gold bouncing off $2580 support!"),
                ChatMessage(id = "2", senderId = "peer", durationSeconds = 18, type = MessageType.VOICE)
            )
        )
    }

    // Dynamic Statuses (with 24-hr check)
    var statusUpdates by remember {
        mutableStateOf(
            listOf(
                TradeStatus(id = "1", username = "BullWhale", textContent = "XAU/USD TP1 Hit! +150 pips secure.", backgroundColorHex = 0xFF004D40),
                TradeStatus(id = "2", username = "AlphaSignals", textContent = "Fed speech in 2 hours, tighten SL.", backgroundColorHex = 0xFF4A148C)
            )
        )
    }

    // Call History Logs
    var callHistory by remember {
        mutableStateOf(
            listOf(
                CallRecord("c1", "VIP Forex Desk", "", CallType.VIDEO, CallDirection.INCOMING, 145),
                CallRecord("c2", "Crypto Prime Desk", "", CallType.AUDIO, CallDirection.MISSED, 0),
                CallRecord("c3", "Scalp Analyst", "", CallType.AUDIO, CallDirection.OUTGOING, 52)
            )
        )
    }

    // Call state interception
    when (currentCallState.status) {
        CallStatus.INCOMING -> {
            IncomingCallScreen(
                callState = currentCallState,
                onAccept = { currentCallState = currentCallState.copy(status = CallStatus.CONNECTED) },
                onReject = { currentCallState = currentCallState.copy(status = CallStatus.IDLE) }
            )
            return
        }
        CallStatus.OUTGOING, CallStatus.CONNECTED -> {
            OutgoingAndActiveCallScreen(
                callState = currentCallState,
                onEndCall = { currentCallState = currentCallState.copy(status = CallStatus.IDLE) }
            )
            return
        }
        else -> Unit
    }

    // Main Scaffold Layout
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ELITE TRADECHAT", color = GoldPrimary, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkSurface),
                actions = {
                    IconButton(onClick = { /* Search */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search", tint = GoldPrimary)
                    }
                    IconButton(onClick = { /* More Menu */ }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menu", tint = GoldPrimary)
                    }
                }
            )
        },
        bottomBar = {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = DarkSurface,
                contentColor = GoldPrimary
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title, fontWeight = FontWeight.Bold) }
                    )
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (selectedTabIndex) {
                0 -> SignalsTabScreen(
                    signals = listOf(
                        TradingSignal("1", "XAU/USD", SignalAction.BUY, 2580.5, 2570.0, 2595.0, 2610.0, 2630.0, SignalStatus.ACTIVE)
                    )
                )
                1 -> Column(modifier = Modifier.fillMaxSize().background(DarkBackground)) {
                    // Chat Top bar action (Direct WebRTC dial)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(DarkSurface)
                            .padding(horizontal = 16.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("VIP Forex Desk", color = Color.White, fontWeight = FontWeight.Bold)
                        Row {
                            IconButton(onClick = {
                                currentCallState = CallState(
                                    callerName = "VIP Forex Desk",
                                    callType = CallType.AUDIO,
                                    status = CallStatus.OUTGOING
                                )
                            }) {
                                Icon(Icons.Default.Call, contentDescription = "Voice Call", tint = GoldPrimary)
                            }
                            IconButton(onClick = {
                                currentCallState = CallState(
                                    callerName = "VIP Forex Desk",
                                    callType = CallType.VIDEO,
                                    status = CallStatus.OUTGOING
                                )
                            }) {
                                Icon(Icons.Default.Videocam, contentDescription = "Video Call", tint = GoldPrimary)
                            }
                        }
                    }

                    // Chat messages list
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(chatMessages) { message ->
                            if (message.type == MessageType.VOICE) {
                                VoiceMessageBubble(
                                    totalDurationSec = message.durationSeconds,
                                    audioUrl = message.mediaUrl,
                                    isOutbound = message.senderId == "me"
                                )
                            } else {
                                Card(
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = if (message.senderId == "me") GoldPrimary else DarkSurface
                                    )
                                ) {
                                    Text(
                                        text = message.content,
                                        modifier = Modifier.padding(12.dp),
                                        color = if (message.senderId == "me") Color.Black else Color.White
                                    )
                                }
                            }
                        }
                    }

                    // Bottom Voice Recording & Text Bar
                    HoldToRecordInputBar(
                        onSendTextMessage = { text ->
                            chatMessages = chatMessages + ChatMessage(
                                id = System.currentTimeMillis().toString(),
                                senderId = "me",
                                content = text,
                                type = MessageType.TEXT
                            )
                        },
                        onVoiceNoteRecorded = { duration ->
                            chatMessages = chatMessages + ChatMessage(
                                id = System.currentTimeMillis().toString(),
                                senderId = "me",
                                durationSeconds = duration,
                                type = MessageType.VOICE
                            )
                        }
                    )
                }
                2 -> StatusScreenWithStories(
                    allStatuses = statusUpdates,
                    onPublishStatus = { text, colorHex ->
                        val newStatus = TradeStatus(
                            id = System.currentTimeMillis().toString(),
                            username = "Me",
                            textContent = text,
                            backgroundColorHex = colorHex
                        )
                        statusUpdates = listOf(newStatus) + statusUpdates
                    }
                )
                3 -> CallsTabScreen(
                    callLogs = callHistory,
                    onPlaceCall = { peerName, type ->
                        currentCallState = CallState(
                            callerName = peerName,
                            callType = type,
                            status = CallStatus.OUTGOING
                        )
                    }
                )
            }
        }
    }
}
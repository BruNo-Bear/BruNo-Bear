package com.bearinmind.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

private enum class Tab(val label: String, val icon: ImageVector) {
    Home("Home", Icons.Filled.Home),
    Services("Services", Icons.Filled.Star),
    Booking("Book", Icons.Filled.CalendarMonth),
    Shop("Catalog", Icons.Filled.ShoppingBag),
    Notes("Notes", Icons.AutoMirrored.Filled.Notes),
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BearInMindTheme {
                BearInMindApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BearInMindApp() {
    var current by rememberSaveable { mutableStateOf(Tab.Home) }
    val booking = rememberBookingFormState()
    var noteDraft by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(BusinessInfo.NAME) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
            )
        },
        bottomBar = {
            NavigationBar {
                Tab.entries.forEach { tab ->
                    NavigationBarItem(
                        selected = current == tab,
                        onClick = { current = tab },
                        icon = { Icon(tab.icon, contentDescription = tab.label) },
                        label = { Text(tab.label) },
                    )
                }
            }
        },
    ) { padding ->
        val modifier = Modifier.padding(padding)
        when (current) {
            Tab.Home -> HomeScreen(modifier) { current = it }
            Tab.Services -> ServicesScreen(modifier) { service ->
                booking.service = service.name
                current = Tab.Booking
            }
            Tab.Booking -> BookingScreen(modifier, booking)
            Tab.Shop -> ShopScreen(modifier)
            Tab.Notes -> NotesScreen(modifier, noteDraft) { noteDraft = it }
        }
    }
}

@Composable
private fun HomeScreen(modifier: Modifier, onNavigate: (Tab) -> Unit) =
    HomeContent(modifier, onBook = { onNavigate(Tab.Booking) }, onServices = { onNavigate(Tab.Services) })

package com.bearinmind.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val screenPadding = 16.dp

@Composable
fun HomeContent(modifier: Modifier = Modifier, onBook: () -> Unit, onServices: () -> Unit) {
    val context = LocalContext.current
    LazyColumn(
        modifier = modifier.fillMaxWidth().padding(screenPadding),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "${BusinessInfo.NAME} logo",
                    modifier = Modifier.size(180.dp),
                )
                Text(BusinessInfo.TAGLINE, style = MaterialTheme.typography.titleLarge)
            }
        }
        item {
            Card { Text(BusinessInfo.ABOUT, modifier = Modifier.padding(screenPadding)) }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = onBook) { Text("Book now") }
                OutlinedButton(onClick = onServices) { Text("Our services") }
            }
        }
        item { SectionTitle("Get in touch") }
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                ContactRow(Icons.Filled.Call, BusinessInfo.PHONE) {
                    Actions.dial(context, BusinessInfo.PHONE)
                }
                ContactRow(Icons.Filled.Email, BusinessInfo.EMAIL) {
                    Actions.email(context, BusinessInfo.EMAIL, "Enquiry for ${BusinessInfo.NAME}")
                }
                ContactRow(Icons.Filled.Place, BusinessInfo.ADDRESS) {
                    Actions.openMap(context, BusinessInfo.ADDRESS)
                }
                ContactRow(Icons.Filled.Language, BusinessInfo.WEBSITE) {
                    Actions.openUrl(context, BusinessInfo.WEBSITE)
                }
            }
        }
        item { SectionTitle("Opening hours") }
        items(BusinessInfo.OPENING_HOURS) { (days, hours) ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(days)
                Text(hours, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
fun ServicesScreen(modifier: Modifier = Modifier, onBook: () -> Unit) {
    LazyColumn(
        modifier = modifier.fillMaxWidth().padding(screenPadding),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item { SectionTitle("What we offer") }
        items(BusinessInfo.SERVICES) { service ->
            Card {
                Column(Modifier.padding(screenPadding), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(service.name, style = MaterialTheme.typography.titleMedium)
                    Text(service.description, style = MaterialTheme.typography.bodyMedium)
                    Text(service.price, fontWeight = FontWeight.Bold)
                    Button(onClick = onBook) { Text("Book this") }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val storage = remember { Storage(context) }
    val bookings = remember { mutableStateListOf<Booking>().also { it.addAll(storage.loadBookings()) } }

    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var service by remember { mutableStateOf(BusinessInfo.SERVICES.first().name) }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    LazyColumn(
        modifier = modifier.fillMaxWidth().padding(screenPadding),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item { SectionTitle("Request an appointment") }
        item {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Your name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        item {
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone number") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth(),
            )
        }
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Service", style = MaterialTheme.typography.labelLarge)
                BusinessInfo.SERVICES.chunked(2).forEach { row ->
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        row.forEach { option ->
                            FilterChip(
                                selected = service == option.name,
                                onClick = { service = option.name },
                                label = { Text(option.name) },
                            )
                        }
                    }
                }
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = date,
                    onValueChange = { date = it },
                    label = { Text("Date") },
                    placeholder = { Text("2026-01-31") },
                    singleLine = true,
                    modifier = Modifier.weight(1f),
                )
                OutlinedTextField(
                    value = time,
                    onValueChange = { time = it },
                    label = { Text("Time") },
                    placeholder = { Text("10:00") },
                    singleLine = true,
                    modifier = Modifier.weight(1f),
                )
            }
        }
        item {
            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Anything we should know?") },
                modifier = Modifier.fillMaxWidth(),
            )
        }
        error?.let { message -> item { Text(message, color = MaterialTheme.colorScheme.error) } }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = {
                        if (name.isBlank() || phone.isBlank() || date.isBlank()) {
                            error = "Please fill in your name, phone number and a date."
                            return@Button
                        }
                        error = null
                        val booking = Booking(name, phone, service, date, time, notes)
                        bookings.add(0, booking)
                        storage.saveBookings(bookings)
                        Actions.email(
                            context,
                            BusinessInfo.EMAIL,
                            "Booking request: $service",
                            bookingSummary(booking),
                        )
                        name = ""; phone = ""; date = ""; time = ""; notes = ""
                    },
                ) { Text("Send request") }
                OutlinedButton(onClick = { Actions.dial(context, BusinessInfo.PHONE) }) { Text("Call us") }
            }
        }
        if (bookings.isNotEmpty()) {
            item { SectionTitle("Your requests") }
            items(bookings) { booking ->
                Card {
                    Row(
                        Modifier.fillMaxWidth().padding(screenPadding),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(Modifier.weight(1f)) {
                            Text(booking.service, style = MaterialTheme.typography.titleSmall)
                            Text("${booking.date} ${booking.time}".trim())
                            Text(booking.name + " · " + booking.phone, style = MaterialTheme.typography.bodySmall)
                        }
                        IconButton(
                            onClick = {
                                bookings.remove(booking)
                                storage.saveBookings(bookings)
                            },
                        ) { Icon(Icons.Filled.Delete, contentDescription = "Delete request") }
                    }
                }
            }
        }
    }
}

@Composable
fun ShopScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    LazyColumn(
        modifier = modifier.fillMaxWidth().padding(screenPadding),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item { SectionTitle("Our products") }
        items(BusinessInfo.PRODUCTS) { product ->
            Card {
                Column(Modifier.padding(screenPadding), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(product.name, style = MaterialTheme.typography.titleMedium)
                    Text(product.description, style = MaterialTheme.typography.bodyMedium)
                    Text(product.price, fontWeight = FontWeight.Bold)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(
                            onClick = {
                                Actions.email(
                                    context,
                                    BusinessInfo.EMAIL,
                                    "Order enquiry: ${product.name}",
                                    "Hi ${BusinessInfo.NAME}, I would like to order: ${product.name} (${product.price}).",
                                )
                            },
                        ) { Text("Enquire") }
                        OutlinedButton(
                            onClick = {
                                Actions.share(
                                    context,
                                    "${product.name} - ${product.price} from ${BusinessInfo.NAME}",
                                )
                            },
                        ) { Text("Share") }
                    }
                }
            }
        }
    }
}

@Composable
fun NotesScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val storage = remember { Storage(context) }
    val notes = remember { mutableStateListOf<Note>().also { it.addAll(storage.loadNotes()) } }
    var draft by remember { mutableStateOf("") }
    val formatter = remember { SimpleDateFormat("d MMM yyyy, HH:mm", Locale.getDefault()) }

    LazyColumn(
        modifier = modifier.fillMaxWidth().padding(screenPadding),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item { SectionTitle("Keep it in mind") }
        item {
            OutlinedTextField(
                value = draft,
                onValueChange = { draft = it },
                label = { Text("New note or reminder") },
                modifier = Modifier.fillMaxWidth(),
            )
        }
        item {
            Button(
                enabled = draft.isNotBlank(),
                onClick = {
                    notes.add(0, Note(draft.trim(), System.currentTimeMillis()))
                    storage.saveNotes(notes)
                    draft = ""
                },
            ) { Text("Add note") }
        }
        if (notes.isEmpty()) {
            item { Text("No notes yet. Anything you add here stays on this phone.") }
        }
        items(notes) { note ->
            Card {
                Row(
                    Modifier.fillMaxWidth().padding(screenPadding),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(Modifier.weight(1f)) {
                        Text(note.text)
                        Text(
                            formatter.format(Date(note.createdAt)),
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                    IconButton(
                        onClick = {
                            notes.remove(note)
                            storage.saveNotes(notes)
                        },
                    ) { Icon(Icons.Filled.Delete, contentDescription = "Delete note") }
                }
            }
        }
    }
}

@Composable
private fun SectionTitle(text: String) =
    Text(text, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

@Composable
private fun ContactRow(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String, onClick: () -> Unit) {
    OutlinedButton(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
        Icon(icon, contentDescription = null)
        Text("  $text")
    }
}

private fun bookingSummary(booking: Booking) = buildString {
    appendLine("Name: ${booking.name}")
    appendLine("Phone: ${booking.phone}")
    appendLine("Service: ${booking.service}")
    appendLine("Date: ${booking.date} ${booking.time}")
    if (booking.notes.isNotBlank()) appendLine("Notes: ${booking.notes}")
}

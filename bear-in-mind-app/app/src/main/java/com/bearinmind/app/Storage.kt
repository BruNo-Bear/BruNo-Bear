package com.bearinmind.app

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

data class Booking(
    val name: String,
    val phone: String,
    val service: String,
    val date: String,
    val time: String,
    val notes: String,
)

data class Note(val text: String, val createdAt: Long)

class Storage(context: Context) {

    private val prefs = context.getSharedPreferences("bear_in_mind", Context.MODE_PRIVATE)

    fun loadBookings(): List<Booking> = readArray(KEY_BOOKINGS).map {
        Booking(
            name = it.optString("name"),
            phone = it.optString("phone"),
            service = it.optString("service"),
            date = it.optString("date"),
            time = it.optString("time"),
            notes = it.optString("notes"),
        )
    }

    fun saveBookings(bookings: List<Booking>) {
        val array = JSONArray()
        bookings.forEach {
            array.put(
                JSONObject()
                    .put("name", it.name)
                    .put("phone", it.phone)
                    .put("service", it.service)
                    .put("date", it.date)
                    .put("time", it.time)
                    .put("notes", it.notes),
            )
        }
        prefs.edit().putString(KEY_BOOKINGS, array.toString()).apply()
    }

    fun loadNotes(): List<Note> = readArray(KEY_NOTES).map {
        Note(text = it.optString("text"), createdAt = it.optLong("createdAt"))
    }

    fun saveNotes(notes: List<Note>) {
        val array = JSONArray()
        notes.forEach {
            array.put(JSONObject().put("text", it.text).put("createdAt", it.createdAt))
        }
        prefs.edit().putString(KEY_NOTES, array.toString()).apply()
    }

    private fun readArray(key: String): List<JSONObject> {
        val raw = prefs.getString(key, null) ?: return emptyList()
        val array = JSONArray(raw)
        return (0 until array.length()).map { array.getJSONObject(it) }
    }

    private companion object {
        const val KEY_BOOKINGS = "bookings"
        const val KEY_NOTES = "notes"
    }
}

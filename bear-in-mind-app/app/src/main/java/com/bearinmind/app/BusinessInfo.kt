package com.bearinmind.app

/**
 * Single place to edit everything shown in the app.
 */
object BusinessInfo {
    const val NAME = "Bear In Mind"
    const val TAGLINE = "Thoughtful service, kept in mind."
    const val ABOUT =
        "Bear In Mind is a small local business built on care and attention to detail. " +
            "We keep track of what matters to you so nothing gets forgotten. " +
            "Browse our services, book an appointment, shop our products, or jot down a reminder."

    const val PHONE = "+27000000000"
    const val EMAIL = "Bruceandre89@gmail.com"
    const val WEBSITE = "https://github.com/BruNo-Bear"
    const val ADDRESS = "123 Main Road, Cape Town, South Africa"

    val OPENING_HOURS = listOf(
        "Monday - Friday" to "08:00 - 17:00",
        "Saturday" to "09:00 - 13:00",
        "Sunday & Public Holidays" to "Closed",
    )

    val SERVICES = listOf(
        Service("Consultation", "A 30 minute chat to understand exactly what you need.", "R250"),
        Service("Standard Package", "Our most popular option, delivered within five working days.", "R1 200"),
        Service("Premium Package", "Priority turnaround with ongoing support for a full month.", "R2 500"),
        Service("Support & Maintenance", "Monthly check-ins, updates and small fixes.", "R650 / month"),
    )

    val PRODUCTS = listOf(
        Product("Bear In Mind Notebook", "A5 hardcover notebook with dotted pages.", "R180"),
        Product("Reminder Sticker Pack", "Twenty weatherproof stickers to label anything.", "R60"),
        Product("Desk Planner", "Undated weekly planner, fifty two tear-off sheets.", "R220"),
        Product("Gift Card", "Any amount, valid for twelve months on all services.", "From R100"),
    )
}

data class Service(val name: String, val description: String, val price: String)

data class Product(val name: String, val description: String, val price: String)

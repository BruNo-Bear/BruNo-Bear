package com.bearinmind.app

/**
 * Single place to edit everything shown in the app.
 */
object BusinessInfo {
    const val NAME = "Bear In Mind"
    const val TAGLINE = "Computer repairs, software and web design."
    const val ABOUT =
        "Bear In Mind fixes laptops and desktops, installs Windows and software, " +
            "and builds websites for small businesses. " +
            "Browse what we do, book a repair, or jot down a reminder for yourself."

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
        Service("Repairing of computers", "Laptops and desktops. Price depends on the problem.", "R700"),
        Service("Web design", "Basic website for your business.", "R1 500"),
        Service("Software installation", "Price depends on the software.", "R150"),
        Service("Windows installation", "Windows 7, 8 or 10 on laptops and desktops.", "From R100"),
    )

    val PRODUCTS = listOf(
        Product("Windows 7", "Booting for laptop and desktop.", "R100"),
        Product("Windows 8", "Booting of laptop and desktop.", "R150"),
        Product("Windows 10", "Booting of laptops and desktop.", "R250"),
        Product("Softwares", "Price depends on the software you need.", "R150"),
    )
}

data class Service(val name: String, val description: String, val price: String)

data class Product(val name: String, val description: String, val price: String)

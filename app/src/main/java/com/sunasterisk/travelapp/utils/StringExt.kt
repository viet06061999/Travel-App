package com.sunasterisk.travelapp.utils

import java.text.Normalizer
import java.text.SimpleDateFormat
import java.util.regex.Matcher
import java.util.regex.Pattern

fun String.convertToPrice(): String {
    val prices = this.split("-")
    if (prices.size > 1) {
        val firstPrice = prices[0]
            .split(".", ",")[0]
            .filter { it in '0'..'9' }
        var secondPrice = prices[1]
            .split(".", ",")[0]
            .filter { it in '0'..'9' }
        if (secondPrice == "1") secondPrice += prices[1].split(".", ",")[1]
        return "${firstPrice}$ - ${secondPrice}$"
    }
    return "60$ - 80$"
}

fun String.getPrice(): Double {
    val prices = this.split("-")
    if (prices.size > 1) {
        val firstPrice = prices[0]
            .split(".", ",")[0]
            .filter { it in '0'..'9' }
        val secondPrice = prices[1]
            .split(".", ",")[0]
            .filter { it in '0'..'9' }
        val result = firstPrice.toDouble() + secondPrice.toDouble()
        return result / 2.0
    }
    return 0.0
}

fun String.removeAccent(): String {
    val tmp = Normalizer.normalize(this, Normalizer.Form.NFD)
    val pattern =
        Pattern.compile("\\p{InCOMBINING_DIACRITICAL_MARKS}+")
    return pattern.matcher(tmp).replaceAll("")
}

fun String.formatDate(): String {
    val DATE_FORMAT_I = "yyyy-MM-dd'T'HH:mm:ss"
    val DATE_FORMAT_O = "dd-MM-yyyy HH:mm"
    val formatInput = SimpleDateFormat(DATE_FORMAT_I)
    val formatOutput = SimpleDateFormat(DATE_FORMAT_O)
    val date = formatInput.parse(this)
    return formatOutput.format(date)
}

fun String.isValidEmail(): Boolean{
        val pattern: Pattern
        val matcher: Matcher
        val emailPattern = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
        pattern = Pattern.compile(emailPattern)
        matcher = pattern.matcher(this)
        return matcher.matches()
}

package com.technical.test.satoritech.utils

fun String.getInitials(): String = this.split(' ')
    .mapNotNull { it.firstOrNull()?.toString() }
    .reduce { acc, s -> acc + s }

fun String.alphabetCheck(): Boolean {
    val regex = Regex("[^A-Za-z]")
    return regex.matches(this)
}
fun String.isLetters(): Boolean {
    return this.replace(" ","").all { it.isLetter() }
}
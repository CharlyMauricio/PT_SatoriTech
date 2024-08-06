package com.technical.test.satoritech.utils

fun String.getInitials(): String = this.split(' ')
    .mapNotNull { it.firstOrNull()?.toString() }
    .reduce { acc, s -> acc + s }

fun String.getIdPokemon(): String {
    return this.replace("[^0-9]".toRegex(), "").drop(1)
}
fun String.isLetters(): Boolean {
    return this.replace(" ","").all { it.isLetter() }
}
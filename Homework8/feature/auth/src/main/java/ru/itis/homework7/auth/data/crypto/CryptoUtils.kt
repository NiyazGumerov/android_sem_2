package ru.itis.homework7.auth.data.crypto

import java.security.MessageDigest

fun hashPassword(password: String): String {
    val bytes = MessageDigest.getInstance("SHA-256").digest(password.toByteArray())
    return bytes.joinToString("") { "%02x".format(it) }
}
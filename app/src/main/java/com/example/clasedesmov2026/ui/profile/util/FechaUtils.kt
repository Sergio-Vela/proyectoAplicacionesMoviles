package com.example.clasedesmov2026.ui.profile.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Convierte una fecha en formato ISO (yyyy-MM-dd) a texto legible en español.
 * Ejemplo: 2026-10-16 -> 16 de octubre de 2026
 */
fun formatFecha(fechaIso: String): String {
    val formatoIso = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val formatoTexto = SimpleDateFormat("d 'de' MMMM 'de' yyyy", Locale("es", "ES"))

    return try {
        val fecha = formatoIso.parse(fechaIso)
        formatoTexto.format(fecha)
    } catch (e: ParseException) {
        fechaIso
    }
}


package com.example.clasedesmov2026.ui.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PersonalInfoCard(
    telefono: String,
    correo: String,
    fechaNac: String,
    genero: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Información personal",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = telefono,
                onValueChange = {},
                label = { Text("Teléfono") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                singleLine = true
            )

            OutlinedTextField(
                value = correo,
                onValueChange = {},
                label = { Text("Correo electrónico") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                singleLine = true
            )

            OutlinedTextField(
                value = fechaNac,
                onValueChange = {},
                label = { Text("Fecha de nacimiento") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                singleLine = true
            )

            OutlinedTextField(
                value = genero,
                onValueChange = {},
                label = { Text("Género") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                singleLine = true
            )
        }
    }
}

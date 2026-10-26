package com.example.clasedesmov2026.ui.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.clasedesmov2026.ui.profile.util.formatFecha

@Composable
fun PersonalInfoCard(
    telefono: String,
    correo: String,
    fechaNac: String,
    genero: String,
    editando: Boolean = false,
    onTelefonoChange: (String) -> Unit = {},
    onCorreoChange: (String) -> Unit = {},
    onFechaNacChange: (String) -> Unit = {},
    onGeneroChange: (String) -> Unit = {},
    onGuardarClick: () -> Unit = {},
    onCancelarClick: () -> Unit = {},
    onEditarClick: () -> Unit = {}
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Información personal",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                if (editando) {
                    IconButton(
                        onClick = onCancelarClick,
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer,
                            contentColor = MaterialTheme.colorScheme.onErrorContainer
                        )
                    ) {
                        Text("X")
                    }
                } else {
                    OutlinedButton(
                        onClick = onEditarClick
                    ) {
                        Text("Editar")
                    }
                }
            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            OutlinedTextField(
                value = telefono,
                onValueChange = onTelefonoChange,
                label = { Text("Teléfono") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = !editando,
                singleLine = true
            )

            OutlinedTextField(
                value = correo,
                onValueChange = onCorreoChange,
                label = { Text("Correo electrónico") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = !editando,
                singleLine = true
            )

            OutlinedTextField(
                value = if (editando) fechaNac else formatFecha(fechaNac),
                onValueChange = onFechaNacChange,
                label = { Text("Fecha de nacimiento") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = !editando,
                singleLine = true
            )

            OutlinedTextField(
                value = genero,
                onValueChange = onGeneroChange,
                label = { Text("Género") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = !editando,
                singleLine = true
            )

            if (editando) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    OutlinedButton(
                        onClick = onCancelarClick
                    ) {
                        Text("Cancelar")
                    }

                    Button(
                        onClick = onGuardarClick,
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Text("Guardar cambios")
                    }
                }
            }
        }
    }
}


package ni.edu.uam.mistareasapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskFormDialog(
    onDismiss: () -> Unit,
    onTaskCreated: (Task) -> Unit
) {
    var taskName by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf(System.currentTimeMillis().toString()) }
    var endDate by remember { mutableStateOf((System.currentTimeMillis() + 86400000).toString()) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .verticalScroll(rememberScrollState())
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Nueva Tarea",
                        style = MaterialTheme.typography.headlineSmall
                    )

                    OutlinedTextField(
                        value = taskName,
                        onValueChange = { taskName = it },
                        label = { Text("Nombre *") },
                        isError = taskName.isBlank(),
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = taskDescription,
                        onValueChange = { taskDescription = it },
                        label = { Text("Descripción") },
                        maxLines = 3,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text(
                        text = "Fechas (timestamp ms)",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    OutlinedTextField(
                        value = startDate,
                        onValueChange = { startDate = it },
                        label = { Text("Fecha inicio") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = endDate,
                        onValueChange = { endDate = it },
                        label = { Text("Fecha final") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text("Cancelar")
                }

                Button(
                    onClick = {
                        if (taskName.isNotBlank()) {
                            val task = Task(
                                name = taskName.trim(),
                                description = taskDescription.trim(),
                                startDate = startDate,
                                endDate = endDate
                            )
                            onTaskCreated(task)
                        }
                    },
                    enabled = taskName.isNotBlank()
                ) {
                    Text("Crear Tarea")
                }
            }
        }
    }
}
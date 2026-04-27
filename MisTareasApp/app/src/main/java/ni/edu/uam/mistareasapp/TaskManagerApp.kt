package ni.edu.uam.mistareasapp

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskManagerApp(taskViewModel: TaskViewModel = viewModel()) {
    var showTaskForm by remember { mutableStateOf(false) }
    var filterCompleted by remember { mutableStateOf(false) }

    val filteredTasks = taskViewModel.tasks.filter { !filterCompleted || !it.isCompleted }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Mis Tareas (${filteredTasks.size})",
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    Switch(
                        checked = filterCompleted,
                        onCheckedChange = { filterCompleted = it }
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showTaskForm = true }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Nueva tarea")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            AnimatedVisibility(
                visible = filteredTasks.isEmpty(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                EmptyState()
            }

            AnimatedVisibility(
                visible = filteredTasks.isNotEmpty(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                LazyColumn(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filteredTasks) { task ->
                        TaskCard(
                            task = task,
                            onToggleComplete = { taskViewModel.toggleTaskCompletion(it) },
                            onDelete = { taskViewModel.deleteTask(it) }
                        )
                    }
                }
            }
        }

        if (showTaskForm) {
            TaskFormDialog(
                onDismiss = { showTaskForm = false },
                onTaskCreated = { taskViewModel.addTask(it) }
            )
        }
    }
}

@Composable
fun EmptyState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "📝 ¡No hay tareas!",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Crea tu primera tarea con el botón +",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskCard(
    task: Task,
    onToggleComplete: (String) -> Unit,
    onDelete: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        ListItem(
            headlineContent = {
                Text(
                    text = task.name,
                    style = MaterialTheme.typography.titleMedium
                )
            },
            supportingContent = {
                Column {
                    Text(task.description)
                    Text(
                        text = task.formattedDateRange(),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            },
            leadingContent = {
                Checkbox(
                    checked = task.isCompleted,
                    onCheckedChange = { onToggleComplete(task.id) }
                )
            },
            trailingContent = {
                IconButton(onClick = { onDelete(task.id) }) {
                    Icon(Icons.Default.Delete, "Eliminar")
                }
            }
        )
    }
}
package ni.edu.uam.mistareasapp

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class TaskViewModel : ViewModel() {
    val tasks: SnapshotStateList<Task> = mutableStateListOf()

    init {
        loadTasks()
    }

    fun addTask(task: Task) {
        tasks.add(task)
        saveTasks()
    }

    fun toggleTaskCompletion(taskId: String) {
        val index = tasks.indexOfFirst { it.id == taskId }
        if (index != -1) {
            tasks[index] = tasks[index].copy(isCompleted = !tasks[index].isCompleted)
            saveTasks()
        }
    }

    fun deleteTask(taskId: String) {
        tasks.removeIf { it.id == taskId }
        saveTasks()
    }

    private fun saveTasks() {
        viewModelScope.launch {
            val json = Json.encodeToString(tasks)
            // Guardar en archivo (simplificado)
            println("Guardando: $json")
        }
    }

    private fun loadTasks() {
        // Cargar desde archivo (simplificado)
        tasks.addAll(listOf(
            Task(
                name = "Tarea de ejemplo",
                description = "Esta es una tarea de ejemplo",
                startDate = "1700000000000",
                endDate = "1701000000000"
            )
        ))
    }
}
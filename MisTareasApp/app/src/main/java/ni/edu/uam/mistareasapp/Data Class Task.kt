package ni.edu.uam.mistareasapp

import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Serializable
data class Task(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val description: String,
    val startDate: String,
    val endDate: String,
    val isCompleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
) {
    fun formattedDateRange(): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return "${sdf.format(Date(startDate.toLongOrNull() ?: 0))} - ${sdf.format(Date(endDate.toLongOrNull() ?: 0))}"
    }
}
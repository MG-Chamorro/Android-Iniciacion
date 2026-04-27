package ni.edu.uam.appdehbitos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DashboardScreen()
                }
            }
        }
    }
}

@Composable
fun DashboardScreen() {
    var habits by remember { mutableStateOf(sampleHabits()) }
    val userName by remember { mutableStateOf("Ana") }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            HeaderSection(userName)
            ProgressCard()
            HabitsList(habits, onToggle = { index ->
                habits = habits.mapIndexed { i, habit ->
                    if (i == index) habit.copy(completed = !habit.completed) else habit
                }
            })
            WeeklySummary()
        }

        // FAB en Box overlay
        FloatingActionButtonCustom()
    }
}

@Composable
private fun HeaderSection(userName: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Hola, $userName 👋",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Icon(
            Icons.Default.Notifications,
            contentDescription = "Notificaciones",
            modifier = Modifier.size(32.dp),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun ProgressCard() {
    var progress by remember { mutableStateOf(75f) }
    val animatedProgress by animateFloatAsState(progress / 100)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Text(
                text = "Progreso de hoy",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(Modifier.height(16.dp))

            // Progress Bar
            Box(
                modifier = Modifier
                    .height(12.dp)
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.outlineVariant,
                        RoundedCornerShape(10.dp)
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(animatedProgress)
                        .background(
                            MaterialTheme.colorScheme.primary,
                            RoundedCornerShape(10.dp)
                        )
                )
            }

            Spacer(Modifier.height(12.dp))
            Text(
                text = "${progress.toInt()}%",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

data class HabitItem(
    val name: String,
    val goal: String,
    val category: String,
    val completed: Boolean = false
)

@Composable
private fun HabitsList(
    habits: List<HabitItem>,
    onToggle: (Int) -> Unit
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(habits) { habit ->
            HabitRow(habit) { onToggle(habits.indexOf(habit)) }
        }
    }
}

@Composable
private fun HabitRow(
    habit: HabitItem,
    onToggle: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggle() },
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Check circle
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(
                        if (habit.completed) MaterialTheme.colorScheme.primary
                        else Color.LightGray,
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (habit.completed) {
                    Text("✓", color = Color.White, fontSize = 16.sp)
                }
            }

            Spacer(Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = habit.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (habit.completed) MaterialTheme.colorScheme.primary else Color.Black
                )
                Text(
                    text = habit.goal,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            // Category badge
            Box(
                modifier = Modifier
                    .background(
                        categoryColor(habit.category),
                        RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = habit.category,
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
private fun WeeklySummary() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val days = listOf("L", "M", "X", "J", "V", "S", "D")
        days.forEachIndexed { index, day ->
            val completed = index % 2 == 0
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(
                        if (completed) Color.Green else Color.Gray,
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = day,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun FloatingActionButtonCustom() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = { },
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ) {
            Icon(Icons.Default.Add, contentDescription = "Agregar")
        }
    }
}

@Composable
private fun categoryColor(category: String): Color = when (category) {
    "Salud" -> Color(0xFF4CAF50)
    "Estudio" -> Color(0xFF2196F3)
    "Trabajo" -> Color(0xFFFF9800)
    else -> Color.Gray
}


private fun sampleHabits() = listOf(
    HabitItem("Beber agua", "2L", "Salud"),
    HabitItem("Meditar", "10 min", "Salud", true),
    HabitItem("Estudiar", "2h", "Estudio"),
    HabitItem("Reunión", "9:00 AM", "Trabajo", true),
    HabitItem("Ejercicio", "30 min", "Salud")
)

@Composable
private fun MyAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        content = content
    )
}
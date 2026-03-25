package com.example.taskmanagercompleted

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanagercompleted.ui.theme.TaskManagerCompletedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskManagerCompletedTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Body(
                        completed = stringResource(R.string.task_Completed),
                        good = stringResource(R.string.Good_Work),
                        modifier = Modifier)
                }
                }
            }
        }
    }


@Composable
fun Body(completed: String, good: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UpImage(
            modifier = modifier
        )
        Text(
            text = completed,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 24.   dp, bottom = 8.dp)
        )
        Text(
            text = good,
            fontSize = 16.sp,
        )
    }
}

@Composable
fun UpImage(modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.ic_task_completed)
    Image(
        painter = image,
        contentDescription = null,
        modifier = modifier

    )
}

@Preview(showBackground = true)
@Composable
fun BodyPreview() {
    TaskManagerCompletedTheme {
        Body(
            completed = stringResource(R.string.task_Completed),
            good = stringResource(R.string.Good_Work)
        )
    }
}
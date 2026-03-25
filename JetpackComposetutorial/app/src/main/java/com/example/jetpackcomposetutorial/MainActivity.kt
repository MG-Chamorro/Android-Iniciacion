package com.example.jetpackcomposetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposetutorial.ui.theme.JetpackComposeTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTutorialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Body(
                        message = stringResource(R.string.compose),
                        from = stringResource(R.string.Jetpack_compose),
                        to = stringResource(R.string.Description),
                        modifier = Modifier)
                }
                }
            }
        }
    }


@Composable
fun Body(message: String, from: String, to: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ){
        Text(
            text = "",
            modifier.padding(10.dp)
        )
        UpImage(
            modifier = modifier
        )
        Text(
            text = message,
            fontSize = 24.sp,
            textAlign = TextAlign.Justify,
            modifier = modifier
                .padding(16.dp)
        )

        Text(
            text = from,
            textAlign = TextAlign.Justify,
            modifier = modifier
                .padding(16.dp)
        )

        Text(
            text = to,
            textAlign = TextAlign.Justify,
            modifier = modifier
                .padding(16.dp)
        )
    }
}

@Composable
fun UpImage(modifier: Modifier = Modifier)
{
    val image = painterResource(R.drawable.bg_compose_background)
    Box(modifier){
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeTutorialTheme {
        Body(
            message = stringResource(R.string.compose),
            from = stringResource(R.string.Jetpack_compose),
            to = stringResource(R.string.Description))
    }
}
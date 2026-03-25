package ni.edu.uam.cuadrantedecompose

import android.os.Bundle
import android.text.style.BackgroundColorSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ni.edu.uam.cuadrantedecompose.ui.theme.CuadranteDeComposeTheme
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CuadranteDeComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Cuadros(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Seccion(Titulo: String, Cuerpo: String, backgroundColor: Color, modifier: Modifier = Modifier)
{
    Column(modifier
        .background(backgroundColor)
        .padding(16.dp)
        .fillMaxSize(), verticalArrangement = Arrangement.Center, Alignment.CenterHorizontally)
    {
        Text(
            text = Titulo,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = Cuerpo,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun Cuadros(modifier: Modifier = Modifier) {
    Column(modifier =modifier.fillMaxSize())
    {
        Row(Modifier.weight(1f))
        {
            Seccion(
                Titulo = "Text composable",
                Cuerpo = "Displays text and follows the recommended Material Design guidelines.",
                backgroundColor = Color(0xFFEADDFF),
                modifier = Modifier.weight(1f)
            )
            Seccion(
                Titulo = "Image composable",
                Cuerpo = "Creates a composable that lays out and draws a given Painter class object.",
                backgroundColor = Color(0xFFD0BCFF),
                modifier = Modifier.weight(1f)
            )
        }
        Row (Modifier.weight(1f)){
            Seccion(
                Titulo = "Row composable",
                Cuerpo = "A layout composable that places its children in a horizontal sequence.",
                backgroundColor = Color(0xFFB69DF8),
                modifier = Modifier.weight(1f)
            )
            Seccion(
                Titulo = "Column composable",
                Cuerpo = "A layout composable that places its children in a vertical sequence.",
                backgroundColor = Color(0xFFF6EDFF),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CuadrosPreview() {
    CuadranteDeComposeTheme {
        Cuadros()
    }
}
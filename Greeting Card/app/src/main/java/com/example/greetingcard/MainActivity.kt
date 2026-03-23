package com.example.greetingcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greetingcard.ui.theme.GreetingCardTheme

// Actividad principal
// Declaración de la clase
class MainActivity : ComponentActivity() {
    override fun onCreate //Se ejecuta al iniciar la app
                (savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) //Inicializa la actividad (obligatorio)
        enableEdgeToEdge()
        //se usa para definir el diseño.
        setContent { // Se deben usar funciones de componibilidad, usando @Composable
            GreetingCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Manuel",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

//Siempre poner @Composable antes de una funcion
//Los nombres de @Composanle deben estar en Mayus.
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // Alt + Enter para agregar funciones
    // Al usar color, usar importacion androidx.compose.ui.graphics.Color
    Surface(color = Color.Red) {
        Text(
            text = "Hi, my name is $name!",
            //Modificador de padding`, para aumentar o decorar un elemento componible
            modifier = modifier.padding(24.dp) //agrega espacio alrededor del elemento
        )
    }
}
//Genera la vista previa
@Preview(showBackground = true) //showbackground gener
@Composable
fun GreetingPreview() {
    GreetingCardTheme {
        Greeting("Manuel")
    }
}
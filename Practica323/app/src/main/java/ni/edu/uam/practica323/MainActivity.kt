package ni.edu.uam.practica323

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ni.edu.uam.practica323.ui.theme.Practica323Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practica323Theme {
                    Saludo()
                }
            }
        }
    }


//Leer el nombre y saludar
@Composable
fun Saludo(){

    var nombre by remember {mutableStateOf("")}
    var saludar by remember {mutableStateOf("")}

    Column(modifier = Modifier
        .padding(35.dp))
    {
        //Text(text = "Como te llamas?")
        TextField(
            value = nombre,
            onValueChange = {nombre = it},
            label = {Text("Dime tu nombre")}
        )

        Spacer(modifier = Modifier.padding(10.dp) )

        Button(onClick = {
            if (nombre.isNotEmpty()){
                saludar = "Hola, $nombre!"
            }else{
                saludar = ""
            }
        }) {
            Text("Saludar")
        }

        Text(text = saludar)
    }
}

package ni.edu.uam.practice233

import android.os.Bundle
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ni.edu.uam.practice233.ui.theme.Practice233Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practice233Theme {

            }
        }
    }
}

@Composable
fun Multi5()
{
    var num by remember { mutableStateOf("") }
    var res by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(35.dp))
    {
        TextField(
            value = num,
            onValueChange = {num = it},
            label = {Text("Dame un numero")}
        )

        Spacer(modifier = Modifier.padding(10.dp) )

        Button(onClick = {
            val numero = num.toIntOrNull()
            if (numero != null){
                res = if(num % 5 == 0){
                    "$num es multiplo de 5"
                }else{
                    "$num no es multiplo de 5"
                }
            }else{
                res = "Numero invalido"
            }
        }) {
            Text("Saludar")
        }
        Text(text = res)
    }
}

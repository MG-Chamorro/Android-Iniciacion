package ni.edu.uam.tarjetasdepresentacin

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ni.edu.uam.tarjetasdepresentacin.ui.theme.TarjetasDePresentaciónTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TarjetasDePresentaciónTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    cuerpo(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun logo(modifier: Modifier = Modifier)
{
    Image(
        painter = painterResource(R.drawable._c865b85539391_5d7f5ceca0b80),
        contentDescription = "Logo de android",
        modifier = Modifier.size(200.dp)
    )
}

@Composable
fun persona(nombre: String, titulo: String, modifier: Modifier = Modifier)
{
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text(
            text = nombre,
            fontWeight = Bold,
            fontSize = 38.sp,
            color = Color.White,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = titulo,
            color = Color.White
        )
    }
}

@Composable
fun info(phone:String, gmail: String, github: String, modifier: Modifier = Modifier)
{
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        infoCont(logo = R.drawable.mail_24dp_e3e3e3, cont = gmail)
        infoCont(logo = R.drawable.icons8_github_30, cont = github)
        infoCont(logo = R.drawable.local_phone_24dp_e3e3e3, cont = phone)
    }
}

@Composable
fun infoCont(cont: String, logo: Int,modifier: Modifier = Modifier)
{
    Row (verticalAlignment = Alignment.CenterVertically, modifier = modifier){
        Icon(
            painter = painterResource(logo),
            contentDescription = null,
            tint = Color(0xFF3ddc84),
            modifier = Modifier
                .size(48.dp) // aquí cambias el tamaño
                .padding(end = 16.dp)
        )
        Text(
            text = cont,
            color = Color.White,
            fontSize = 17.sp
        )
    }
}

@Composable
fun cuerpo(modifier: Modifier = Modifier)
{
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF042936))
            .padding(bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            logo()
            persona(
                nombre = "Manuel Chamorro",
                titulo = stringResource(R.string.Titulo)
            )
        }
        info(
            gmail = "mgchamorro@gmail.com",
            github = "github.com/MG-Chamorro",
            phone = "8942-1920")
    }
}

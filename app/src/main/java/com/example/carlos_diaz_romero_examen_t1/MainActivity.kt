import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.carlos_diaz_romero_examen_t1.R
import com.example.carlos_diaz_romero_examen_t1.SampleData
import com.example.carlos_diaz_romero_examen_t1.ui.theme.Carlos_Diaz_Romero_Examen_T1Theme

// Soy el número 7 de la lista

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent() {
            Carlos_Diaz_Romero_Examen_T1Theme {
                Scaffold (topBar ={
                    CenterAlignedTopAppBar(title = { Text(text ="App Descuentos")},
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
                    )
                }
                ) {
                    Conversacion(SampleData.conversationSample)
                }
            }
        }
    }
}

data class Mensajes(val autor: String, val mensaje: String)

@Composable
fun MensajesInidividuales(msg: Mensajes, numero: Int){
    Row (modifier = Modifier.padding(all = 8.dp)){
        Spacer(modifier = Modifier.width(8.dp))

        var estaExpandido by remember {
            mutableStateOf(false)
        }
        val colorFondo by animateColorAsState(
            if (estaExpandido) Color.White else MaterialTheme.colorScheme.primary,
        )

        Column (modifier = Modifier.clickable { estaExpandido = !estaExpandido }) {
            Text(
                text = msg.autor,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.width(4.dp))

            Surface (
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                color = colorFondo,
                modifier = Modifier
                    .padding(1.dp)
            ) {
                Text(
                    text = msg.mensaje,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (estaExpandido) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun cartaAlumno(nombre: String){
    Card (modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.avatar7),
            contentDescription = "Foto de Carlos",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, Color.Black)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = "Alumno: " + nombre)
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "Soy un alumno")
        }
    }
}

@Composable
fun Conversacion(lista: List<Mensajes>){
    Column {
        cartaAlumno(nombre = "Carlos")
        LazyColumn {
            items(lista) { mensaje ->
                MensajesInidividuales(msg = mensaje, numero = 7)
            }
        }
    }
}

@Preview
@Composable
fun PreviewConversacion(){
    Conversacion(SampleData.conversationSample)
}
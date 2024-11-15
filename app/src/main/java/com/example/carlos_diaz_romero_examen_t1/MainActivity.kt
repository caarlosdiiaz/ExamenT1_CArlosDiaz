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
import androidx.compose.material3.Button
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
import com.example.carlos_diaz_romero_examen_t1.R
import com.example.carlos_diaz_romero_examen_t1.SampleData
import com.example.carlos_diaz_romero_examen_t1.ui.theme.Carlos_Diaz_Romero_Examen_T1Theme

// Soy el número 7 de la lista
// Espero que tu lo puedas ver, yo lo hice todo a ciegas

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent() {
            Carlos_Diaz_Romero_Examen_T1Theme {
                Scaffold (topBar ={
                    CenterAlignedTopAppBar(title = { Text(text ="App Descuentos",
                        color = alumno7
                    )},
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

val alumno0=Color(0xFFEA80FC)
val alumno1=Color(0xFF8C9EFF)
val alumno2=Color(0xFFFFD180)
val alumno3=Color(0xFFFF9E80)
val alumno4=Color(0xFFA7FFEB)
val alumno5=Color(0xFFDD2C00)
val alumno6=Color(0xFF64DD17)
val alumno7=Color(0xFF00BFA5)
val alumno8=Color(0xFFAA00FF)
val alumno9=Color(0xFFFFD600)

data class Mensajes(val autor: String, val mensaje: String)

// Creo cada mensaje individual
@Composable
fun MensajesInidividuales(msg: Mensajes, numero: Int){
    Row (modifier = Modifier.padding(all = 8.dp)){
        // Mostramos la foto del profesor ya que siempre es él
        // quien manda todos los mensajes
        Image(
            painter = painterResource(R.drawable.profesor),
            contentDescription = "Profesor",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, Color.Blue, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Boton
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Elegir nuevo color",
                color = Color.Blue)
        }

        Spacer(modifier = Modifier.width(8.dp))

        // creo dos variables, una para saber si esta expandido
        // y otra para medio animar al darle click
        var estaExpandido by remember {
            mutableStateOf(false)
        }
        val colorFondo by animateColorAsState(
            if (estaExpandido) Color.White else Color.Magenta,
        )

        // Creo la columna y lo pongo clicable para que al darle se cambie
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

// Creo la tarjeta de la parte de arriba
// En la que se ve la foto y el nombre
@Composable
fun cartaAlumno(nombre: String, numero: Int){
    Card (modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.avatar7),
            contentDescription = "Foto de Carlos",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, Color.Black, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = "Alumno: " + nombre)
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "Soy un alumno")
        }
    }
}

// Era para mandarle por parametros la imagen pero no me da tiempo
// de hacerlo todo
/*val avatares = Array(){
    R.drawable.avatar0,
    R.drawable.avatar1,
    R.drawable.avatar2,
    R.drawable.avatar3,
    R.drawable.avatar4,
    R.drawable.avatar5,
    R.drawable.avatar6,
    R.drawable.avatar7,
    R.drawable.avatar8,
    R.drawable.avatar9,
    R.drawable.profesor,
}*/

// Con la lista de los mensajes los mostramos todo
@Composable
fun Conversacion(lista: List<Mensajes>){
    Column {
        cartaAlumno(nombre = "Carlos", numero = 7)
        LazyColumn {
            items(lista) { mensaje ->
                MensajesInidividuales(msg = mensaje, numero = 7)
            }
        }
    }
}

// El preview para yo poder ver algo
@Preview
@Composable
fun PreviewConversacion(){
    Conversacion(SampleData.conversationSample)
}
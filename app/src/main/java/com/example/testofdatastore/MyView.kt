import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testofdatastore.ui.theme.TestOfDataStoreTheme
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

@Composable
fun MyView(controller: MyController) {
    var value by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = value) {
        value = controller.getValueFromDataStore()
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Current Value: $value",
            modifier = Modifier.padding(16.dp)
        )



        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                value++
                coroutineScope.launch {
                    controller.saveValueToDataStore(value)
                }
            }) {
                Text(text = "Increase")
            }

            Button(onClick = {
                value--
                coroutineScope.launch {
                    controller.saveValueToDataStore(value)
                }
            }) {
                Text(text = "Decrease")
            }
        }
    }
}

class MainActivity : ComponentActivity() {
    private val controller by lazy { MyController(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestOfDataStoreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyView(controller)
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun MyViewPreview() {
    TestOfDataStoreTheme {
        MyView(MyController(LocalContext.current))
    }
}


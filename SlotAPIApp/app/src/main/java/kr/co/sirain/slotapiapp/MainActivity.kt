package kr.co.sirain.slotapiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.sirain.slotapiapp.ui.theme.SlotAPIAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SlotAPIAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier.padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        MainScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen() {

    var linearSelected by remember { mutableStateOf(true) }
    var imageSelected by remember { mutableStateOf(true) }

    val onLinearClick = { value: Boolean ->
        linearSelected = value
    }

    val onTitleClick = { value: Boolean ->
        imageSelected = value
    }

    ScreenContent(
        linearSelected = linearSelected,
        imageSelected = imageSelected,
        onLinearClick = onLinearClick,
        onTitleClick = onTitleClick,
        titleContent = {
            if (imageSelected) {
                TitleImage(drawing = R.drawable.baseline_arrow_circle_down_24)
            } else {
                Text(
                    "Downloading", style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(30.dp)
                )
            }
        },
        progressContent = {
            if (linearSelected) {
                LinearProgressIndicator(Modifier.height(40.dp))
            } else {
                CircularProgressIndicator(
                    Modifier.size(200.dp),
                    strokeWidth = 18.dp
                )
            }
        }
    )
}

@Composable
fun ScreenContent(
    linearSelected: Boolean,
    imageSelected: Boolean,
    onTitleClick: (Boolean) -> Unit,
    onLinearClick: (Boolean) -> Unit,
    titleContent: @Composable () -> Unit,
    progressContent: @Composable () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        titleContent()
        progressContent()
        CheckBoxes(linearSelected, imageSelected, onTitleClick, onLinearClick)
    }
}

@Composable
fun TitleImage(drawing: Int) {
    Image(
        painter = painterResource(drawing),
        contentDescription = "title image",
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
    )
}

@Composable
fun CheckBoxes(
    linearSelected: Boolean,
    imageSelected: Boolean,
    onTitleClick: (Boolean) -> Unit,
    onLinearClick: (Boolean) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(20.dp)) {
        Checkbox(
            checked = imageSelected,
            onCheckedChange = onTitleClick
        )
        Text("Image Title")
        Spacer(Modifier.width(20.dp))
        Checkbox(
            checked = linearSelected,
            onCheckedChange = onLinearClick
        )
        Text("Linear Progress")
    }
}

//@Preview(showSystemUi = true)
//@Composable
//fun SimpleRow() {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//            .background(Color.LightGray),
//        horizontalArrangement = Arrangement.SpaceEvenly, // 요소들 사이에 균등한 간격
//        verticalAlignment = Alignment.CenterVertically // 세로 방향 중앙 정렬
//    ) {
//        Text(text = "Apple")
//        Text(text = "Banana")
//        Text(text = "Orange")
//    }
//}

@Preview(showSystemUi = true)
@Composable
fun ComposePreview() {
    SlotAPIAppTheme {
        MainScreen()
    }
}
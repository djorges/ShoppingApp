import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    val viewModel = MainViewModel()
    MaterialTheme {
        AppContent(viewModel = viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
){
    val products by viewModel.products.collectAsState()
    val scrollState = rememberLazyGridState()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Column(
            modifier = Modifier.fillMaxWidth()
        ){
            SearchBar(
                modifier = Modifier.fillMaxWidth(),
                query = "",
                onQueryChange = { },
                active = false,
                onActiveChange = { },
                onSearch = { },
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "")},
                placeholder = { Text("Search") }
            ){

            }
        }
        Spacer(modifier = Modifier.height(8.dp))
       LazyVerticalGrid(
           modifier = Modifier,
           state = scrollState,
           columns = GridCells.Adaptive(90.dp),
           contentPadding = PaddingValues(10.dp)
       ) {
           //Search Bar


           //Products List
           items(items = products, key = {it.id.toString()}){
               Card(
                   modifier = Modifier.padding(8.dp).fillMaxWidth(),
                   shape = RoundedCornerShape(16.dp),
                   elevation = 4.dp
               ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        AsyncImage(
                            model = it.image,
                            contentDescription = it.title,
                            modifier = Modifier.height(130.dp).padding(8.dp),
                            contentScale = ContentScale.Fit,
                        )
                        Text(
                            text = it.title.toString(),
                            maxLines = 2,
                            fontSize = 13.sp,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(horizontal = 8.dp).heightIn(min = 40.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.CenterStart
                        ){
                            Text(
                                text = "${it.price.toString()}$",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(horizontal = 8.dp).heightIn(min = 40.dp)
                            )
                        }
                    }
               }
           }
       }
    }
}

@Composable
fun RotatingImage(modifier: Modifier = Modifier) {
    var rotationAngle by remember { mutableStateOf(0f) }
    val rotationAnimation = rememberInfiniteTransition()
    val density = LocalDensity.current

    val rotation by rotationAnimation.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Box(
        modifier = Modifier.size(200.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            imageVector = Icons.Filled.Face,
            contentDescription = "Rotating Image",
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer(
                    rotationZ = rotationAngle
                )
        )
    }

    Button(onClick = { rotationAngle = rotation}) {
        Text("Rotate Image")
    }
}
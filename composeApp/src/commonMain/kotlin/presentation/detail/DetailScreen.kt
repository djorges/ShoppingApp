package presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.arkivanov.decompose.extensions.compose.subscribeAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    detailComponent: DetailComponent
){
    val modelState = detailComponent.model.subscribeAsState()
    val item = modelState.value.item

    val textTitleStyle = TextStyle(
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Scaffold(
            scaffoldState= rememberScaffoldState(),
            topBar = {
                //App Bar
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = item.category.toString().capitalize(Locale.current),
                            style = textTitleStyle,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    },
                    modifier = Modifier,
                    navigationIcon = {
                        //Back Button
                        IconButton(onClick = { detailComponent.onBackPressed() }){
                            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back Button")
                        }
                    },
                    actions = {
                        //Add to wishlist Button
                        IconButton(onClick = { /*  */ }) {
                            Icon(imageVector = Icons.Filled.Star, contentDescription = "Add to wishlist")
                        }
                    },
                    windowInsets = WindowInsets(0.dp),
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
                    scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
                )
            }
        ) {
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
            ){
                AsyncImage(
                    model = item.image,
                    contentDescription = item.title,
                    modifier = Modifier.height(230.dp).fillMaxWidth(),
                    contentScale = ContentScale.Fit,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp),
                    text = item.title.toString(),
                    style = textTitleStyle
                )
                Text(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp),
                    text = "$${item.price}",
                    color = Color(0xFF0F9D58),
                    style = textTitleStyle
                )
                Text(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp),
                    text = item.description.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray
                )
            }
        }
    }
}
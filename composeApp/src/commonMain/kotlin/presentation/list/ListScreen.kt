package presentation.list

import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.arkivanov.decompose.extensions.compose.subscribeAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    listComponent: ListComponent
){
    val products = listComponent.model.subscribeAsState()
    var query by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //Search Bar
        Column(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
        ){
            SearchBar(
                modifier = Modifier.fillMaxWidth(),
                query = query,
                onQueryChange = { query = it },
                onActiveChange = { },
                onSearch = { },
                active = false,
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
                placeholder = { Text("Search") }
            ){
                //TODO:
            }
        }
        //Products List
        LazyVerticalGrid(
            modifier = Modifier,
            state = rememberLazyGridState(),
            columns = GridCells.Adaptive(90.dp),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(items = products.value.items, key = {it.id.toString()}){
                Card(
                    modifier = Modifier.padding(8.dp).fillMaxWidth().clickable {
                        //Navigate to Details Screen
                        listComponent.onItemClicked(it)
                    },
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
                            fontWeight = FontWeight.SemiBold,
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
                                text = "$${it.price}",
                                fontSize = 13.sp,
                                color = Color(0xFF0F9D58),
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
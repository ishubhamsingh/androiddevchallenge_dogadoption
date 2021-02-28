package com.ishubhamsingh.androiddevchallenge.dogadoption.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.transform.CircleCropTransformation
import com.ishubhamsingh.androiddevchallenge.dogadoption.R
import com.ishubhamsingh.androiddevchallenge.dogadoption.data.Dog
import com.ishubhamsingh.androiddevchallenge.dogadoption.data.dogDataList
import com.ishubhamsingh.androiddevchallenge.dogadoption.ui.theme.greyText
import com.ishubhamsingh.androiddevchallenge.dogadoption.ui.theme.purple500
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun TopAppBarComponent(){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 0.dp, 8.dp, 0.dp)
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Outlined.Home,
                contentDescription = "Home",
                tint = purple500
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "LOCATION",
                color = greyText,
                style = MaterialTheme.typography.caption
            )
            Text(text = "Bengaluru, India",
                color = purple500,
                style = MaterialTheme.typography.button
            )
        }

        CoilImage(
            modifier = Modifier
                .width(32.dp)
                .height(32.dp),
            data = "https://github.com/ishubhamsingh.png",
            contentDescription = "profile_pic",
            fadeIn = true,
            requestBuilder = {
                transformations(CircleCropTransformation())
            }
        )
    }
}

@Composable
fun ContentHolder() {
    Surface(color = colorResource(id = R.color.greyBackground),
        shape = RoundedCornerShape(30.dp,30.dp,0.dp,0.dp),
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SearchViewComp()
            Spacer(modifier = Modifier.height(16.dp))
            DogItem(dogDataList.shuffled())
        }
    }
}

@Composable
fun SearchViewComp() {
    Row(
        Modifier
            .background(color = MaterialTheme.colors.background, shape = RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = MaterialTheme.colors.onBackground)
        Text(text = "Search Dogs", color = greyText, style = MaterialTheme.typography.button)
    }
}

@Composable
fun DogItem(dogList: List<Dog>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(dogList) {dog->
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable(onClick = {
                    println(dog.name)
                }),
            ) {
                Surface(modifier = Modifier
                    .height(150.dp)
                    .width(120.dp),
                    shape = RoundedCornerShape(20.dp),
                ) {
                    CoilImage(
                        data = dog.imgUrl,
                        contentDescription = "dog_item_pic",
                        fadeIn = true,
                        contentScale = ContentScale.FillBounds,
                        loading = {
                            Box(Modifier.matchParentSize()) {
                                CircularProgressIndicator(Modifier.align(Alignment.Center),color = purple500)
                            }
                        }
                    )
                }
                Surface(modifier = Modifier
                    .height(120.dp)
                    .width(230.dp),
                    shape = RoundedCornerShape(0.dp,20.dp,20.dp,0.dp),
                ) {
                    Column(modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(text = dog.name,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = purple500,
                            fontWeight = FontWeight.ExtraBold,
                            style = MaterialTheme.typography.button
                        )
                        Text(text = dog.gender,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = greyText,
                            fontWeight = FontWeight.Normal,
                            style = MaterialTheme.typography.caption)
                        Text(text = "${dog.age} old",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = greyText,
                            fontWeight = FontWeight.Normal,
                            style = MaterialTheme.typography.caption)

                        Text(text = dog.address,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = greyText,
                            fontWeight = FontWeight.Normal,
                            style = MaterialTheme.typography.caption)
                    }
                }
            }
        }
    }
}

@Composable
fun DogListScreen() {
    Column {
        TopAppBarComponent()
        ContentHolder()
    }
}

@Preview
@Composable
fun DogItemPreview() {
    DogItem(dogDataList.shuffled())
}
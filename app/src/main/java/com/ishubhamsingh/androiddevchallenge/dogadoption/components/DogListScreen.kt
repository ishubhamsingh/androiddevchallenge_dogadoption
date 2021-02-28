/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ishubhamsingh.androiddevchallenge.dogadoption.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import coil.transform.CircleCropTransformation
import com.ishubhamsingh.androiddevchallenge.dogadoption.R
import com.ishubhamsingh.androiddevchallenge.dogadoption.data.Dog
import com.ishubhamsingh.androiddevchallenge.dogadoption.data.dogDataList
import com.ishubhamsingh.androiddevchallenge.dogadoption.navigation.Navigation
import com.ishubhamsingh.androiddevchallenge.dogadoption.ui.theme.greyText
import com.ishubhamsingh.androiddevchallenge.dogadoption.ui.theme.purple500
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun TopAppBarComponent() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 0.dp, 8.dp, 0.dp)
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Outlined.Home,
                contentDescription = "Home",
                tint = purple500
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "LOCATION",
                color = greyText,
                style = MaterialTheme.typography.caption
            )
            Text(
                text = "Bengaluru, India",
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
fun ContentHolder(navController: NavController) {
    Surface(
        color = colorResource(id = R.color.greyBackground),
        shape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp),
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SearchViewComp()
            Spacer(modifier = Modifier.height(16.dp))
            DogList(dogDataList, navController)
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
fun DogList(dogList: List<Dog>, navController: NavController) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        itemsIndexed(dogList) { index, dog ->
            key(index) {
                DogItem(dog, navController)
            }
        }
    }
}

@Composable
fun DogItem(dog: Dog, navController: NavController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable(
                onClick = {
                    navController.navigate(Navigation.dogDetailRoute(dog.id))
                }
            )
            .padding(top = 4.dp, bottom = 4.dp)
            .padding(4.dp),
    ) {
        Card(
            modifier = Modifier
                .height(150.dp)
                .width(120.dp),
            backgroundColor = colorResource(id = R.color.cardBgColor),
            shape = RoundedCornerShape(20.dp),
            elevation = 4.dp,
        ) {
            CoilImage(
                data = dog.imgUrl,
                contentDescription = "dog_item_pic",
                fadeIn = true,
                contentScale = ContentScale.FillBounds,
                loading = {
                    Box(Modifier.matchParentSize()) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center), color = purple500)
                    }
                }
            )
        }
        Card(
            modifier = Modifier
                .height(120.dp)
                .width(230.dp),
            backgroundColor = colorResource(id = R.color.cardBgColor),
            shape = RoundedCornerShape(0.dp, 20.dp, 20.dp, 0.dp),
            elevation = 4.dp,
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = dog.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = purple500,
                    fontWeight = FontWeight.ExtraBold,
                    style = MaterialTheme.typography.button.copy(fontSize = 16.sp)
                )
                Text(
                    text = dog.gender,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = greyText,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.caption.copy(fontSize = 12.sp)
                )
                Text(
                    text = "${dog.age} old",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = greyText,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.caption.copy(fontSize = 12.sp)
                )

                Text(
                    text = dog.address,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = greyText,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.caption.copy(fontSize = 12.sp)
                )
            }
        }
    }
}

@Composable
fun DogListScreen(navController: NavController) {
    Column {
        TopAppBarComponent()
        ContentHolder(navController)
    }
}

@Preview
@Composable
fun DogListPreview() {
    DogList(dogDataList, rememberNavController())
}

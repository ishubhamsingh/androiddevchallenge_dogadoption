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

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.transform.CircleCropTransformation
import com.ishubhamsingh.androiddevchallenge.dogadoption.data.Dog
import com.ishubhamsingh.androiddevchallenge.dogadoption.data.dogDataList
import com.ishubhamsingh.androiddevchallenge.dogadoption.ui.theme.greyText
import com.ishubhamsingh.androiddevchallenge.dogadoption.ui.theme.purple500
import com.ishubhamsingh.androiddevchallenge.dogadoption.ui.theme.purple700
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun DogDetails(navController: NavController, dogId: Int) {
    val dog = dogDataList.filter { dog -> dog.id == dogId }.getOrNull(0)

    if (dog != null) {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            DogDetailsHeader(navController = navController, dog = dog)
            OwnerInfo(dog = dog)
            DogDescription(dog = dog)
            DogButtons(dog = dog)
        }
    } else {
        Text(text = "Dog not found.")
    }
}

@Composable
fun DogDetailsHeader(navController: NavController, dog: Dog) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
    ) {

        CoilImage(
            data = dog.imgUrl,
            contentDescription = "dog_pic",
            fadeIn = true,
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(400.dp),
            loading = {
                Box(Modifier.matchParentSize()) {
                    CircularProgressIndicator(
                        Modifier.align(Alignment.Center),
                        color = purple500
                    )
                }
            }
        )

        Box(modifier = Modifier.statusBarsPadding()) {
            Icon(
                imageVector = Icons.Default.ArrowBack, contentDescription = "back_arrow",
                modifier = Modifier
                    .padding(8.dp)
                    .size(32.dp)
                    .background(
                        color = MaterialTheme.colors.background,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { navController.popBackStack() }
                    .padding(4.dp),
                tint = MaterialTheme.colors.onBackground

            )
        }

        Card(
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp, top = 350.dp)
                .requiredHeight(120.dp)
                .fillMaxWidth()
                .padding(4.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = 4.dp
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(16.dp)
            ) {
                Text(
                    text = dog.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = purple500,
                    fontWeight = FontWeight.ExtraBold,
                    style = MaterialTheme.typography.button.copy(fontSize = 22.sp),
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = dog.gender,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = greyText,
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.caption.copy(fontSize = 14.sp)
                    )
                    Text(
                        text = "${dog.age} old",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = greyText,
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.caption.copy(fontSize = 14.sp)
                    )
                }
                Text(
                    text = dog.address,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = greyText,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.caption.copy(fontSize = 14.sp)
                )
            }
        }
    }
}

@Composable
fun OwnerInfo(dog: Dog) {
    Row(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 16.dp)
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CoilImage(
            modifier = Modifier
                .width(36.dp)
                .height(36.dp),
            data = "https://github.com/ishubhamsingh.png",
            contentDescription = "profile_pic",
            fadeIn = true,
            requestBuilder = {
                transformations(CircleCropTransformation())
            }
        )

        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = dog.ownerName,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = purple700,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.button.copy(fontSize = 14.sp),
                )
                Text(
                    text = dog.datePosted,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = greyText,
                    fontWeight = FontWeight.Light,
                    style = MaterialTheme.typography.caption.copy(fontSize = 12.sp)
                )
            }
            Text(
                text = "Owner",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = greyText,
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.caption.copy(fontSize = 12.sp)
            )
        }
    }
}

@Composable
fun DogDescription(dog: Dog) {
    Text(
        text = dog.description,
        maxLines = 5,
        overflow = TextOverflow.Clip,
        style = MaterialTheme.typography.caption.copy(fontSize = 12.sp),
        color = MaterialTheme.colors.onBackground.copy(alpha = 0.3f),
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, bottom = 16.dp)
            .padding(4.dp),
    )
}

@Composable
fun DogButtons(dog: Dog) {
    val context = LocalContext.current
    var isFav by remember { mutableStateOf(false) }
    val favIcon = if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 24.dp, end = 24.dp, bottom = 16.dp)
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { isFav = !isFav },
        ) {
            Icon(imageVector = favIcon, contentDescription = "fav", tint = Color.Red)
        }
        Button(
            onClick = {
                Toast.makeText(context, "Congrats! ${dog.name} is yours now.", Toast.LENGTH_SHORT).show()
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = purple700,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = "Adopt", style = MaterialTheme.typography.button)
        }
    }
}

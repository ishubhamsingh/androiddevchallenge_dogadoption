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
package com.ishubhamsingh.androiddevchallenge.dogadoption

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.ishubhamsingh.androiddevchallenge.dogadoption.components.DogDetails
import com.ishubhamsingh.androiddevchallenge.dogadoption.components.DogListScreen
import com.ishubhamsingh.androiddevchallenge.dogadoption.navigation.Navigation
import com.ishubhamsingh.androiddevchallenge.dogadoption.ui.theme.MyTheme
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                ProvideWindowInsets {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    NavGraph()
}

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Navigation.NAV_DOG_LIST_SCREEN) {
        composable(route = Navigation.NAV_DOG_LIST_SCREEN) {
            DogListScreen(navController = navController)
        }
        composable(
            route = Navigation.NAV_DOG_DETAILS_SCREEN,
            arguments = listOf(
                navArgument(Navigation.NAV_DOG_ID) {
                    type = NavType.IntType
                }
            )
        ) {
            DogDetails(navController = navController, dogId = it.getDogIdArgument(Navigation.NAV_DOG_ID))
        }
    }
}

fun NavBackStackEntry.getDogIdArgument(key: String) =
    arguments?.getInt(key) ?: error("$key not found")

@Preview("Light Theme", widthDp = 360, heightDp = 640, showBackground = true)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640, showBackground = true)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}

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
package com.ishubhamsingh.androiddevchallenge.dogadoption.navigation

object Navigation {
    const val NAV_DOG_LIST_SCREEN = "dog_list_screen"

    const val NAV_DOG_ID = "dogId"
    const val NAV_DOG_DETAILS_SCREEN = "dog_details_screen/{$NAV_DOG_ID}"

    fun dogDetailRoute(dogId: Int) = "dog_details_screen/$dogId"
}

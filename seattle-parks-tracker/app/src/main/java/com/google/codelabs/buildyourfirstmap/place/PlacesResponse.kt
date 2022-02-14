// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.codelabs.buildyourfirstmap.place

import com.google.android.gms.maps.model.LatLng

data class PlaceResponse(
    val locID: String,
    val lat: Double,
    val lng: Double,
    val name: String,
    val vicinity: String,
    val rating: Float
)

fun PlaceResponse.toPlace(): Place = Place(
    locID = locID,
    name = name,
    latLng = LatLng(lat, lng),
    address = vicinity,
    rating = rating,
    visited = false
)

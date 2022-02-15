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

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.codelabs.buildyourfirstmap.BitmapHelper
import com.google.codelabs.buildyourfirstmap.R
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer

/**
 * A custom cluster renderer for Place objects.
 */
class PlaceRenderer(
    private val context: Context,
    map: GoogleMap,
    private val clusterManager: ClusterManager<Place>
) : DefaultClusterRenderer<Place>(context, map, clusterManager) {
    fun setListener() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        clusterManager.markerCollection.setOnInfoWindowClickListener(object : GoogleMap.OnInfoWindowClickListener {
            override fun onInfoWindowClick(marker: Marker?) {
                val place = marker?.tag as? Place ?: return
                place.visited = !place.visited
                marker.setIcon(placeIcon(place))
                marker.showInfoWindow()
                val editor = preferences.edit()
                if (place.visited && !preferences.getBoolean(place.locID, false))
                    editor.putBoolean(place.locID, true)
                else if(!place.visited && preferences.getBoolean(place.locID, false))
                    editor.remove(place.locID)
                editor.apply()
            }
        })
    }

    /**
     * The icon to use for each cluster item
     */
    private val unvisitedParkIcon: BitmapDescriptor by lazy {
        val color = ContextCompat.getColor(context,
            R.color.colorPrimary
        )
        BitmapHelper.vectorToBitmap(
            context,
            R.drawable.ic_baseline_nature_people_24,
            color
        )
    }

    private val visitedParkIcon: BitmapDescriptor by lazy {
        val color = ContextCompat.getColor(context,
            R.color.colorAccent
        )
        BitmapHelper.vectorToBitmap(
            context,
            R.drawable.ic_baseline_nature_people_24,
            color
        )
    }

    private fun placeIcon(item: Place) : BitmapDescriptor = if (item.visited)
        visitedParkIcon else unvisitedParkIcon

    /**
     * Method called before the cluster item (i.e. the marker) is rendered. This is where marker
     * options should be set
     */
    override fun onBeforeClusterItemRendered(item: Place, markerOptions: MarkerOptions) {
        markerOptions.title(item.name)
            .position(item.latLng)
            .icon(placeIcon(item))
    }

    /**
     * Method called right after the cluster item (i.e. the marker) is rendered. This is where
     * properties for the Marker object should be set.
     */
    override fun onClusterItemRendered(clusterItem: Place, marker: Marker) {
        marker.tag = clusterItem
    }
}
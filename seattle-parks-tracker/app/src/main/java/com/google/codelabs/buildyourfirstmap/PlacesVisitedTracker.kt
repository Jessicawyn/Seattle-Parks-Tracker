package com.google.codelabs.buildyourfirstmap

class PlacesVisitedTracker {
    ///Create set when places visitedcalled --> add ID
    //when places unvisted called -->> remove ID

    val visitedSet = mutableSetOf<String>()

    fun hasVisitedPlace(locID: String): Boolean {
        // Read if this has been visited
        //return true or false
        if (visitedSet.contains(locID)) {
            return true
        }
        return false
    }

    fun placeVisited(locID: String) {
        //If Switch turned to on --> place visited and update storage
        visitedSet.add(locID)
    }

    fun placeUnvisited(locID: String) {
        //If switch turned to off --> not visited and update storage
        if (visitedSet.indexOf(locID) >= 0) {
            visitedSet.remove(locID)
        }
    }

}

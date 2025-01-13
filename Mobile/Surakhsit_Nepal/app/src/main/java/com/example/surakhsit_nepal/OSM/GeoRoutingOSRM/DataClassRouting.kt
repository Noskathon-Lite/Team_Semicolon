package com.example.osmandroid.OSM.GeoRoutingOSRM

data class RouteResponse(
    val code: String,
    val routes: List<Route>,
    val waypoints: List<Waypoint>
)



data class Route(
    val geometry: Geometry,  // Update this from String to Geometry
    val legs: List<Leg>,
    val weight_name: String,
    val weight: Double,
    val duration: Double,
    val distance: Double
)

data class Geometry(
    val coordinates: List<List<Double>>,  // List of [longitude, latitude]
    val type: String
)

data class Leg(
    val steps: List<Any>,  // Adjust if needed
    val summary: String,
    val weight: Double,
    val duration: Double,
    val distance: Double
)

data class Waypoint(
    val hint: String,
    val distance: Double,
    val name: String,
    val location: List<Double>
)

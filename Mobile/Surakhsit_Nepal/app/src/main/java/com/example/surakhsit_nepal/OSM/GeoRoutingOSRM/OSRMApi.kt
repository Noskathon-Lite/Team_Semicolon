package com.example.osmandroid.OSM.GeoRoutingOSRM

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OSRMApi {
    @GET("route/v1/driving/{startLon},{startLat};{endLon},{endLat}")
    suspend fun getRoute(
        @Path("startLat") startLat: Double,
        @Path("startLon") startLon: Double,
        @Path("endLat") endLat: Double,
        @Path("endLon") endLon: Double,
        @Query("overview") overview: String = "full",
        @Query("geometries") geometries: String = "geojson"
    ): RouteResponse

}


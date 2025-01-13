package com.example.osmandroid.OSM.GeoEncoding

data class GeocodeResponse(
    val lat: String, // Latitude as a string
    val lon: String, // Longitude as a string
    val display_name: String // Full address
)


data class ReverseGeocodeResponse(
    val display_name: String, // Full address
    val address: Address // Address details
)

data class Address(
    val road: String?,
    val suburb: String?,
    val city: String?,
    val state: String?,
    val country: String?,
    val postcode: String?
)


import React, { useState, useEffect } from "react";
import { MapContainer, TileLayer, useMap } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import L from "leaflet";
import "leaflet-routing-machine";

const OSMRouting = ({ currentLocation, destination }) => {
  const map = useMap();


  // Add routing control only destination aayesi
  useEffect(() => {
    if (!currentLocation || !destination) return; // Wait for current position and destination to be set

    const routingControl = L.Routing.control({
      waypoints: [
        L.latLng(currentLocation.lat, currentLocation.lng), // Start point
        L.latLng(destination.lat, destination.lng), // Destination point
      ],
      routeWhileDragging: false, 
      draggableWaypoints: false,
      createMarker: function() { return null; },
      showAlternatives: false, // Hide alternative routes
      lineOptions: {
        styles: [{  weight: 4, opacity: 1 }],
      },
      summaryTemplate: '',
    }).addTo(map);

    return () => {
      map.removeControl(routingControl); // Properly remove the control
    };
  }, [map, currentLocation, destination]); // Re-run when `position` changes

  return null;
};

export default OSMRouting;

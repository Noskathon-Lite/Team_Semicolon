import { MapContainer, TileLayer, useMap, Marker, Popup, useMapEvents } from 'react-leaflet'

import React, {useState, useEffect} from 'react'


import './OSM.css'
import LeafletControlGeocoder from './OSMGeoEncode'
import CurrentLocationMarker from './CurrentLocationMarker'
import OSMRouting from './OSMRouting'


function OSM() {
    const position = [27.673541044863676, 85.36675407160472]
    const [currentLocation, setCurrentLocation] = useState(null); // For the user's current location
    const [destination, setDestination] = useState(null); 
  return (
    <div className="map-container">
        {/* <input type="text" id="search" placeholder="Search for places" /> */}
        <MapContainer className='map' center={position} zoom={15} scrollWheelZoom={true} style={{ width: '100%', height: '100vh' }}>
            <TileLayer
            attribution='&copy; Blood Sewa Team'
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />
        
            <CurrentLocationMarker setCurrentLocation={setCurrentLocation} />
            <LeafletControlGeocoder setDestination={setDestination} />
            <OSMRouting currentLocation={currentLocation} destination={destination} />
       
        </MapContainer>
    </div>
  )
}




export default OSM
import { MapContainer, TileLayer, useMap, Marker, Popup, useMapEvents } from 'react-leaflet'

import React, {useState, useEffect} from 'react'

function LocationMarker({ setCurrentLocation }) {
    const [position, setPosition] = useState(null)
    const map = useMap()
  
    
    useEffect(() => {
      map.locate() 
      map.on('locationfound', (e) => {
        setPosition(e.latlng)
        setCurrentLocation(e.latlng)  
        map.flyTo(e.latlng, map.getZoom())
      })
  
      // location vettayena vane
      map.on('locationerror', () => {
        console.error('Location not found')
      })
  
      return () => {
        map.off('locationfound')
        map.off('locationerror')
      }
    }, [map, setCurrentLocation])
  
    // If position is null, we don't render anything hai ta
    return position === null ? null : (
      <Marker position={position}>
        <Popup>You are here</Popup>
      </Marker>
    )
  }

  export default LocationMarker;
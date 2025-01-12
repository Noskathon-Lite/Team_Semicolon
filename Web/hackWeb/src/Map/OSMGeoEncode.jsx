import React, { useEffect, useRef } from 'react';
import { useMap } from 'react-leaflet';
import 'leaflet/dist/leaflet.css';
import L from 'leaflet';
import 'leaflet-control-geocoder/dist/Control.Geocoder.css'; 
import 'leaflet-control-geocoder'; 

function LeafletControlGeocoder({ setDestination }) {
    const map = useMap();
    const markerRef = useRef(null);

  useEffect(() => {
    var geocoder = L.Control.Geocoder.nominatim();




    const geocoderControl = L.Control.geocoder({
      query: "",
      placeholder: "Search here...",
      defaultMarkGeocode: false, //yesma false garnu parxa nava arkei tira dekhauxa
      geocoder
    })
      .on("markgeocode", function (e) {
        var latlng = e.geocode.center;

        //agadi marker xa vane hatauxa
        if (markerRef.current) {
          map.removeLayer(markerRef.current);
        }

        
        const marker = L.marker(latlng)  //icons hatayesi kaam garo
          .addTo(map)
          .bindPopup(e.geocode.name)
          .openPopup();

        markerRef.current = marker;

        setDestination(latlng);
        map.fitBounds(e.geocode.bbox);
      })
      .addTo(map);

      //duita icon aako vara yo rakheko ho
      return () => {
        map.removeControl(geocoderControl);
      }
  }, [map, setDestination]);

  return null;
}


export default LeafletControlGeocoder;


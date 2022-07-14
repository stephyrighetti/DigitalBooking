import React, {useEffect, useState} from "react";
import"../../../css/body/products/MapsGoogle.css"
import {GoogleMap,useLoadScript,Marker,useGoogleMap} from '@react-google-maps/api'



export const MapsGoogle = ({lat,lng}) => {
    const {isLoaded}=useLoadScript({googleMapsApiKey:"AIzaSyBVuKHgN1V6tuzDKM8XIMe9dkFE2izt3Cg"})

    if(!isLoaded)return <div>Loading...</div>;
    return <LoadedMap lat={lat} lng={lng}/>

}

function LoadedMap({lat,lng}){
    
    const[random,setRandom]=useState('1')
    
    
    useEffect(() => {
        setTimeout(()=> setRandom('2'),1)
    
    }, []);
    
    const center={lat:parseFloat(lat),lng:parseFloat(lng)}
    const myLatLng = { lat:parseFloat(lat),lng:parseFloat(lng) } //marcador
    
    var createMapOptions={
        panControl:false,
        mapTypeControl: false,
        scrollwheel: false,
        fullscreenControl: false,
        zoomControl:true,
        streetViewControl:false,
        
    }
    return(

<div className='map-loaded'>
            
           <div className='map-loaded-container'>
             <div className='map-icon-location'> </div>
           <GoogleMap 
           zoom={15}
           center={center}
           mapContainerClassName='map-container' 
           options={createMapOptions} >
            
           <Marker position={myLatLng} key={random} />

   </GoogleMap>
   </div>
       </div>

    )
}

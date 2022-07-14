import React, {useState, useEffect} from 'react'
import { LocationOptions } from './LocationOptions';
import apiUrl from "../../../Config"
import "../../../css/body/searchBar/SearchLocationOpt.css"
import axios from 'axios';


export const SearchLocationsOpt = ({changeFlagLocations,newChosenLocation, query}) => {

  const [locations, setLocations] = useState([])
  
  const getLocations = () => {
    axios.get(`${apiUrl}/public/cities`)
      .then((res) => setLocations(res.data))
      .catch((error) => console.error(error))
  }
  
  useEffect(() => {
    getLocations()
  }, [])


  return (
    <div className="container-locations-options">
        {locations
          .filter((locationOpt)=>(locationOpt.name.toLowerCase().includes(query)))
          .map((locationOpt)=>(<LocationOptions  changeFlagLocations={changeFlagLocations} locationOpt={locationOpt} key={locationOpt.id} newChosenLocation={newChosenLocation} />))}
    </div>
  )
}

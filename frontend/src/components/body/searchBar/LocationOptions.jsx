import React from 'react'
import { GoLocation } from "react-icons/go";
import "../../../css/body/searchBar/LocationOption.css"

export const LocationOptions = ({changeFlagLocations, newChosenLocation,locationOpt}) => {

  const pruebaonclick=()=>{
    newChosenLocation(locationOpt.name,locationOpt.country.name,locationOpt.id)
    changeFlagLocations()
  }
  
  return (
    <div onClick={()=>{pruebaonclick()}} className='container-option'>
        <span  className='locations-option-icon'><GoLocation /></span>
        <div className='option-description-text'>
            <p className='city-name'>{locationOpt.name}</p>
            <p className='city-country'>{locationOpt.country.name}</p>
        </div>
    </div>
  )
}

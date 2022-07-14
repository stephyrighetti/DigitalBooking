import React from 'react'

export const CityOption = ({changeFlagLocations, city, newChosenLocation}) => {

  const pruebaonclick=()=>{
    newChosenLocation(city.name,city.country.name,city.id)
    changeFlagLocations()
  }
  
  return (
    <div onClick={()=>{pruebaonclick()}} className='container-category-option'>
        <div>
            <p>{city.name}, {city.country.name}</p>
        </div>
    </div>
  )
}

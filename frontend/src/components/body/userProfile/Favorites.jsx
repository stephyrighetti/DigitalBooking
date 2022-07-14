import axios from 'axios'
import React, { useState, useEffect , useContext} from 'react'
import { NavBarUser } from './NavBarUser'
import apiUrl from '../../../Config';
import { Accommodation } from '../accommodations/Accommodation';
import {AuthContext} from "../../auth/Auth.context.jsx"

import { ErrorFavoriteReserva } from './ErrorFavoriteReserva';
import { useParams } from 'react-router-dom';

export const Favorites = () => {
  const [accommodations, setAccommodations] = useState([])
  const { state: ContextState, logout } = useContext(AuthContext);
  const { user } = ContextState;
  const [error, setError] = useState("No has agregado ningun producto a favorito.")
  const {userId} = useParams()
  
  useEffect(() => {
    getFavoritesAccomodations()
    console.log(user)
  }, [])

  const getFavoritesAccomodations = ()=>{
    axios.get(`${apiUrl}/public/products/favorite-products/${userId}`)
      .then((res) => {setAccommodations(res.data)
      })
      .catch((error) => console.error(error))
  }

  return (
    accommodations.length !== 0 
    ? 
    <div>
        <NavBarUser/>
        <ul className="accommodation-list">
          {accommodations.map((accommodation)=>(<Accommodation key={accommodation.id+"a"} accommodation={accommodation}/>))}
        </ul>
    </div>
    :
    <div>
        <NavBarUser/>
        <div className='container-message-error'>
          <ErrorFavoriteReserva error={error} />
        </div>
    </div>
  )
}

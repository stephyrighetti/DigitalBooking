import axios from 'axios'
import React, { useState, useContext, useEffect } from 'react'
import { ErrorFavoriteReserva } from './ErrorFavoriteReserva'
import { NavBarUser } from './NavBarUser'
import {AuthContext} from "../../auth/Auth.context.jsx"
import { CardReservationResume } from './CardReservationResume'
import apiUrl  from "../../../Config"

export const UserReservations = () => {
  const [error, setError] = useState("Aún no has efectuado ninguna reserva.")
  const [reservations, setReservations] = useState([])
  const { state: ContextState, logout } = useContext(AuthContext);
  const { user } = ContextState;

  useEffect(() => {
    getReservations()
  }, [])
  
  const getReservations = ()=>{
    axios.get(`${apiUrl}/private/bookings/user/${user.id}`,
    {headers : { 
      'Content-Type': 'application/json', 
      'Authorization': `${user.type} ${user.token}`
    }})
      .then((res) => {setReservations(res.data)
        console.log(reservations)
      })
      .catch((error) => console.error(error))
  }

  return (
    reservations.length !== 0 
    ?
    <div className='container-prueba'>
      <NavBarUser />
      <ul className='container-reservations'>
        {reservations.map((reservation)=>(<CardReservationResume key={reservation.id} reservation={reservation}/>))}
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

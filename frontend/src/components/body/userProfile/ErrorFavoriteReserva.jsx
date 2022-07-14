import React from 'react'
import { FiAlertCircle } from "react-icons/fi";
import "../../../css/body/userProfile/ErrorFavoriteReservas.css"

export const ErrorFavoriteReserva = ({error}) => {
  
  return (
      <div className='container-error-fav-res'>
          <span className='icon-error-fav-res'><FiAlertCircle /></span>
          <span className='text-error-fav-res'>{error}</span>
      </div>
  )
}

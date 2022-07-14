import { NavBarUser } from './NavBarUser'
import React, {useState, useContext, useEffect} from "react";
import "../../../css/body/userProfile/UserProfile.css"
import {AuthContext} from "../../auth/Auth.context.jsx"
import { useParams } from 'react-router-dom';

export const UserProfile = () => {
  const { state: ContextState, logout } = useContext(AuthContext);
  const { user } = ContextState;
  useEffect(() => {
    window.scrollTo(0,0)

  }, []) 
  const {userId} = useParams()
  return (
    <div className=''>
      <NavBarUser/>
      <div className='container-user'>
        <div className='container-data-user'>
          <div className='container-data-user-detail'>
            <span>Nombre: </span><span>{user?.name}</span>
          </div>
          <div className='container-data-user-detail'>
            <span>Apellido: </span><span>{user?.surname}</span>
          </div>
          <div className='container-data-user-detail'>
            <span>Correo electrónico: </span><span>{user?.email}</span>
          </div>
          <div className='container-data-user-detail'>
            <span>Ciudad: </span><span>{user?.city}</span>
          </div>
        </div>

      </div>
    </div>
  )
}

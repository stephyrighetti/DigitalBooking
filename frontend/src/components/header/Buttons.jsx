import React from 'react'
import { Link } from 'react-router-dom'
import "../../css/layouts/Header.css"
import {useLocation} from "react-router-dom";


export const Buttons = () => {

  const location = useLocation();
  
  return (
    <div className="container-buttons-header">
      <div className="buttons-headers">
        <>
        <Link style={{display: (location.pathname === "/new-account") ? 'none' : 'flex'}} className="header-button1" to="/new-account">Crear cuenta</Link>
        <Link style={{display: (location.pathname === "/log-in") ? 'none' : 'flex'}} className="header-button2" to="/log-in">Iniciar sesión</Link>
        </>
      </div>
    </div>
  )
}


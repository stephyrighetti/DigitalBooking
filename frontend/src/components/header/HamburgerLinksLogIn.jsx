import React, { useContext } from "react";
import "../../css/header/HamburgerLinks.css";
import { User } from "../header/User";
import { AuthContext } from '../auth/Auth.context.jsx';

export const HamburgerLinksLogIn = () => {
  const { state: logout } = useContext(AuthContext);

  const onLogout = () => {
    logout();
  }

  return (
    <div className='container-buttons-menu2'>
      <span className="text-wish">¿Deseas </span>
      <span className="text-log-out" onClick={()=>onLogout()}>cerrar sesión?</span>
    </div>
  )
}

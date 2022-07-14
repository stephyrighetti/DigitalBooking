import React, {useState , useEffect, useContext} from "react";
import { Link, useLocation } from "react-router-dom";
import "../../css/header/HamburgerLinks.css";

export const HamburgerLinksLogOut = ({handlerHamburger}) => {
    const location = useLocation();
    return (
      <div className="container-buttons-menu">
        <>
        <Link onClick={()=>handlerHamburger()} style={{display: (location.pathname === "/new-account") ? 'none' : 'normal'}} className="hamburger-link" to="/new-account">Crear cuenta</Link>
        <hr className="hamburguer-hr"/>
        <Link onClick={()=>handlerHamburger()} style={{display: (location.pathname === "/log-in") ? 'none' : 'normal'}} className="hamburger-link" to="/log-in">Iniciar sesión</Link>
        </>
      </div>
    )
  }
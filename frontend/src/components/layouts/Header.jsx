import React, {useState , useEffect, useContext} from "react";
import { Link } from "react-router-dom";
import Logo from "../../img/logoDigitalBooking2.svg"
import "../../css/layouts/Header.css"
import { Buttons } from "../header/Buttons";
import { User } from "../header/User";
import { HamburgerMobile } from "../header/HamburgerMobile";
import { IoReorderThreeSharp } from "react-icons/io5";

import { AuthContext } from '../auth/Auth.context.jsx';


export const Header = () => {

  const { state: ContextState, logout } = useContext(AuthContext);
  const {
    isLoginPending,
    isLoggedIn,
    loginError
  } = ContextState;
  
  useEffect(() => {

  },[])
  
  const [clicked, setClicked] = useState(false);

  const handlerHamburger = () => {
    setClicked(false)
  }

  return (
    <header className="container-header">
      <div className="container-logo-dh">
        <Link to="/"><img src={Logo} className="logo-dh" alt=""></img></Link>
        <Link className="slogan" to="/"><p className="header-text">Sentite como en tu hogar</p></Link>
      </div>
      

      {(isLoggedIn) ? 
        <div className="container-header-user">
        <User />
          </div>
       :  
          <Buttons />
      }
        
      {!clicked && <span className="hamburger" onClick={() =>setClicked(true) } ><IoReorderThreeSharp /></span>}
      <div onClick={()=>setClicked(false)} className={clicked ? "container-hamburger-menu" : "container-hamburger-menu-none"}>
          {clicked && <HamburgerMobile handlerHamburger={handlerHamburger} />}
      </div>

    </header>
  );
};

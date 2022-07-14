import React, {useState, useContext} from "react";
import {
  FaFacebook,
  FaInstagram,
  FaLinkedinIn,
  FaTwitter,
} from "react-icons/fa";
import "../../css/header/HamburgerMobile.css";
import { HamburgerLinksLogOut } from "./HamburgerLinksLogOut";
import { ImCross } from "react-icons/im";
import { User } from "../header/User";
import { AuthContext } from '../auth/Auth.context.jsx';
import { HamburgerLinksLogIn } from "./HamburgerLinksLogIn";

export const HamburgerMobile = ({handlerHamburger}) => {
const [logInClassName, setLogInClassName] = useState("container-hamburger-main")
  const { state: ContextState, logout } = useContext(AuthContext);
  const {
    isLoginPending,
    isLoggedIn,
    loginError
  } = ContextState;

  const onLogout = () => {
    logout();
  }

  return (
    <div className="container-hamburger-menu-open">
        <div className="container-hamburger-header">
          <span onClick={()=>handlerHamburger()} className="exit"><ImCross /></span>

          {(isLoggedIn) ? (
            <User />
          ) : ( 
            <h3 className="menu-title">MENÚ</h3>
          )}
          
        </div>
        <div className={(isLoggedIn) ?  "container-hamburger-main2" : "container-hamburger-main"}>
          {(isLoggedIn) ? 
            <HamburgerLinksLogIn/> 
            : 
            <HamburgerLinksLogOut handlerHamburger={handlerHamburger}/> 
          }
          
          <div className="container-icons-menu">
            <span className="hamburger-icon"><FaFacebook /></span>
            <span className="hamburger-icon"><FaLinkedinIn /></span>
            <span className="hamburger-icon"><FaTwitter /></span>
            <span className="hamburger-icon"><FaInstagram /></span>
          </div>
        </div>
    </div>
  );
};

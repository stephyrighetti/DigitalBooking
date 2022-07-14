import React, { useContext } from 'react'
import {Link} from "react-router-dom";
import "../../../css/body/userProfile/UserProfile.css"
import { useLocation } from 'react-router-dom';
import { IoChevronBack } from "react-icons/io5";
import { AuthContext } from '../../auth/Auth.context';

export const NavBarUser = () => {
    const location = useLocation();
    const { state: ContextState, logout } = useContext(AuthContext);
    const { user } = ContextState;
  return (
    <nav className='navbar-user'>
        <ul>
          <div className='cointainer-options-user'>
            <li className='li-home'><Link to={`/user/${user.id}`} className={(location.pathname === `/user/${user.id}`  ? 'link-navbar-selected' : 'link-navbar')} >Mis datos</Link></li>
            <li className='li-home'><Link to={`/user/${user.id}/reservas`} className={(location.pathname === `/user/${user.id}/reservas` ? 'link-navbar-selected' : 'link-navbar')} >Mis reservas</Link></li>
            <li className='li-home'><Link to={`/user/${user.id}/favoritos`} className={(location.pathname === `/user/${user.id}/favoritos`  ? 'link-navbar-selected' : 'link-navbar')}  >Mis favoritos</Link></li>
          </div>
          <div className='cointainer-options-user'>
            <li ><Link to="/" className='link-home-user' ><span><IoChevronBack/></span></Link></li>
          </div>
        </ul>
    </nav>
  )
}

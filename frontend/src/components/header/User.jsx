import React, { useState, useEffect, useContext } from "react";
import { AuthContext } from "../auth/Auth.context.jsx";
import "../../css/header/User.css";
import { ImCross } from "react-icons/im";
import { Link, useNavigate } from "react-router-dom";

export const User = () => {
  const navigate = useNavigate();
  const { state: ContextState, logout } = useContext(AuthContext);
  const { user } = ContextState;

  const getFirstLetters = (str) => {
    const firstLetters = str
      .split(" ")
      .map((word) => word[0])
      .join("");

    return firstLetters;
  };

  const onLogout = () => {
    logout();
  };

  return (
    <div className="container-user-profile">
      {user?.role[0] === 'ADMIN' &&       
      <div className="administration">
        <Link to="/administracion">ADMINISTRACION</Link>
      </div>
      }
      <div
        className="user-profile-picture"
        onClick={() => navigate(`/user/${user.id}`)}
      >
        <span className="user-initials">
          {getFirstLetters(user.name) + getFirstLetters(user.surname)}
        </span>
      </div>
      <div className="container-username">
        <div>
          <p className="user-hi">Hola,</p>  
          <span onClick={() => onLogout()}>
            <ImCross className="user-exit" />
          </span>
        </div>
        <span className="user-name">{user.name + " " + user.surname}</span>
      </div>
    </div>
  );
};

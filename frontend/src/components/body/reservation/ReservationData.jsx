import React, {useState, useContext} from "react";
import "../../../css/body/reservation/ReservationData.css";
import {AuthContext} from "../../auth/Auth.context.jsx"

export const ReservationData = ({setStateCity}) => {


  const { state: ContextState, logout } = useContext(AuthContext);
  const { user } = ContextState;

  return (
    <div className="container-reservation-data">
      <div className="container-reservation-data-form">
        <div className="container-input">
          <label htmlFor="" className="data-text">Nombre</label>
          <input type="text" disabled className="data-input" placeholder={user.name}/>
        </div>
        <div className="container-input">
          <label htmlFor="" className="data-text">Apellido</label>
          <input type="text" disabled className="data-input" placeholder={user.surname}/>
        </div>
        <div className="container-input">
          <label htmlFor="" className="data-text">Correo Electrónico</label>
          <input type="email" disabled className="data-input" placeholder={user.email}/>
        </div>
        <div className="container-input">
            <label htmlFor="" className="data-text">Ciudad</label>
            {user.city !== "" ? <input type="text" disabled className="data-input" placeholder={user.city}/>
            : <input type="text" required className="data-input" onChange={(event) => setStateCity(event.target.value)}/>}
        </div>
      </div>
    </div>
  );
};

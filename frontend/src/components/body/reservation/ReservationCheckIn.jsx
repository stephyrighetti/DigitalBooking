import React from "react";
import { BsCheckCircle } from "react-icons/bs";
import "../../../css/body/reservation/ReservationCheckIn.css";

export const ReservationCheckIn = ({setBookingStartTime}) => {
  const array = [
    "10:00",
    "11:00", "12:00", "13:00", "14:00", "15:00",
    "16:00", "17:00", "18:00", "19:00", "20:00",
    "21:00", "22:00", "23:00"
  ];

  return (
    <div className="container-checkin-time">
      <h3 className="reservation-checkin-title">Tu horario de llegada</h3>
      <div className="container-checkin-form">
        <p className="checkin-text">
          <span className="checkin-icon"><BsCheckCircle /></span>
          Tu habitacion va a estar lista para el check-in entre las 10:00 AM y
          las 11:00PM
        </p>
        <label htmlFor="" className="checkin-label">Indicá tu horario estimado de llegada</label>
        <select 
        name="" 
        id="default" 
        defaultValue="default" 
        className="checkin-select" 
        required
        onChange={(event)=>setBookingStartTime(event.target.value)}>
          <option value="default" disabled hidden>Seleccionar hora de llegada</option>
          {
            array.map((element, index) => {
              return <option key={index} value={element}>{element}</option>})
          }
        </select>
      </div>
    </div>
  );
};

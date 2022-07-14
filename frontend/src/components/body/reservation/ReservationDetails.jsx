import React, { useState, useEffect, useContext } from "react";
import { AiFillStar } from "react-icons/ai";
import { GrLocation } from "react-icons/gr";
import { FiAlertCircle } from "react-icons/fi";
import { Link, useNavigate} from "react-router-dom";
import "../../../css/body/reservation/ReservationDetails.css";
import { DateContext } from "../../../context/DateContext";

export const ReservationDetails = ({ product, flagError }) => {

  const { startDate, endDate } = useContext(DateContext)
  const navigate = useNavigate()
  const estrellitas = (qualification) => {
    let cantidad = 1;
    if (qualification >= 9.0) cantidad = 5;
    else if (qualification >= 8.0) cantidad = 4;
    else if (qualification >= 7.0) cantidad = 3;
    else if (qualification >= 5.0) cantidad = 2;

    const array = [];

    for (let i = 0; i < 5; i++) {
      array.push(
        <span key={i} className={i < cantidad ? "stars-score" : "stars-score1"}>
          <AiFillStar />
        </span>
      );
    }

    return array;
  };

  return product === null ? (
    navigate(`/error404`)
  ) :(
    <div className="container-reservationdetails">
      <h2 className="title-details">Detalle de la reserva</h2>
      <div className="container-reservation-card">
        <div className="container-reservation-image">
          <img
            src={product.mainPicture}
            alt=""
            className="image-container-reservationdetails"
          />
        </div>
        <div className="container-reservation-details">
          <p className="details-category">HOTEL</p>
          <p className="details-name">{product.name}</p>

          <div className="container-stars-details">
            <span className="text-stars-details">{product.category.title}</span>
            {estrellitas(product.average).map((estrellita) => {
              return estrellita;
            })}
          </div>
          <div className="ilocationDetails">
            <GrLocation />
            <span className="ilocation-details-address">{product.addressAccommodation}</span>
            <span className="ilocation-details-text">{product.city}, {product.country}</span>
          </div>
          <div className="container-checkin-checkout">
              <div className="container-checkout container-checkin">
                  <span className="span-checkout"> Check in </span>
                  <span className="span-checkout">{startDate.toLocaleDateString()}</span>
              </div>
              <div className="container-checkout">
                <span className="span-checkout"> Check out </span>
                <span className="span-checkout">{endDate.toLocaleDateString()}</span>
              </div>
          </div>
          {flagError && 
          <div className="error-time">
            <p className="error-icon"><FiAlertCircle /></p>
            <p className="error-text">Debe seleccionar un horario de llegada para poder realizar la reserva.</p>
          </div>
          }
          <button  className="button-details" type="submit">Confirmar reserva</button>
        </div>
      </div>
    </div>
  );
};

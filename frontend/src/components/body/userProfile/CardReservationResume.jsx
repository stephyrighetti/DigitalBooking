import React from 'react'
import { GrLocation } from "react-icons/gr"; 
import "../../../css/body/userProfile/UserReservations.css"
import { AiFillStar } from "react-icons/ai";
import Moment from 'moment';


export const CardReservationResume = ({reservation}) => {

  const handlerDateFormat =(date)=>{
    return Moment(date).format('DD-MM-YYYY')
  }

  const estrellitas = (qualification) => {
    let cantidad = 1;
    if (qualification >= 9.0) cantidad = 5;
    else if (qualification >= 8.0) cantidad = 4;
    else if (qualification >= 7.0) cantidad = 3;
    else if (qualification >= 5.0) cantidad = 2;

    const array = [];

    for (let i = 0; i < 5; i++) {
      array.push(
        <span key={i} className={i < qualification ? "stars-score" : "stars-score1"}>
          <AiFillStar />
        </span>
      );
    }

    return array;
  };

  return (
    <li className='container-card-reservation-resume'>

        <div className='image-product-reservation'>
          <img className='image-reservation-resume' src={reservation.productDTO.mainPicture} alt=""/>
        </div>
        <div className='data-product-reservation'>
          <div>
            <p className='reservation-type-product'>HOTEL</p>
            <p className='reservation-product-name'>{reservation.productDTO.name}</p>
            <div>
              {estrellitas(5).map((estrellita) => {
                return estrellita;
              })}
            </div>
          </div>
          <div>
            <div className='container-address'>
              <span><GrLocation /></span>
              <span className='reservation-span'>{reservation.productDTO.addressAccommodation}</span>
            </div>
            <p className='reservation-product-location'>{reservation.productDTO.city}, {reservation.productDTO.country}</p>
          </div>
          <div className='container-dates-reservation-resume'>
              <div className='dates-reservation-resume'>
                <span>Check in: </span>
                <span>{Moment(reservation.startDate).format('DD/MM/YYYY')}</span>
              </div>
              <div className='dates-reservation-resume'>
                <span>Check out: </span>
                <span>{Moment(reservation.finalDate).format('DD/MM/YYYY')}</span>
              </div>
          </div>  
        </div>
    </li>

  )
}

import React,{} from "react";
import { BsFillPatchCheckFill} from "react-icons/bs";
import { Link } from "react-router-dom";
import "../../../css/body/reservation/ReservationSuccessful.css"
 
export const ReservationSuccessful =() =>{

  return(
      <div className="containerReservationSuccessfull">
          <div className="container-ok">
              <div className="container-icon"><BsFillPatchCheckFill size={80} style={{ color: 'var(--amarillo)' }}/></div>
              <p className="title-Successful">¡Muchas Gracias!</p>
              <p className="p-Successful"> Su reserva se ha realizado con éxito</p>
              <Link to="/" className="button-ok">Ok</Link>
          </div>
      </div>
  )
}


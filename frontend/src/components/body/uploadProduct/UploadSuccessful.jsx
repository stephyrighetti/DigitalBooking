import React,{} from "react";
import { BsFillPatchCheckFill} from "react-icons/bs";
import { Link } from "react-router-dom";
 
export const UploadSuccessful =() =>{
  return(
      <div className="containerReservationSuccessfull">
          <div className="container-ok">
              <div className="container-icon"><BsFillPatchCheckFill size={80} style={{ color: 'var(--amarillo)' }}/></div>
              <p className="p-Successful"> Tu propiedad se ha creado con éxito</p>
              <Link to="/" className="button-ok">Volver</Link>
          </div>
      </div>
  )
}
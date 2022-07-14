import React from "react";
import { Link } from "react-router-dom";
import "../../css/utilidades/errorNotFound.css";
import ImgError from "../../img/error403.png";

export const ErrorNotFound = () => {
  return (
    <div className="containerError">
      <div className="container-error-card">
        <div className="container-error-image">
          <img src={ImgError} alt="Error" className="container-image-error" />
        </div>
        <div className="container-error-image2">
          <div>
            <p className="title-error">¡UPS!</p>
            <p className="title-error">Página no encontrada</p>
            <p className="p-error">Lo sentimos, la página que estas buscando no pudo ser encontrada.</p>
          </div>
          {/* <p className="p-error">Por favor, vuelva a la página de inicio</p> */}
          <Link to="/" className="button-go-home">
            Volver a Inicio
          </Link>
        </div>
      </div>
    </div>
  );
};

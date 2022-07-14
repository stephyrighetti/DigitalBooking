import React from "react";
import "../../css/utilidades/errorNotFound.css";
import ImgError from "../../img/Ups.png";

export const ErrorNotProductFound = () => {
  return (
    <div className="containerOops">
      <div className="container-error-card container-oops-card">
        <div className="container-error-image">
          <img src={ImgError} alt="Error" className="container-image-error" />
        </div>
        <div className="container-error-image2">
          <p className="title-error">No se encontró ningún alojamiento</p>
          <p className="p-error">
            Lo sentimos, vuelva a intentar con otros filtros
          </p>
        </div>
      </div>
    </div>
  );
};

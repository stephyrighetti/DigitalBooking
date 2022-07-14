import React from "react";
import "../../css/utilidades/errorNotFound.css";
import ImgLoading from "../../img/thinking.jpg";

export const LoadingAccount = () => {
  return (
    <div className="containerError">
      <div className="container-error-card">
        <div className="container-error-image">
          <img src={ImgLoading} alt="Error" className="container-image-error" />
        </div>
        <div className="container-error-image2">
          <p className="title-error">¡Estamos creando su cuenta!</p>
          <p className="p-error">Por favor, espere un momento</p>
        </div>
      </div>
    </div>
  );
};

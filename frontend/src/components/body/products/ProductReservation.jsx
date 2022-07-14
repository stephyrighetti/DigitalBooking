import React from "react";
import { CalendarProduct } from "./CalendarProduct";
import "../../../css/body/products/ProductReservation.css"
import { Link } from "react-router-dom";


export const ProductReservation = ({product}) => {

  
  return (
    <div className="container-product-reservation">
      <p className="product-reservation-text">Fechas disponibles</p>
      <div className="container-reservation">
        <div className="container-calendar2">
          <CalendarProduct product={product}/>
        </div>
        <div className="container-button-reservation">
          <p className="text-button-reservation">Agregá tus fechas de viaje para obtener precios exactos</p>
          <Link to={`/productos/${product.id}/reserva`} className="button-reservation">Iniciar reserva</Link>
        </div>
      </div>
    </div>
  );
};

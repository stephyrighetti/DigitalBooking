import React, { useState } from "react";
import "../../../css/body/products/ProductData.css";
import { GrLocation } from "react-icons/gr";
import { Link, useNavigate } from "react-router-dom";
import { AiFillStar } from "react-icons/ai";

export const ProductData = ({ product }) => {
  const handlerTextQualification = (qualification) => {
    if (qualification >= 9.0) return "Excelente";
    if (qualification >= 8.0) return "Muy bueno";
    if (qualification >= 7.0) return "Bueno";
    if (qualification >= 5.0) return "Regular";
    if (qualification === 0 || qualification === 0.0) return "Sin calificaciones"
    return "Malo";
  };

  const estrellitas = (qualification) => {
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

  const navigate = useNavigate()

  return product === "" ? (
    navigate(`/error404`)
  ) : (
    <div className="container-product-info">
      <div className="container-location-product">
        <div className="icon-location-product">
          <span>
            <GrLocation />
          </span>
        </div>
        <div className="product-location-text">
          <p>
            {product.addressAccommodation} {product.city}, {product.country}
          </p>
          {/* <p className="addres-mobile">{product.address}</p> */}
        </div>
      </div>
      <div className="container-qualification-info">
        <div className="container-qualification-stars">
          <p className="product-text-qualification">
            {handlerTextQualification(product.average)}
          </p>
          {estrellitas(product.stars).map((estrellita) => {
            return estrellita;
          })}
        </div>
        <p className="product-text-number-qualification">{product.average}</p>
      </div>
    </div>
  );
};

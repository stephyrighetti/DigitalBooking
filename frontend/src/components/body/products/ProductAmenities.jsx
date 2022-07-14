import React from "react";
import { useNavigate } from "react-router-dom";
import "../../../css/body/products/ProductAmenities.css";
import { ProductAmenity } from "./ProductAmenity";

export const ProductAmenities = ({product}) => {
  const navigate = useNavigate()

  return product === "" ? (
    navigate(`/error404`)
  ) : (
    <div>
      <div className="container-amenities">
        <h3 className="product-amenities-text">¿Qué ofrece el lugar?</h3>
        <hr className="product-amenities-hr" />
        <ul className="container-amenity">
          {product.features.map((politica)=>{
            return(<ProductAmenity politica={politica} key={politica.id}/>)}
          )}
        </ul>
      </div>
    </div>
  );
};

import React from "react";
import "../../../css/body/products/ProductDescription.css";

export const ProductDescription = ({product}) => {
  return (
    <div className="container-product-description">
      <h3 className="product-slogan">{product.shortDescription}</h3>
      <p className="product-description-text">
        {product.longDescription}
      </p>
    </div>
  );
};

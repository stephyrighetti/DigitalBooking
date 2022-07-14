import React from 'react'
import "../../../css/body/products/ProductPolicies.css";
import { MapsGoogle} from './MapsGoogle';

export const ProductMap = ({product}) => {
  return (
    <div className="container-product-policies">
          <h3 className="product-amenities-text">¿Dónde vas a estar?</h3>
          <hr className="product-amenities-hr" />
        <div className="container-imagen-map">
          <p className="text-location-map">{product.city}, {product.country}</p>
          <div className="container-map">
            <MapsGoogle lat={product.latitude} lng={product.longitude} />
          </div>
        </div>
      </div>
  )
}

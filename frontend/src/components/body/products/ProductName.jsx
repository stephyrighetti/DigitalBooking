import React from 'react'
import "../../../css/body/products/ProductName.css";
import { IoChevronBack } from "react-icons/io5";
import { Link, useLocation, useParams } from 'react-router-dom';

export const ProductName = ({product}) => {
  const {accommodationId} = useParams()
  const location = useLocation();

  if(!product)
    return "loading..."
  return (
    <div className="container-product-name">
      <div className="container-product-category-name">
        <p className="product-category">{product.category}</p>
        <p className="product-name">{product.name}</p>
      </div>
      {(location.pathname === `/productos/${accommodationId}`) ? 
      <Link className="button-back-home" to="/"><span className="button-back-home"><IoChevronBack/></span></Link> 
      :
      <Link className="button-back-home" to={`/productos/${accommodationId}`}><span className="button-back-home"><IoChevronBack/></span></Link> 
      }
    </div>
  )
  
}

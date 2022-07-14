import axios from 'axios';
import React, {useState,useEffect} from 'react'
import { useParams } from 'react-router-dom';
import {ProductAmenities} from './ProductAmenities';
import { ProductData } from './ProductData';
import {ProductDescription} from './ProductDescription';
import {ProductImages} from './ProductImages';
import { ProductMap } from './ProductMap';
import {ProductName} from './ProductName';
import {ProductPolicies} from './ProductPolicies';
import {ProductReservation} from './ProductReservation';
import apiUrl from '../../../Config';

export const Products = ({setProduct,product}) => {
  const {accommodationId} = useParams()

  const getproduct = (id) => {
    axios
      .get(`${apiUrl}/public/products/${accommodationId}`)
      .then((res) => setProduct(res.data))
      .catch((error) => console.error(error))
  }
  
  useEffect(() => {
    window.scrollTo(0,0)
    getproduct(accommodationId)

  }, []) 

  return (
    product !== null &&
    <div className='container-products'>
      <ProductName product={product} />
      <ProductData product={product} />
      <ProductImages product={product} />
      <ProductDescription product={product} />
      <ProductAmenities product={product} />
      <ProductReservation product={product} />
      <ProductMap product={product} />
      <ProductPolicies product={product} />
    </div>
  )
}


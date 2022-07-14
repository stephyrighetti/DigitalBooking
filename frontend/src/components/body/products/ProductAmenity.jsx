import React from 'react'
import {BsCheckLg} from "react-icons/bs"
import {AiOutlineWifi,AiFillCar} from "react-icons/ai"
import {MdKitchen, MdPool} from "react-icons/md"
import {CgScreen} from "react-icons/cg"
import {FaRegSnowflake, FaPaw} from "react-icons/fa"
import { useNavigate } from 'react-router-dom'

export const ProductAmenity = ({politica}) => {
  const getIcon= (name)=>{
    const nameLowerCase = name.toLowerCase();
    if(nameLowerCase === "wifi") 
      return <AiOutlineWifi/>
    if(nameLowerCase === "mascota") 
      return <FaPaw/>
    if(nameLowerCase === "cocina") 
      return <MdKitchen/>
    if(nameLowerCase === "pileta") 
      return <MdPool/>
    if(nameLowerCase === "estacionamiento gratuito") 
      return <AiFillCar/>
    if(nameLowerCase === "televisor") 
      return <CgScreen/>
    if(nameLowerCase === "aire acondicionado") 
      return <FaRegSnowflake/>
    return <BsCheckLg/>
  }

  return  (
    <li className="product-amenity">
        <span className="product-icon">{getIcon(politica.name)}</span>
        <span>{politica.name}</span>
    </li>
  )
}

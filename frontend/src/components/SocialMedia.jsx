import React from 'react'
import {
    FaFacebook,
    FaInstagram,
    FaLinkedinIn,
    FaTwitter,
} from "react-icons/fa";
import "../css/layouts/Footer.css"
import "../css/body/products/ProductImages.css"

export const SocialMedia = ({className}) => {
  return (
    <div className={className}>
        <span><FaFacebook /></span>
        <span><FaLinkedinIn /></span>
        <span><FaTwitter /></span>
        <span><FaInstagram /></span>
    </div>
  )
}

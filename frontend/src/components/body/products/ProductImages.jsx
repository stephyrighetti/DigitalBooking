import React, { useState } from "react";
import "../../../css/body/products/ProductImages.css";
import { BsSuitHeart, BsShare } from "react-icons/bs";
import { ProductCarousel } from "./ProductCarousel";
import { useModal } from "../../utilidades/useModal";
import { SocialMedia } from "../../SocialMedia";
import { Heart } from "../../utilidades/Heart"

export const ProductImages = ({ product }) => {
  const [isOpenModal, openModal, closeModal] = useModal(false);

  const [flagHeart, setFlagHeart] = useState(false);
  const [flagSocialMedia, setFlagSocialMedia] = useState(false);
  const [classNameSocialMedia, setClassNameSocialMedia] =
    useState("social-media-icons");

  const firstProduct = product.images[0];
  const otherProducts = product.images.slice(1, 5);

  const addSocialMedia = () => {
    return (
      <div className="container-social-media-icons">
        <SocialMedia className={classNameSocialMedia} />
      </div>
    );
  };

  return (
    <>
      <div className="container-icons">
        <span
          onClick={() => setFlagSocialMedia(!flagSocialMedia)}
          className={
            flagSocialMedia ? "icon-share-product-clic" : "icon-share-product"
          }
        >
          <BsShare />
        </span>
        <span className="icon-heart-product">
          <Heart />
        </span>
      </div>
      <ProductCarousel
        isOpen={isOpenModal}
        closeModal={closeModal}
        imagenes={product?.images}
      >
        {" "}
      </ProductCarousel>
      <div className="container-product-images">
        <div className="container-product-principal">
          <ul className="products-list-principal">
            <li className="li-card-product-principal" key={firstProduct.id}>
              <div className="container-image-principal">
                <img
                  src={firstProduct.url}
                  alt=""
                  className="image-principal"
                />
              </div>
            </li>
          </ul>
        </div>

        <div className="container-product-secondary">
          <ul className="products-list-secondary">
            {otherProducts.map((imageC) => (
              <li className="li-card-product-secondary" key={imageC.id}>
                <div className="container-image-secondary">
                  <img src={imageC?.url} alt="" className="secondary-images" />
                </div>
              </li>
            ))}
          </ul>
        </div>
      </div>
      <div className="container-button-abrecarrusel">
        <p onClick={openModal} className="button-abrecarrusel">
          Ver más
        </p>
      </div>
    </>
  );
};

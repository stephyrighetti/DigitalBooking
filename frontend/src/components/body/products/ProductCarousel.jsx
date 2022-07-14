import React, {useState} from 'react';
import Carousel from 'react-gallery-carousel';
import "../../../css/body/products/ProductCarousel.css";
import { BsXLg } from "react-icons/bs";


export const ProductCarousel = ({ children, isOpen, closeModal,imagenes }) => {

  const images = imagenes.map((product) => ({
    src: product?.url,

  }));


  return (
    <div className='background-modal'>
      {isOpen &&

        <div className='modal is-open'>
          <div className='modal-container'>
            {children}

            <Carousel
              images={images}
              style={{ height: 500, width: 800 }}
              thumbnailWidth={"11em"}
              thumbnailHeight={"7em"}
              hasIndexBoard={'bottomCenter'}
              autoPlayInterval={3000}
            />

            <div className='container-iconX'>
              <button onClick={closeModal} className="icon-X-product">
                <BsXLg />
              </button>
            </div>
          </div>
        </div>
      }
      <div className='container-carouselTablet'>
        
        <Carousel
          images={images}
          style={{ height: 600, width: 900 }}
          autoPlayInterval={3000}
          hasThumbnails={false}
          hasLeftButton={false}
          hasRightButton={false}
          hasMediaButton={false}
          hasSizeButton={false}
          hasIndexBoard={"bottomRight"}
          isAutoPlaying={true}
        />

      </div>
        
      <div className='container-carouselMobile'>
        
        <Carousel
          images={images}
          style={{ height: 400, width: 500 }}
          autoPlayInterval={3000}
          hasThumbnails={false}
          hasLeftButton={false}
          hasRightButton={false}
          hasMediaButton={false}
          hasSizeButton={false}
          hasIndexBoard={"bottomRight"}
          isAutoPlaying={true}
        />

      </div>

    </div>


  );

};



import React, { } from 'react'
import "../../../css/body/uploadProduct/StarOption.css";


export const StarOption = ({ starId, rating, onMouseEnter, onMouseLeave, onClick }) => {
  
  let styleClass = "star-rating-blank";
  if (rating >= starId) {
    styleClass = "star-rating-filled";
  }

  return (
      <div
        className="star"
        onMouseEnter={onMouseEnter}
        onMouseLeave={onMouseLeave}
        onClick={onClick}
      >
        <svg
          height="54px"
          width="53px"
          className={styleClass}
          viewBox="0 0 25 23"
          data-rating="0"
        >
          <polygon
            strokeWidth="0"
            points="9.9, 1.1, 3.3, 21.78, 19.8, 8.58, 0, 8.58, 16.5, 21.78"
          />
        </svg>
      </div>
  );
};

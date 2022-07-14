import React, { useState } from "react";
import "../../../css/body/categories/Category.css";

export const Category = ({
  category,
  getCategoryAccommodations,
  setSelectedCategory,
  selectedCategory
}) => {
  return (
    <li
      onClick={() => {
        getCategoryAccommodations(category.id, category.description);
        setSelectedCategory(category.id);
      }}
      className="container-card-category"
    >
      <div
        className={
          selectedCategory === category.id
            ? "container-category container-category-clic"
            : "container-category"
        }
      >
        <div className="container-image">
          <img src={category.urlImage} alt="" className="image-category" />
        </div>
        <div className="container-info">
          <p className="text-name">{category.description}</p>
          <p className="text-description">
            {category.quantityProducts} {category.description.toLowerCase()}
          </p>
        </div>
      </div>
    </li>
  );
};

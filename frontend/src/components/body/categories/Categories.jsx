import React, { useState, useEffect } from "react";
import { Category } from "./Category";
import "../../../css/body/categories/Categories.css";
import axios from "axios";
import apiUrl from "../../../Config";
import { SkeletonCategory } from "../../skeleton/SkeletonCategory";

export const Categories = ({ getCategoryAccommodations, selectedCategory, setSelectedCategory}) => {
  const [categories, setCategories] = useState(null);

  const getCategories = () => {
    axios
      .get(`${apiUrl}/public/categories`)
      .then((res) => setCategories(res.data))
      .catch((error) => {
        console.error(error)});
  };

  useEffect(() => {
    getCategories();
  }, []);


  return (
    <div className="container-categories">
      <h2 className="title-categories">Buscar por tipo de alojamiento</h2>
      <ul className="categories-list">
        {categories ? (
          categories.map((category) => (
            <Category
              key={category.id + "c"}
              category={category}
              getCategoryAccommodations={getCategoryAccommodations}
              setSelectedCategory={setSelectedCategory}
              selectedCategory={selectedCategory}
            />
          ))
        ) : (
          <>
            <SkeletonCategory />
            <SkeletonCategory />
            <SkeletonCategory />
            <SkeletonCategory />
          </>
        )}
      </ul>
    </div>
  );
};

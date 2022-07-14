import React, { useState, useEffect } from "react";
import "../../../css/body/uploadProduct/UploadAmenity.css";
import apiUrl from "../../../Config";
import axios from "axios";
import { NewProductItems } from "./NewProductItems";

export const UploadAmenity = ({features, setFeatures, label, placeHolder, handlerOnChange, handlerCross}) => {
  const [amenities, setAmenities] = useState([]);

  const getAmenities = () => {
    axios
      .get(`${apiUrl}/public/features`)
      .then((res) => setAmenities(res.data))
      .catch((error) => console.error(error));
  };

  useEffect(() => {
    getAmenities();
  }, []);

  const amenityAlreadyExits = (name) =>{
    return amenities.some((amenity)=>amenity.name.toLowerCase() === name.toLowerCase())
  }


  const handleCheck = (event) =>{
    let updatedList=[...features]
    
    if(event.target.checked) {
      updatedList=[...features, {id:parseInt((event.target.value)),name:(event.target.name)}]
    } else {
      updatedList.splice(features.indexOf(event.target.value), 1);
    }
    setFeatures(updatedList);
    console.log(features);
  }

  return (
    <div className="container-UploadAmenity">
      <div className="unpload-amenity">
        {amenities.map((amenity) => (
          <div key={amenity.id+"d"} className="cointainer-check-input">
            <input
              key={amenity.id+"i"}
              type="checkbox"
              name={amenity.name}
              value={amenity.id}
              className="check-input-amenity"
              onChange={handleCheck}
            />
            {amenity.name}
          </div>
        ))}
      </div>
      {/* <NewProductItems label={label} placeHolder={placeHolder} handlerOnChange={handlerOnChange} handlerCross={handlerCross} amenityAlreadyExits={amenityAlreadyExits} /> */}
    </div>
  );
};

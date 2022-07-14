import axios from "axios";
import React, { useState , useEffect } from "react";
import { CityOption } from "./CityOption";
import apiUrl from "../../../Config"

export const SearchCityOpt = ({changeFlagLocations,newChosenLocation,query}) => {
  const [cities, setCities] = useState([]);

  const getCities = () => {
    axios
      .get(`${apiUrl}/public/cities`)
      .then((res) => setCities(res.data))
      .catch((error) => console.error(error));
  };

  useEffect(() => {
    getCities();
  }, []);

  return (
    <div className="container-categories-options">
      {cities.map((city) => (
        <CityOption
          changeFlagLocations={changeFlagLocations}
          city={city}
          key={city.id}
          newChosenLocation={newChosenLocation}
        />
      ))}
    </div>
  );
};

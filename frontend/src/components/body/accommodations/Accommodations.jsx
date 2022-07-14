import React,{useState, useEffect} from "react";
import { Accommodation } from "./Accommodation";
import '../../../css/body/accommodations/Accommodations.css'
import { SkeletonAccommodation } from "../../skeleton/SkeletonAccommodation";
import { ErrorNotProductFound } from "../../utilidades/ErrorNotProductFound";


export const Accommodations = ({accommodations, typeRecomendation}) => {

  return (
    <div className="container-accommodations">
      <h2 className="title-accommodations">{typeRecomendation}</h2>
      <ul className="accommodation-list">
        {
        accommodations 
        ?
        accommodations.map((accommodation) => (
          <Accommodation key={accommodation.id+"p"} accommodation={accommodation}  /> 
        ))
        :
        <>
          <SkeletonAccommodation />
          <SkeletonAccommodation />
          <SkeletonAccommodation />
          <SkeletonAccommodation />
        </>
        }
        {accommodations?.length === 0 && <ErrorNotProductFound />}
      </ul>
    </div>
  );
};

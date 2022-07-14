import React, { useState } from "react";
import "../../../css/body/searchBar/SearchBar.css";
import { GoLocation } from "react-icons/go";
import { BsCalendar4Event } from "react-icons/bs";
import { SearchLocationsOpt } from "./SearchLocationsOpt";
import { CalendarLocation } from "./CalendarLocation";
import { Link } from "react-router-dom";
import Moment from 'moment';

export const SearchBar = ({getCityAccommodations, getCityAndDatesAccommodations, setCitySelected, setSelectedCategory}) => {
  
  const [flagCalendar, setFlagCalendar] = useState(false)
  const [flagListLocations, setFlagListLocations] = useState(false)
  const [flagPlaceHolderLocations, setflagPlaceHolderLocations] = useState(false)
  const [chosenLocation, setChosenLocation] = useState("")
  const [ query, setQuery ] = useState("")
  const [flagPlaceHolderDate, setFlagPlaceHolderDate] = useState(false)
  const [chosenStartDate, setChosenStartDate] = useState("")
  const [chosenEndDate, setChosenEndDate] = useState("")
  const [chosenDate, setChosenDate] = useState("")
  const [idCity, setIdCity] = useState("")

  const newChosenDate = (event,startDate, endDate) => {
    event.preventDefault()
    setChosenStartDate(startDate)
    setChosenEndDate(endDate)
    setChosenDate(startDate.toLocaleDateString()+" - "+endDate.toLocaleDateString())
    setFlagPlaceHolderDate(true)
  }

  const newChosenLocation = (city,country,id) => {
    setChosenLocation(city+", "+country)
    setCitySelected(city+", "+country)
    setflagPlaceHolderLocations(true)
    setIdCity(id)
  }

  const AddCalendar=() => {
    return(
      <div className="container-list-locations container-list-locations-date">
        {<CalendarLocation newChosenDate={newChosenDate} changeFlagCalendar={changeFlagCalendar} />}
      </div>
    )
  }
  
  const AddListLocations = () => {
    return(
      <div className="container-list-locations">
        {<SearchLocationsOpt  changeFlagLocations={changeFlagLocations} newChosenLocation={newChosenLocation} query={query}/>}
      </div>
    )
  }

  const changeFlagCalendar = () => {
    setFlagCalendar(!flagCalendar)
  }

  const changeFlagLocations = () => {
    setFlagListLocations(!flagListLocations)
  }

  const formSearchHandler= (e)=>{
    e.preventDefault()
    setSelectedCategory("")

    if(chosenEndDate !== "" || chosenStartDate !== ""){
      getCityAndDatesAccommodations(idCity, Moment(chosenStartDate).format('YYYY-MM-DD'), Moment(chosenEndDate).format('YYYY-MM-DD'))
    }
    else{
      getCityAccommodations(idCity)
    }
    
  }



  return (
    <div className="container-searchBar">
      <h1 className="title-searchBar">Busca ofertas en hoteles, casas y mucho más</h1>
      <div className="container-form-searchBar">
        <form onSubmit={(e)=>formSearchHandler(e)} className="form-search-searchBar">
          <div className="container-inputs-searchBar">
            <div className="container-location-search-searchBar">
              <span className="icon-location-searchBar">{<GoLocation />}</span>
              <input 
               onClick={()=>{
                setFlagListLocations(!flagListLocations)
                setFlagCalendar(false)
              }} 
              type="text" 
              name="" 
              id="input-city" 
              className="input-location-search-searchBar" 
              placeholder={flagPlaceHolderLocations ? chosenLocation : "¿A dónde vamos?"}
              onChange={(e)=> setQuery(e.target.value)}
              />
            </div>
            {(flagListLocations && AddListLocations())}
          </div>

          <div className="container-inputs-searchBar">
            <div className="container-date-search-searchBar">
              <span className="icon-location-searchBar">{<BsCalendar4Event />}</span>
              <input onClick={()=>{
                setFlagCalendar(!flagCalendar)
                setFlagListLocations(false)
              }} type="text" className="input-date-searchBar" placeholder={flagPlaceHolderDate ? chosenDate : "Check in - Check out"} readOnly/>
            </div>
            {(flagCalendar && AddCalendar())}
          </div>

          <button type={"submit"} className="button-search-searchBar">Buscar</button>

        </form>
      </div>
    </div>
  );
};

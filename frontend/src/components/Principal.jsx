import apiUrl from '../Config';
import { SearchBar } from "./body/searchBar/SearchBar.jsx";
import { Categories } from "./body/categories/Categories.jsx";
import { Accommodations } from "./body/accommodations/Accommodations.jsx";
import { useEffect, useState } from 'react';
import axios from 'axios';

export const Principal = () => {

  const [citySelected, setCitySelected] = useState("")
  const [typeRecomendation, setTypeRecomendation] = useState("")
  const [accommodations, setAccommodations] = useState(null)
  const [selectedCategory, setSelectedCategory] = useState("");

  useEffect(() => {
      getRandomAccommodations()
    }, [])
  
  const getRandomAccommodations = ()=>{
    axios.get(`${apiUrl}/public/products/random/8`)
      .then((res) =>{ setAccommodations(res.data)
        setTypeRecomendation("Recomendaciones")
      })
      .catch((error) => console.error(error))
  }

  const getCityAccommodations = (id)=>{
    axios.get(`${apiUrl}/public/products/city-filter/${id}`)
      .then((res) => {setAccommodations(res.data)
        setTypeRecomendation(`Recomendaciones en ${citySelected}`)
      })
      .catch((error) => console.error(error))
  }

  const getCategoryAccommodations = (id, nameCategory)=>{
    axios.get(`${apiUrl}/public/products/category-filter/${id}`)
      .then((res) => {
        setAccommodations(res.data)
        setTypeRecomendation(`${nameCategory}`)
      })
      .catch((error) => console.error(error))
  }

  const getCityAndDatesAccommodations = (id, fechaInicio, fechaFin)=>{
    axios.get(`${apiUrl}/public/products/filter/${id}/${fechaInicio}/${fechaFin}`)
      .then((res) => {
        setAccommodations(res.data)
        setTypeRecomendation(`Recomendaciones en ${citySelected} en las fechas seleccionadas`)
      })
      .catch((error) => console.error(error))
  }

  return (
    <>
      <SearchBar  getCityAccommodations={getCityAccommodations} getCityAndDatesAccommodations={getCityAndDatesAccommodations} setCitySelected={setCitySelected} setSelectedCategory={setSelectedCategory} />
      <Categories  getCategoryAccommodations={getCategoryAccommodations} selectedCategory={selectedCategory} setSelectedCategory={setSelectedCategory} />
      <Accommodations accommodations={accommodations} typeRecomendation={typeRecomendation} />
    </>
  )
}

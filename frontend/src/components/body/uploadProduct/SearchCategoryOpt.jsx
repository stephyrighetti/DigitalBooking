import React, {useState, useEffect} from 'react'
import { CategoryOptions } from './CategoryOptions';
import apiUrl from "../../../Config"
import "../../../css/body/uploadProduct/SearchCategoryOpt.css"
import axios from 'axios';

export const SearchCategoryOpt = ({changeFlagCategory,newChosenCategory, query}) => {
    const [categories, setCategories] = useState([])
  
    const getCategories = () => {
      axios.get(`${apiUrl}/public/categories`)
        .then((res) => setCategories(res.data))
        .catch((error) => console.error(error))
    }
    
    useEffect(() => {
      getCategories()
    }, [])
  
  
    return (
      <div className="container-categories-options">
          {categories
            .map((categoryOpt)=>(<CategoryOptions  changeFlagCategory={changeFlagCategory} categoryOpt={categoryOpt} key={categoryOpt.id} newChosenCategory={newChosenCategory} />))}
      </div>
    )
}




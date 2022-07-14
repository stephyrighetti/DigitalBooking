import React from 'react'
import "../../../css/body/uploadProduct/CategorieOptions.css"


export const CategoryOptions = ({changeFlagCategory, newChosenCategory,categoryOpt}) => {

  const pruebaonclick=()=>{
    newChosenCategory(categoryOpt.title,categoryOpt.id)
    changeFlagCategory()
  }
  
  return (
    <div onClick={()=>{pruebaonclick()}} className='container-category-option'>
        <div>
            <p>{categoryOpt.title}</p>
        </div>
    </div>
  )
}



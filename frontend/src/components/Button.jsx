import React from 'react'

export const Button = ({typeButton,classNameButton,textButton}) => {
  return (
    <button type={typeButton} className={classNameButton}>
        {textButton}
    </button>
  )
}

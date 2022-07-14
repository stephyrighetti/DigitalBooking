import React, {useState} from 'react'
import { NewAccount } from './NewAccount'
import "../../../css/Home.css"
import { Header } from '../../layouts/Header'
import { Footer } from '../../layouts/Footer'

export const NewAccountContainer = () => {


  return (
    <div className="container-home">

        <div className="container-body">
            <NewAccount />
        </div>
        
    </div>
  )
}

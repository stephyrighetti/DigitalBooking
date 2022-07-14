import React, { useState, useEffect, useContext } from "react";
import { BsSuitHeartFill, BsWifi } from "react-icons/bs";
import { AiFillStar } from "react-icons/ai";
import { GrLocation } from "react-icons/gr";
import { AiOutlineWifi, AiFillCar } from "react-icons/ai";
import { MdKitchen, MdPool } from "react-icons/md";
import { CgScreen } from "react-icons/cg";
import { FaRegSnowflake, FaPaw } from "react-icons/fa";
import "../../../css/body/accommodations/Accommodation.css";
import apiUrl from "../../../Config";
import { Link, useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import { Heart } from "../../utilidades/Heart";
import { AuthContext } from "../../auth/Auth.context";
import Swal from 'sweetalert2'

export const Accommodation = ({ accommodation }) => {
  const { state: ContextState, logout, setUser } = useContext(AuthContext);
  const { user,isLoggedIn } = ContextState;
  const navigate = useNavigate();

  const [classNameTextVerMas, setClassNameTextVerMas] = useState(
    "text-description-accommodation"
  );
  const [flagClassHeart, setFlagClassHeart] = useState(false)
  const [nameButtonVerMas, setNameButtonVerMas] = useState("...más");
  const [flagVerMas, setFlagVerMas] = useState(false);
  const [flagHeart, setFlagHeart] = useState(false);

  const handlerVerMas = () => {
    setFlagVerMas(!flagVerMas);
    setClassNameTextVerMas("text-description-accommodation-unclip");
    setNameButtonVerMas("...menos");
  };
  const handlerVerMenos = () => {
    setFlagVerMas(!flagVerMas);
    setClassNameTextVerMas("text-description-accommodation");
    setNameButtonVerMas("...más");
  };
  const handlerTextQualification = (qualification) => {
    if (qualification >= 9.0 || qualification === 10.0) return "Excelente";
    if (qualification >= 8.0) return "Muy bueno";
    if (qualification >= 7.0) return "Bueno";
    if (qualification >= 5.0) return "Regular";
    if (qualification === 0.0 || qualification === 0) return "Sin puntuaciones";
    return "Malo";
  };

  const estrellitas = (qualification) => {
    const array = [];

    for (let i = 0; i < 5; i++) {
      array.push(
        <span key={i+"e"} className={i < qualification ? "stars-score" : "stars-score1"}>
          <AiFillStar />
        </span>
      );
    }

    return array;
  };

  const getIcon = (name) => {
    const nameLowerCase = name.toLowerCase();
    if (nameLowerCase === "wifi")
      return (
        <span key="name1" className="product-icon">
          <AiOutlineWifi />
        </span>
      );
    if (nameLowerCase === "mascota")
      return (
        <span key="name2" className="product-icon">
          <FaPaw />
        </span>
      );
    if (nameLowerCase === "cocina")
      return (
        <span key="name3" className="product-icon">
          <MdKitchen />
        </span>
      );
    if (nameLowerCase === "pileta")
      return (
        <span key="name4" className="product-icon">
          <MdPool />
        </span>
      );
    if (nameLowerCase === "estacionamiento gratuito")
      return (
        <span key="name5" className="product-icon">
          <AiFillCar />
        </span>
      );
    if (nameLowerCase === "televisor")
      return (
        <span key="name6" className="product-icon">
          <CgScreen />
        </span>
      );
    if (nameLowerCase === "aire acondicionado")
      return (
        <span key="name7" className="product-icon">
          <FaRegSnowflake />
        </span>
      );
  };

  const addFavorite = () => {
    const userParse = JSON.parse(localStorage.getItem("user"));
    if (!user) return (      
      Swal.fire({
      confirmButtonColor: '#FBC02D',
      width: '350px',
      icon: 'error',
      title: '',
      text: 'Debes estar logueado para agregar un producto a favoritos',
      footer: '<a href="/log-in">Deseas loguearte?</a>'
    }),
    setFlagClassHeart(false))
    
    const dataPost = {
      user: {
        id: userParse.id,
      },
      product: {
        id: accommodation.id,
      },
    };

    const dataDelete = user.favorites.find(favorite=> favorite.product.id === accommodation.id)

    const configPost = {
      method: "post",
      url: `${apiUrl}/public/favorites`,
      headers: {},
      data: dataPost,
    };

    const isAlreadySaved = user.favorites.some((favorite)=> favorite.product.id === accommodation.id)
    
      if(isAlreadySaved){
        const configDelete = {
          method: "delete",
          url: `${apiUrl}/public/favorites/${dataDelete.id}`,
          headers: {}
        };
        axios(configDelete)
        .then((response)=>{
          const newUser = {
            ...user, 
            favorites: user.favorites.filter((favorite)=> favorite.id !== dataDelete.id)
          }
          setUser(newUser);
        })
        .catch((error) => {
          console.error(error);
          alert(
            "ERROR DELETE"
          );
        });
      }else{
        axios(configPost)
          .then((response) => {
            const newUser = {
              ...user, 
              favorites: user.favorites.concat(response.data)
            }
            setUser(newUser);
          })
          .catch((error) => {
            console.error(error);
            alert(
              "ERROR POST"
            );
          });
      }
  };

  return (
    accommodation !== null && (
      <li className="container-accommodation-card">
          <div className="container-img-accommodation">
            {accommodation.mainPicture !== null && (
              <img
                src={accommodation.mainPicture}
                alt=""
                className="img-accommodation-card"
              />
            )}
            <span className={user?.favorites.some(((favorite)=> favorite.product.id === accommodation.id)) || flagClassHeart ? "icon-heart-accommodation-selected" : "icon-heart-accommodation" }>
              <Heart addFavorite={addFavorite} />
            </span>
          </div>
          <div className="container-data-accommodation">
            <div>
              <div className="container-accommodation-info">
                <div>
                  <div className="container-stars-accommodation">
                    <span className="text-stars-accommodation">
                      {accommodation.category}
                    </span>
                    {estrellitas(accommodation.stars).map((estrellita) => {
                      return estrellita;
                    })}
                  </div>
                  <p className="text-name-accommodation">
                    {accommodation.name}
                  </p>
                </div>
                <div className="container-qualification-accommodation">
                  <p className="text-number-qualification">
                    {((accommodation.average === 0) || (accommodation.average === 0.0)) ? "-" : accommodation.average }
                  </p>
                  <p className="text-qualification">
                    {handlerTextQualification(accommodation.average)}
                  </p>
                </div>
              </div>
              <div className="container-location-info-accomodation">
                <span className="icon-location-accomodation">
                  <GrLocation />
                </span>
                <div>
                  <span className="text-location-part-1">
                    {accommodation.city}, {accommodation.country}
                  </span>
                  <span className="text-location-part-2">
                    MOSTRAR EN EL MAPA
                  </span>
                </div>
              </div>
              <div className="container-services-accomodation">
                {accommodation.features.map((feature) => getIcon(feature.name))}
              </div>
            </div>
            <div className="container-description-accommodation container-description-plus-mas">
              <p className={classNameTextVerMas}>
                {accommodation.shortDescription}
              </p>
              <span
                onClick={
                  flagVerMas ? () => handlerVerMenos() : () => handlerVerMas()
                }
                className="text-description-acommodation-mas"
              >
                {nameButtonVerMas}
              </span>
            </div>
            <Link
              to={`/productos/${accommodation.id}`}
              className="button-more-description-accommodation"
            >
              Ver más
            </Link>
          </div>
      </li>
    )
  );
};

import React, { useEffect, useState } from "react";
import { IoChevronBack } from "react-icons/io5";
import { Link, useNavigate } from "react-router-dom";
import { SearchCategoryOpt } from "../uploadProduct/SearchCategoryOpt";
import "../../../css/body/uploadProduct/UploadProduct.css";
import { StarOption } from "./StarOption";
import axios from "axios";
import { NewProductItems } from "./NewProductItems";
import { BsQuestionSquareFill } from "react-icons/bs";
import { SearchCityOpt } from "./SearchCityOpt";
import apiUrl from "../../../Config";
import Swal from 'sweetalert2'


export const UploadProduct = () => {
  const navigate = useNavigate();

  const [name, setName] = useState("");
  const [shortDescription, setShortDescription] = useState("");
  const [longDescription, setLongDescription] = useState("");
  const [addressAccommodation, setAddressAccommodation] = useState("");
  const [latitude, setLatitude] = useState("");
  const [longitude, setLongitude] = useState("");
  const [city, setCity] = useState("");
  const [country, setCountry] = useState("");
  const [category, setCategory] = useState("");
  const [mainPicture, setMainPicture] = useState("");

  const [features, setFeatures] = useState([]);
  const [images, setImages] = useState([]);

  const [policiesNormas, setPoliciesNormas] = useState([]);
  const [policiesSalud, setPoliciesSalud] = useState([]);
  const [policiesCancelacion, setPoliciesCancelacion] = useState([]);

  const [rating, setRating] = useState(0);
  const [hoverRating, setHoverRating] = useState(0);
  const stars = [1, 2, 3, 4, 5];

  const [flagListCategory, setFlagListCategory] = useState(false);
  const [flagPlaceHolderCategory, setflagPlaceHolderCategory] = useState(false);
  const [chosenCategory, setChosenCategory] = useState("");
  const [idCategory, setIdCategory] = useState("");

  const [flagListLocations, setFlagListLocations] = useState(false);
  const [flagPlaceHolderLocations, setflagPlaceHolderLocations] =
    useState(false);
  const [chosenLocation, setChosenLocation] = useState("");
  const [idCity, setIdCity] = useState("");
  const [query, setQuery] = useState("");

  const [isShown, setIsShown] = useState(false);

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

  const handleCheck = (event) => {
    let updatedList = [...features];

    if (event.target.checked) {
      updatedList = [
        ...features,
        { id: parseInt(event.target.value), name: event.target.name },
      ];
    } else {
      updatedList.splice(features.indexOf(event.target.value), 1);
    }
    setFeatures(updatedList);
  };

  const changeFlagCategory = () => {
    setFlagListCategory(!flagListCategory);
  };

  const newChosenCategory = (category, id) => {
    setChosenCategory(category);
    setCategory(category);
    setflagPlaceHolderCategory(true);
    setIdCategory(id);
  };

  const AddListCategory = () => {
    return (
      <div className="container-upload-list-categories">
        <SearchCategoryOpt
          changeFlagCategory={changeFlagCategory}
          newChosenCategory={newChosenCategory}
          query={query}
        />
      </div>
    );
  };

  const changeFlagLocations = () => {
    setFlagListLocations(!flagListLocations);
  };

  const newChosenLocation = (city, country, id) => {
    setChosenLocation(city + ", " + country);
    setCity(city);
    setCountry(country);
    setflagPlaceHolderLocations(true);
    setIdCity(id);
  };

  const AddListLocations = () => {
    return (
      <div className="container-cities-options">
        <SearchCityOpt
          changeFlagLocations={changeFlagLocations}
          newChosenLocation={newChosenLocation}
          query={query}
        />
      </div>
    );
  };

  const handleOnChangePoliciesNormas = (description) => {
        setPoliciesNormas((prevState) => [
          ...prevState,
          { description: description, idPolicyTpe: 1 },
        ]);
  };

  const handleOnChangePoliciesSalud = (description) => {
        setPoliciesSalud((prevState) => [
          ...prevState,
          { description: description, idPolicyTpe: 2 },
        ]);
  };

  const handleOnChangePoliciesCancelacion = (description) => {
        setPoliciesCancelacion((prevState) => [
          ...prevState,
          { description: description, idPolicyTpe: 3 },
        ]);
  };

  const handleOnChangeImages = (url) => {
        setImages((prevState) => [
          ...prevState,
          { title: `imagen hotel ${name}`, url: url },
        ]);
  };

  const handlerCrossImage = (url) => {
    const array = images.filter((image) => image.url !== url);
    setImages(array);
  };

  const handlerCrossPolicyNormas = (description) => {
    const array = policiesNormas.filter(
      (policy) => policy.description !== description
    );
    setPoliciesNormas(array);
  };
  const handlerCrossPolicySalud = (description) => {
    const array = policiesSalud.filter(
      (policy) => policy.description !== description
    );
    setPoliciesSalud(array);
  };
  const handlerCrossPolicyCancelacion = (description) => {
    const array = policiesCancelacion.filter(
      (policy) => policy.description !== description
    );
    setPoliciesCancelacion(array);
  };

  const addProduct = (event) => {
    event.preventDefault();

    const user = JSON.parse(localStorage.getItem("user"));

    let newPolicies = [];

    policiesNormas.map((policy) => newPolicies.push(policy));
    policiesSalud.map((policy) => newPolicies.push(policy));
    policiesCancelacion.map((policy) => newPolicies.push(policy));

    const data = {
      name,
      shortDescription,
      longDescription,
      address: "address",
      addressAccommodation,
      average: 0.0,
      stars: rating,
      latitude,
      longitude,
      city,
      country,
      category,
      mainPicture,
      policies: newPolicies,
      images,
      features,
    };

    const config = {
      method: "post",
      url: `${apiUrl}/public/products/save-frontend`,
      headers: {},
      data: data,
    };

    axios(config)
      .then(() => {
        navigate("/administracion/producto/exitosa");
      })
      .catch((error) => {
        console.error(error);
        Swal.fire({
          confirmButtonColor: '#FBC02D',
          width: '350px',
          icon: 'error',
          text: 'Los datos de tu hotel tienen un error',
        })
      });

    setName("");
    setShortDescription("");
    setLongDescription("");
    setAddressAccommodation("");
    setLatitude("");
    setLongitude("");
    setCity("");
    setCountry("");
    setCategory("");
    setMainPicture("");

    setImages([]);
    setPoliciesNormas([]);
    setPoliciesSalud([]);
    setPoliciesCancelacion([]);
  };

  return (
    <div className="upload-product-container">
      <div className="upload-product-container-admin">
        <p className="text-admin">Administración</p>
        <Link className="button-back-home" to="/">
          <span className="button-back-home">
            <IoChevronBack />
          </span>
        </Link>
      </div>

      <h3 className="upload-product-title">Crear propiedad</h3>
      <div className="container-upload-form">
        <form
          onSubmit={(event) => addProduct(event)}
          className="container-upload-product-form"
        >
          <div className="container-upload-product-data">
            <div className="container-upload-name-category">
              <div className="container-upload-input">
                <label htmlFor="" className="upload-label">
                  Nombre de la propiedad
                </label>
                <input
                  type="text"
                  className="upload-input"
                  onChange={(event) => setName(event.target.value)}
                  required
                />
              </div>
              <div className="container-upload-input">
                <label htmlFor="" className="upload-label">
                  Categoría
                </label>
                <input
                  onClick={() => {
                    setFlagListCategory(!flagListCategory);
                    setFlagListLocations(false);
                  }}
                  required
                  type="text"
                  id="input-category"
                  className="upload-input"
                  value={chosenCategory}
                  placeholder={
                    flagPlaceHolderCategory
                      ? chosenCategory
                      : "Seleccione Categoria"
                  }
                  onChange={(e) => setQuery(e.target.value)}
                />
                {flagListCategory && AddListCategory()}
              </div>
            </div>
            <div className="container-upload-name-category">
              <div className="container-upload-input">
                <label htmlFor="" className="upload-label">
                  Dirección
                </label>
                <input
                  type="text"
                  className="upload-input"
                  onChange={(event) =>
                    setAddressAccommodation(event.target.value)
                  }
                  required
                />
              </div>
              <div className="container-upload-input">
                <label htmlFor="" className="upload-label">
                  Ciudad
                </label>
                <input
                  onClick={() => {
                    setFlagListLocations(!flagListLocations);
                    setFlagListCategory(false);
                  }}
                  required
                  type="text"
                  id="input-city"
                  className="upload-input"
                  value={chosenLocation}
                  placeholder={
                    flagPlaceHolderLocations
                      ? chosenLocation
                      : "Seleccione Ciudad"
                  }
                  onChange={(e) => setQuery(e.target.value)}
                />
                {flagListLocations && <AddListLocations />}
              </div>
            </div>

            <div className="container-upload-input">
              <label htmlFor="" className="upload-label">
                Descripción corta (hasta 250 caracteres)
              </label>
              <textarea
                maxLength="250"
                className="upload-input upload-input-description"
                onChange={(event) => setShortDescription(event.target.value)}
                required
              ></textarea>
            </div>

            <div className="container-upload-input">
              <label htmlFor="" className="upload-label">
                Descripción larga (hasta 500 caracteres)
              </label>
              <textarea
                maxLength="500"
                className="upload-input upload-input-description"
                onChange={(event) => setLongDescription(event.target.value)}
                required
              ></textarea>
            </div>

            <div className="container-upload-name-category">
              <div className="container-upload-input">
                <label htmlFor="" className="upload-label">
                  Latitud
                </label>
                <input
                  type="text"
                  className="upload-input"
                  onChange={(event) => setLatitude(event.target.value)}
                  required
                />
              </div>
              <div className="container-upload-input">
                <label htmlFor="" className="upload-label">
                  Longitud
                </label>
                <input
                  type="text"
                  className="upload-input"
                  onChange={(event) => setLongitude(event.target.value)}
                  required
                />
              </div>
            </div>
          </div>

          <div className="container-rating">
            <div>
              <div>
                <h3>Ingresar estrellas de su hospedaje</h3>
                <div className="container-startOption">
                  {stars.map((star, i) => (
                    <StarOption
                      key={i}
                      starId={i}
                      rating={hoverRating || rating}
                      onMouseEnter={() => setHoverRating(i)}
                      onMouseLeave={() => setHoverRating(0)}
                      onClick={() => setRating(i)}
                    />
                  ))}
                </div>
              </div>
              <span
                onMouseEnter={() => setIsShown(true)}
                onMouseLeave={() => setIsShown(false)}
              >
                <BsQuestionSquareFill />
              </span>
            </div>

            {isShown && (
              <div className="regulation-container">
                <p>
                  El alojamiento es quien facilita a Bookingdh.xyz esta
                  clasificación por estrellas, que suele estar regulada por una
                  organización oficial de clasificación hotelera o un tercero.
                </p>
              </div>
            )}
          </div>

          <div className="container-upload">
            <h3>Agregar atributos</h3>
            <div className="container-UploadAmenity">
              <div className="unpload-amenity">
                {amenities.map((amenity) => (
                  <div
                    key={amenity.id + "d"}
                    className="cointainer-check-input"
                  >
                    <input
                      key={amenity.id + "i"}
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
            </div>
          </div>

          <div className="container-upload">
            <h3>Políticas del producto</h3>
            <NewProductItems
              label="Normas de la casa"
              placeHolder="Descripción"
              handlerOnChange={handleOnChangePoliciesNormas}
              handlerCross={handlerCrossPolicyNormas}
            />
            <NewProductItems
              label="Salud y seguridad"
              placeHolder="Descripción"
              handlerOnChange={handleOnChangePoliciesCancelacion}
              handlerCross={handlerCrossPolicyCancelacion}
            />
            <NewProductItems
              label="Políticas de cancelación"
              placeHolder="Descripción"
              handlerOnChange={handleOnChangePoliciesSalud}
              handlerCross={handlerCrossPolicySalud}
            />
          </div>
          <div className="container-upload">
            <h3>Cargar imágenes</h3>
            <div className="upload-image">
              <label htmlFor="" className="upload-label">
                Imágen principal
              </label>
              <input
                type="text"
                placeholder="Insertar https://"
                className="upload-input-image"
                onChange={(event) => setMainPicture(event.target.value)}
                required
              />
            </div>
            <NewProductItems
              label="Imágenes"
              placeHolder="Insertar https://"
              handlerOnChange={handleOnChangeImages}
              handlerCross={handlerCrossImage}
            />
          </div>
          <button type="submit" className="upload-button">
            Crear
          </button>
        </form>
      </div>
    </div>
  );
};

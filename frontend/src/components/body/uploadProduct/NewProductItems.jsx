import React, { useState, useEffect } from "react";
import "../../../css/body/uploadProduct/UploadAmenity.css";
import "../../../css/body/uploadProduct/newProductItems.css";
import { BsFillPlusSquareFill, BsFillXSquareFill } from "react-icons/bs";
import { FiAlertCircle } from "react-icons/fi";

const AddedItem = ({ name, handlerCrossItems }) => {
  return (
    <li className="container-item">
      <p>{name}</p>
      <button onClick={() => handlerCrossItems(name)}>
        <BsFillXSquareFill />
      </button>
    </li>
  );
};

export const NewProductItems = ({
  label,
  placeHolder,
  handlerOnChange,
  handlerCross,
  amenityAlreadyExits,
}) => {
  const [input, setInput] = useState("");
  const [addedItems, setAddedItems] = useState([]);

  const [flagError, setFlagError] = useState(false);
  const [errorText, setErrorText] = useState("")
  const [errorTextBlank, setErrorTextBlank] = useState()
  const [valueInput, setValueInput] = useState("")
  const handlerCrossItems = (name) => {
    handlerCross(name);
    const array = addedItems.filter((item) => item !== name);
    setAddedItems(array);
  };

  const onClick = () => {
    if (input === "") {
      setErrorText("No se puede ingresar un dato vacío.")
      setFlagError(true);

    } else {
      if (addedItems.find((item) => item === input)) {
        setErrorText("El dato ingresado ya existe.")
        setFlagError(true);
      } else {
        setFlagError(false);
        setAddedItems((prevState) => [...prevState, input]);
      }
    }
    
    handlerOnChange(input);
    setInput("")
  };

  return (
    <div className="container">
      <div className="container-uploadAmenity-newIcon">
        <div className="container-uploadAmenity-newIcon-name">
          <label className="label-uploadAmenity" htmlFor="">
            {label || "Titulo"}
          </label>
          <input
            className="uploadAmenity-newIcon-name"
            placeholder={placeHolder}
            type="text"
            onChange={(event) =>{
              setInput(event.target.value)}}
            value={input}
          />
        </div>
        <div className="container-uploadAmenity-newIcon-button ">
          <span className="button-uploadAmenity" onClick={onClick}>
            <BsFillPlusSquareFill />
          </span>
        </div>
      </div>
      <ul className="container-list-items">
        {flagError && (
          <div className="error-item-repeted">
            <span>
              <FiAlertCircle />
            </span>
            <p>{errorText}</p>
          </div>
        )}
        {addedItems.map((item, index) => (
          <AddedItem
            key={index}
            name={item}
            handlerCrossItems={handlerCrossItems}
          />
        ))}
      </ul>
    </div>
  );
};

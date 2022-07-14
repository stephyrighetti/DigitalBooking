import React, {  useState } from "react";
import "../../../css/body/logIn-newAccount/LogIn.css";
import { BsEyeSlash, BsEye } from "react-icons/bs";
import { Link } from "react-router-dom";
import { useForm } from "react-hook-form";
import axios from "axios";
import apiUrl from '../../../Config';
import Error from "../spinner/Error";
import Success from "../spinner/Success";
import { LoadingAccount } from "../../utilidades/LoadingAccount";



export const NewAccount = () => {

  const [tipoPassword, setTipoPassword] = useState("password");
  const [flagIcon, setFlagIcon] = useState(false);
  
  const {
    register,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm();

  const [isLoading, setIsLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");
  const [success, setsuccess] = useState(false);


  const ShowPassword = () => {
    setTipoPassword("text");
    return (
      <span
        onClick={() => {
          setFlagIcon(!flagIcon);
        }}
        className={(errors?.password?.type === "required") || (errors.password?.type === "minLength") ? "icon-eye-password-bc" : "icon-eye-password"}
      >
        <BsEye />
      </span>
    );
  };

  const HidePassword = () => {
    setTipoPassword("password");
    return (
      <span
        onClick={() => {
          setFlagIcon(!flagIcon);
        }}
        className={(errors?.password?.type === "required") || (errors.password?.type === "minLength") ? "icon-eye-password-bc" : "icon-eye-password"}
      >
        <BsEyeSlash />
      </span>
    );
  };

const onSubmit = (data, e) => {
  e.preventDefault();

  addUser(data.email, data.password, data.firstName, data.lastName);
  
};
const onError = (errors, e) => {
  console.log(e);
};

  const addUser = (email, password, name, surname) => (async() => {

    var data = new FormData();
    data.append('email', email);
    data.append('password', password);
    data.append('name', name);
    data.append('surname', surname);
    data.append('city', "");
  
    var config = {
      method: 'post',
      url: `${apiUrl}/public/auth/signup`,
      headers: { },
      data : data
    };
  
    try {
      
      setIsLoading(true);
      await axios(config)
      .then(function (response) {
        if(response.data){
          // alert(response.data);
          setsuccess(true);
        }
      })
      .catch(function (error) {
        setIsLoading(false);
        if (error.response) {
          if(error.response.data.details[0]!== undefined){
            setErrorMessage(error.response.data.details[0]);
          }
        } else if (error.request) {
          setErrorMessage("Error de comunicación con el servidor!.");
        } else {
          setErrorMessage(`Error inesperado : ${error.message} `);
        }
        
      });
      
    } catch (error) {
      setIsLoading(false);
      setErrorMessage(`Error inesperado: ${error} `);
    }

  })();


  const renderComponent=(
  
    <div className="container-log-in">
      <h1 className="title-log-in">Crear cuenta</h1>
      <form className="form-log-in" onSubmit={handleSubmit(onSubmit, onError)}>
        <div className="container-name-last-n">
          <div className="container-name">
            <label htmlFor="name" className="form-log-in-label">Nombre</label>
            <input
              {...register("firstName", {
                required: true,
                minLength:2,
                maxLength: 40,
                pattern:
                  /^[a-zA-ZÀ-ÿ\u00f1\u00d1\s]+(\s*[a-zA-ZÀ-ÿ\u00f1\u00d1\s]*)*[a-zA-ZÀ-ÿ\u00f1\u00d1\s]+$/,
              })}
              className={(errors?.firstName?.type === "required") || (errors?.firstName?.type === "maxLength") || (errors?.firstName?.type === "pattern") ? "form-log-in-input form-log-in-input-bc" : "form-log-in-input"}
              type="text"
            />
            {errors?.firstName?.type === "required" && (
              <p className="validation-text">Este campo es requerido</p>
            )}
            {errors?.firstName?.type === "maxLength" && (
              <p className="validation-text">El nombre no puede tener mas de 40 caracteres</p>
            )}
            {errors?.firstName?.type === "minLength" && (
              <p className="validation-text">El nombre no cumple con la mínima cantidad de caracteres</p>
            )}
            {errors?.firstName?.type === "pattern" && (
              <p className="validation-text">Solo se permiten caracteres alfabéticos</p>
            )}
          </div>

          <div className="container-name">
            <label htmlFor="lastName" className="form-log-in-label">Apellido</label>
            <input
              {...register("lastName", {
                required: true,
                minLength:2,
                maxLength: 40,
                pattern:
                  /^[a-zA-ZÀ-ÿ\u00f1\u00d1\s]+(\s*[a-zA-ZÀ-ÿ\u00f1\u00d1\s]*)*[a-zA-ZÀ-ÿ\u00f1\u00d1\s]+$/,
              })}
              className={(errors?.lastName?.type === "required") || (errors?.lastName?.type === "maxLength") || (errors?.lastName?.type === "pattern") ? "form-log-in-input form-log-in-input-bc" : "form-log-in-input"}
              type="text"
            />
            {errors?.lastName?.type === "required" && (
              <p className="validation-text">Este campo es requerido</p>
            )}
            {errors?.lastName?.type === "pattern" && (
              <p className="validation-text">Solo se permiten caracteres alfabéticos</p>
            )}
            {errors?.firstName?.type === "minLength" && (
              <p className="validation-text">El nombre no cumple con la mínima cantidad de caracteres</p>
            )}
            {errors?.lastName?.type === "maxLength" && (
              <p className="validation-text">El apellido no puede tener mas de 40 caracteres</p>
            )}
          </div>
        </div>

        <label htmlFor="email" className="form-log-in-label ">Correo Electrónico</label>
        <input
          {...register("email", {
            required: true,
            pattern:
              /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
          })}
          
          className={(errors?.email?.type === "required" )|| (errors.mail) || (errors?.email?.type === "pattern") ? "form-log-in-input form-log-in-input-bc" : "form-log-in-input form-log-in-input-email"}
          type="email"

        />
        {errors?.email?.type === "pattern" && <p className="validation-text">Mail inválido</p>}
        {errors.email && <p className="validation-text">Mail inválido</p>}
        {errors?.email?.type === "required" && <p className="validation-text">Este campo es requerido</p>}


        <label htmlFor="password" className="form-log-in-label">Contraseña</label>

        <div className="container-icon-password">
          <input
            {...register("password", { minLength: 6, required: true })}
            className={((errors?.password?.type === "required") || (errors.password?.type === "minLength")) ? "form-input-bc" : "form-input-password form-log-in-input"}
            type={tipoPassword}
          />
          {flagIcon ? <ShowPassword /> : <HidePassword />}
        </div>
          {errors?.password?.type === "required" && (
            <p className="validation-text">Este campo es requerido</p>
          )}
          {errors.password?.type === "minLength" && (
            <p className="validation-text">La contraseña debe tener al menos 6 caracteres</p>
          )}

        <label htmlFor="confirmPass" className="form-log-in-label">Confirmar Contraseña</label>
          <input
             {...register("confirmPassword", {
              minLength: 6,
              required: true,
              validate: (val) => {
                if (watch("password") !== val) {
                  return "Las contraseñas no coinciden";
                }
              }
            })}
            className={errors.confirmPassword ? "form-log-in-input form-log-in-input-bc" : "form-log-in-input"}
            type={tipoPassword}

          />

        {errors.confirmPassword && <p className="validation-text">Las passwords no concuerdan</p>}
        
        <button className="button-forms-log-in" type="submit">Crear Cuenta</button>
      </form>

      <div className="container-options-log-in">
        <p className="text-part-1">¿Ya tienes una cuenta?</p>
        <Link to={'/log-in'} className="link">Iniciar sesión</Link>
      </div>
    </div>

  );

  return (
      <>
        {isLoading && <LoadingAccount/>}
        {(errorMessage!=="" && !isLoading) && <Error title={"Error"} message={errorMessage} redirectPath={"/recovery"} />}
        {(errorMessage==="" && !isLoading) && renderComponent}
        {success && <Success title={"Cuenta Creada"} message={"Ingrese a su correo para activar su cuenta!."} redirectPath={"/"} />}
      </>
  );
};

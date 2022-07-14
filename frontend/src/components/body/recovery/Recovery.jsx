import React,{useState} from "react";
import { Link } from "react-router-dom";
import apiUrl from '../../../Config';
import axios from "axios"
import LoadingSpinner from "../spinner/LoadingSpinner";
import Error from "../spinner/Error";
import { useForm } from "react-hook-form";
import "../../../css/body/logIn-newAccount/LogIn.css";


export const Recovery = () => {
  
  
  const {
    register,
    handleSubmit,
    formState: { errors},
  } = useForm();

  const [isLoading, setIsLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");

  const onSubmit = (data, e) => {
    e.preventDefault();
    recuperaCuenta(data.email);
    
  };
  const onError = (errors, e) => {
    console.log(e);
  };

  const recuperaCuenta=(email) => (async() => {

    var data = new FormData();
    data.append('email', email);

    var config = {
        method: 'post',
        url: `${apiUrl}/public/auth/recovery`,
        headers: { },
        data: data
    }
    let apiRes = null;
    try {
      setIsLoading(true);
      apiRes = await axios(config);
    } catch (error) {
      if(error.response.data.details[0]){
        setErrorMessage(error.response.data.details[0]);
      }else{
        setErrorMessage("No hay comunicación con el servidor!");
      }
        
    } finally {
      setIsLoading(false);
    }
  })();
  
  const renderComponent=(
    <div className="container-log-in">
      <h2 className="title-log-in">Recuperar contraseña</h2>
      <form className="form-log-in" onSubmit={handleSubmit(onSubmit, onError)}>
        
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

        <button className="button-forms-log-in" type="submit">Enviar</button>

      </form>

    </div>

  );

  return (
      <>
        {isLoading && <LoadingSpinner />}
        {(errorMessage!=="" && !isLoading) && <Error title={"Error"} message={errorMessage} redirectPath={"/new-account"} />}
        {(errorMessage==="" && !isLoading) && renderComponent}
      </>
  );
}





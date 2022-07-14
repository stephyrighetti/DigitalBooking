import React,{useState} from "react";
import { Link, useParams, useNavigate } from "react-router-dom";
import apiUrl from '../../../Config';
import axios from "axios"
import LoadingSpinner from "../spinner/LoadingSpinner";
import Error from "../spinner/Error";
import { useForm } from "react-hook-form";
import { BsEyeSlash, BsEye } from "react-icons/bs";
import "../../../css/body/logIn-newAccount/LogIn.css";
import Swal from 'sweetalert2'


export const RecoveryConfirm = () => {
  const {hashCode} = useParams();

  const navigate = useNavigate();
  
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

  const onSubmit = (data, e) => {
    e.preventDefault();
    recuperaCuenta(data.password);
    
  };
  const onError = (errors, e) => {
    console.log(e);
  };

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

  const recuperaCuenta=(password) => (async() => {

    var data = new FormData();
    data.append('password', password);

    var config = {
        method: 'post',
        url: `${apiUrl}/public/auth/recovery-confirm/${hashCode}`,
        headers: { },
        data: data
    }
    try {
      
      setIsLoading(true);
      await axios(config)
      .then(function (response) {
        Swal.fire({
          confirmButtonColor: '#FBC02D',
          icon: 'success',
          title: 'Genial!',
          text: 'Has cambiado tu contraseña con éxito'
        })
        navigate('/log-in', {replace: true});
      })
      .catch(function (error) {
        console.log(error);
        setErrorMessage(error.response.data.details[0]);
        
      });
      setIsLoading(false);
    } catch (error) {
      setErrorMessage(`Error inesperado: ${error} `);
    }  
    
  })();
  
  const renderComponent=(
    <div className="container-log-in">
      <h2 className="title-log-in">Recuperar cuenta</h2>
      <form className="form-log-in" onSubmit={handleSubmit(onSubmit, onError)}>
        
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

      <button className="button-forms-log-in" type="submit">Enviar</button>

      </form>
    </div>

  );

  return (
      <>
        {isLoading && <LoadingSpinner />}
        {(errorMessage!=="" && !isLoading) && <Error title={"Error"} message={errorMessage} redirectPath={"/"} />}
        {(errorMessage==="" && !isLoading) && renderComponent}
      </>
  );
}





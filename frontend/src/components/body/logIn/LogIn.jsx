import { useState, useContext, useEffect} from "react";
import React from "react";
import { Link, useNavigate, useLocation } from "react-router-dom";
import "../../../css/body/logIn-newAccount/LogIn.css";
import { BsEyeSlash, BsEye } from "react-icons/bs";

import { useSetState } from 'react-use';
import { AuthContext } from '../../auth/Auth.context.jsx';

const initialState = {
  email: '',
  password: ''
}

export const LogIn = () => {
  
  const [flagIcon, setFlagIcon] = useState(false);
  const [tipoPassword, setTipoPassword] = useState("password");
  
  
  const [flagCredential, setFlagCredential] = useState(false);
  const [bcClassname, setBcClassname] = useState("form-input-bc")
  const [gcClassname, setGcClassname] = useState("form-input-password form-log-in-input ")
  const [eyeBcClassname, setEyeBcClassname] = useState("icon-eye-password-bc")
  const [eyeGcClassname, setEyeGcClassname] = useState("icon-eye-password")

  const { state: ContextState, login } = useContext(AuthContext);
  const {
    isLoginPending,
    isLoggedIn,
    loginError
  } = ContextState;
  const [state, setState] = useSetState(initialState);

  const navigate = useNavigate();
  const location = useLocation();

  const onSubmit = (e) => {
    e.preventDefault();
    const { email, password } = state;

    
    login(email, 
      password, 
      success => {
        
        const redirectPath = location.state?.path || '/'
        navigate(redirectPath, {replace: true})
        
      });
  }
  useEffect(() => {
    if (isLoggedIn) {
      setState(initialState);

    } else if (loginError) {
      setFlagCredential(true);
    }
  }, [isLoggedIn, loginError]);


  const ShowPassword = () => {
    setTipoPassword("text")
    return(
      <span 
        onClick={()=>{setFlagIcon(!flagIcon)}} 
        className={flagCredential ? eyeBcClassname : eyeGcClassname}>
          <BsEye />
      </span>
    )
  }

  const HidePassword = () => {
    setTipoPassword("password")
    return(
      <span 
        onClick={()=>{setFlagIcon(!flagIcon)}} 
        className={flagCredential ? eyeBcClassname : eyeGcClassname}>
          <BsEyeSlash />
      </span>
    )
  }


  return (
    <div className="container-log-in">
      <h1 className="title-log-in">Iniciar sesión</h1>
      <form className="form-log-in" onSubmit={onSubmit}>
        <label htmlFor="email" className="form-log-in-label">Correo Electrónico</label>
        <input
          className={flagCredential ? "form-log-in-input form-log-in-input-bc" : "form-log-in-input"}
          type="email"
          id="email"
          onChange={(e) => setState({email: e.target.value})}
        />
        
        <label htmlFor="password" className="form-log-in-label">Contraseña</label>
        <div className="container-icon-password">
          <input
            className={flagCredential ?  bcClassname : gcClassname}
            type={tipoPassword}
            id="password"
            onChange={(e) => setState({password: e.target.value})}
          />
          {(flagIcon ? <ShowPassword /> : <HidePassword />)}
        </div>
        
        {flagCredential && <p className="validation-text">Por favor vuelva a intentarlo, sus credenciales son inválidas.</p>}

        <button className="button-forms-log-in" type="submit">Ingresar</button>
        
        { isLoginPending && <div>Please wait...</div> }
        { isLoggedIn && <div>Success.</div> }

      </form>

      <div>
        <div className="container-options-log-in">
          <p className="text-part-1">¿Olvidó su contraseña?</p>
          <Link to={'/recovery'} className="link">Recuperar</Link>
        </div>
      </div>
    </div>
  );
}





import React,{ useState, useContext, useEffect } from "react";
import { BsFillPatchCheckFill} from "react-icons/bs";
import { Link, useParams } from "react-router-dom";
import apiUrl from '../../../Config';
import axios from "axios"
import LoadingSpinner from "../spinner/LoadingSpinner";
import Error from "../spinner/Error";
import { AuthContext } from '../../auth/Auth.context.jsx';

import "../../../css/body/reservation/ReservationSuccessful.css"

export const NewAccountSuccess =() =>{
    const {hashCode} = useParams();

    //auth
    const { state: ContextState, loginAuto } = useContext(AuthContext);
    const {
        isLoggedIn
    } = ContextState;

    const [isLoading, setIsLoading] = useState(false);
    const [errorMessage, setErrorMessage] = useState("");

    useEffect(() => {
        
        if(!isLoggedIn){
            activaCuenta();
        }
      }, []);

    const activaCuenta=() => (async() => {
        var config = {
            method: 'post',
            url: `${apiUrl}/public/auth/activate/${hashCode}`,
            headers: { }
        }
        let apiRes = null;
        try {
          setIsLoading(true);
          apiRes = await axios(config);
        } catch (err) {
            console.log(err);
            setErrorMessage("No se puede activar su cuenta, intente más tarde");
        } finally {
            localStorage.setItem("user", JSON.stringify(apiRes.data));
            loginAuto();
            setIsLoading(true);
        }
      })();


      const renderComponent=(
        <div className="containerReservationSuccessfull">
          <div className="container-ok">
              <div className="container-icon"><BsFillPatchCheckFill size={80} style={{ color: 'var(--amarillo)' }}/></div>
              <p className="title-Successful">¡Felicitaciones!</p>
              <p className="p-Successful"> Ya eres parte de BookingDH</p>
              <Link to="/"><button className="button-ok">ok</button></Link>
          </div>
      </div>
      );

  return(
      <>
        {isLoading && <LoadingSpinner />}
        {(errorMessage!=="" && !isLoading) && <Error title={"Error al activar la cuenta"} message={"Este hash ya fue usado!"} redirectPath={"/"} />}
        {(errorMessage==="" && !isLoading) && renderComponent}
      </>
  )
}

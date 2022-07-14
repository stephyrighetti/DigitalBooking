import { BsFillPatchExclamationFill} from "react-icons/bs";
import "../../../css/body/reservation/ReservationSuccessful.css"

export default function Error({title, message, redirectPath}) {
  

  const navegar = ()=>{
    window.location.href=redirectPath
  }

  return (
    <div className="containerReservationSuccessfull">
          <div className="container-ok">
              <div className="container-icon"><BsFillPatchExclamationFill size={80} style={{ color: 'var(--red)' }}/></div>
              <p className="title-Successful">{title}</p>
              <p className="p-Successful">{message}</p>
              <button className="button-ok" onClick={navegar}>Ok</button>
          </div>
      </div>
  );
}
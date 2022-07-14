import { BsFillPatchCheckFill} from "react-icons/bs";
import "../../../css/body/reservation/ReservationSuccessful.css"

export default function Success({title, message, redirectPath}) {
  

  const navegar = ()=>{
    window.location.href=redirectPath
  }

  return (
    <div className="containerReservationSuccessfull">
          <div className="container-ok">
              <div className="container-icon"><BsFillPatchCheckFill size={80} style={{ color: 'var(--yellow)' }}/></div>
              <p className="title-Successful">{title}</p>
              <p className="p-Successful">{message}</p>
              <button className="button-ok" onClick={navegar}>Ok</button>
          </div>
      </div>
  );
}
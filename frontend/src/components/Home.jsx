import "../css/Home.css"
import { useState } from 'react';
import { Routes, Route} from "react-router-dom";
import { NewAccountContainer } from "./body/newAccount/NewAccountContainer";
import { LogInContainer } from "./body/logIn/LogInContainer";
import { Recovery } from "./body/recovery/Recovery";
import { RecoveryConfirm } from "./body/recovery/RecoveryConfirm";
import { Principal } from "./Principal";
import { Products } from "./body/products/Products";
import { Reservation } from "./body/reservation/Reservation";
import { ProtectedRoute } from "./auth/ProtectedRoute";
import { ReservationSuccessful } from "./body/reservation/ReservationSuccessful";
import { NewAccountSuccess } from "./body/newAccount/NewAccountSuccess"
import { UserReservations } from "./body/userProfile/UserReservations";
import { Favorites } from "./body/userProfile/Favorites";
import { UserProfile } from "./body/userProfile/UserProfile";
import { UploadSuccessful } from "./body/uploadProduct/UploadSuccessful";
import { UploadProduct } from "./body/uploadProduct/UploadProduct";
import { IsAdmin } from "./auth/IsAdmin";
import { ErrorNotFound } from "./utilidades/ErrorNotFound";

export const Home = () => {

  const [product, setProduct] = useState(null);
  
  return (
    <div className="container-home">
        <div className="container-body">
            <Routes>
                <Route path="new-account" element={<NewAccountContainer />} />
                <Route path="log-in" element={<LogInContainer  />} />
                <Route path="recovery" element={<Recovery />} />
                <Route path="/recovery-confirm/:hashCode" element={<RecoveryConfirm />} />
                <Route path="/" element={<Principal />} />
                <Route path="/productos/:accommodationId" element={<Products setProduct={setProduct} product={product} />} />
                <Route path="/productos/:accommodationId/reserva" element={<ProtectedRoute><Reservation product={product}/></ProtectedRoute> } />
                <Route path="/productos/:accommodationId/reserva/exitosa" element={<ReservationSuccessful />}/>
                <Route path="/activate/:hashCode" element={<NewAccountSuccess />}/>
                <Route path="/user/:userId" element={<ProtectedRoute><UserProfile /></ProtectedRoute>} />
                <Route path="/user/:userId/reservas" element={<ProtectedRoute><UserReservations /></ProtectedRoute>} />
                <Route path="/user/:userId/favoritos" element={<ProtectedRoute><Favorites /></ProtectedRoute>} />
                <Route path="/administracion/producto/exitosa" element={<IsAdmin><UploadSuccessful /></IsAdmin>}/>
                <Route path="/administracion" element={<IsAdmin><UploadProduct /></IsAdmin>}/>
                <Route path="/error403" element={<ErrorNotFound />} />
            </Routes>  
        </div>
    </div>
 
  );
};

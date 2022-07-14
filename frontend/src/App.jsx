import "./css/App.css";
import Layouts from "./components/layouts";
import { useState, useEffect, useContext} from "react"
import {
  BrowserRouter,
  Routes,
  Route,
} from "react-router-dom";
import axios from "axios";
import { AuthContext } from "./components/auth/Auth.context.jsx";
import { DateProvider } from "./context/DateContext";
import apiUrl from "./Config";


function App() {
  
  const { state: ContextState, loginAuto } = useContext(AuthContext);

  const [isLoading, setIsLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");

  const checkout = typeof window !== 'undefined' ? JSON.parse(localStorage.getItem('user')) : null

  useEffect(() => {
    if(checkout!==null){
      validateToken(checkout.token)
    }
  }, []);

  const validateToken=(myToken) => (async() => {
    
    var config = {
        method: 'post',
        url: `${apiUrl}/public/auth/validate-token/${myToken}`,
        headers: { },
        data: {}
    }
    try {
      await axios(config)
      .then(function (response) {
        loginAuto();
      })
      .catch(function (error) {
        console.log(error);
        
      });
    } catch (error) {
      setErrorMessage(`Error inesperado: ${error} `);
    }  
    
  })();

  return (
    <DateProvider >
      <BrowserRouter>
        <Routes>
          <Route path="*" element={<Layouts />} />  
        </Routes>
      </BrowserRouter>  
    </DateProvider>
  );
}

export default App;

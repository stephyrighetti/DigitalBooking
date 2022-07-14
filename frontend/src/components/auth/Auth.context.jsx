import React, { useEffect } from 'react';
import { useSetState } from 'react-use';
import apiUrl from "../../Config";
import axios from "axios";

export const AuthContext = React.createContext(null);

const initialState = {
  isLoggedIn: false,
  isLoginPending: false,
  loginError: null,
  user: null
}

export const ContextProvider = props => {
  const [state, setState] = useSetState(initialState);

  const setLoginPending = (isLoginPending) => setState({isLoginPending});
  const setLoginSuccess = (isLoggedIn) => setState({isLoggedIn});
  const setLoginError = (loginError) => setState({loginError});
  const setUser = (user) => setState({user});

  const login = (email, password, success) => {
    setLoginPending(true);
    setLoginSuccess(false);
    setLoginError(null);

    fetchLogin( email, password, error => {
      setLoginPending(false);

      if (!error) {
        setLoginSuccess(true);
        setUser(JSON.parse(localStorage.getItem("user")));

        success()
      } else {
        setLoginError(error);
      }
    })
  }
  useEffect(() => {
  }, [])
  

  const logout = () => {
    setLoginPending(false);
    setLoginSuccess(false);
    setLoginError(null);
    setUser(null);
    localStorage.clear();
  }

  const loginAuto = ()=>{
    setLoginSuccess(true);
    setLoginError(null);
    setLoginPending(false);
    setLoginError(null);
    setUser(JSON.parse(localStorage.getItem("user")));
  }

  return (
    <AuthContext.Provider
      value={{
        state,
        login,
        logout,
        loginAuto,
        setUser
      }}
    >
      {props.children}
    </AuthContext.Provider>
  );
};


const fetchLogin = (email, password, callback) => {
  var data = new FormData();
  data.append('email', email);
  data.append('password', password);

  var config = {
    method: 'post',
    url: `${apiUrl}/public/auth/signin`,
    headers: { 
      'content-type': 'multipart/form-data'
    },
    data : data
  };

  axios(config)
  .then(function (response) {
      myLocalStorage(response.data);
      callback(null);

  })
  .catch(function (error) {
    callback(new Error('Invalid email or password'))
  });

}

const myLocalStorage=(user)=>{
  if (typeof(Storage) !== "undefined") {
      localStorage.setItem("user", JSON.stringify(user));
  } else {

  }

}

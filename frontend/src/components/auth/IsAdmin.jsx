import { useContext } from 'react';
import { Navigate, useLocation } from 'react-router-dom'
import { AuthContext } from './Auth.context'

export const IsAdmin = ({ children }) => {
    const location = useLocation()

    const { state: ContextState } = useContext(AuthContext);
    const {isLoggedIn} = ContextState;

    const checkout = typeof window !== 'undefined' ? JSON.parse(localStorage.getItem('user')) : null

    if ((!isLoggedIn && checkout!==null)) {
        if(checkout.role){
            if(checkout.role.filter(i => (i === "USER")).length ) {
                return <Navigate to='/error403' state={{path: location.pathname}} />
            } 
        }
    }
    if(isLoggedIn === false){
        return <Navigate to='/error403' state={{path: location.pathname}} />
    }

    return children
}

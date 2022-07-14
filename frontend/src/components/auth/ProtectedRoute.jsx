import { useContext } from 'react';
import { Navigate, useLocation } from 'react-router-dom'
import { AuthContext } from './Auth.context'
import Swal from 'sweetalert2'


export const ProtectedRoute = ({ children }) => {
    const location = useLocation()

    const { state: ContextState } = useContext(AuthContext);
    const {isLoggedIn} = ContextState;

    if (!isLoggedIn) {

        return (
            Swal.fire({
                confirmButtonColor: '#FBC02D',
                width: '350px',
                icon: 'error',
                text: 'Debes estar logueado para realizar una reserva',
              }),
        <Navigate to='/log-in' state={{path: location.pathname}} />)
    }

    return children
}
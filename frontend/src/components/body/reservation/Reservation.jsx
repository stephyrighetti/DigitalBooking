import React, { useContext, useEffect, useState, createContext } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { CalendarProduct } from "../products/CalendarProduct";
import { ProductName } from "../products/ProductName";
import { ProductPolicies } from "../products/ProductPolicies";
import { ReservationData } from "./ReservationData";
import { ReservationDetails } from "./ReservationDetails";
import { ReservationCheckIn } from "./ReservationCheckIn";
import "../../../css/body/reservation/Reservation.css";
import { DateContext } from "../../../context/DateContext";
import axios from "axios";
import apiUrl from "../../../Config";
import { AuthContext } from "../../auth/Auth.context.jsx";
import Moment from "moment";
import Swal from 'sweetalert2'

export const Reservation = ({ product }, ...props) => {
  const { accommodationId } = useParams();
  const { state: ContextState, logout } = useContext(AuthContext);
  const { user } = ContextState;

  let navigate = useNavigate();

  const date = new Date();

  const { startDate, endDate, setStartDate, setEndDate } =
    useContext(DateContext);
  const [stateCity, setStateCity] = useState("");
  const [id, setId] = useState("");
  const [bookingStartTime, setBookingStartTime] = useState("");
  const [userId, setUserId] = useState(user.id);
  const [productId, setProductId] = useState("");
  const [flagError, setFlagError] = useState(false);

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  const addReservation = (event) => {
    event.preventDefault();

    const user = JSON.parse(localStorage.getItem("user"));
    if(user.city === ""){
      user.city = stateCity
    }
    axios
      .post(
        `${apiUrl}/private/bookings`,
        {
          bookingStartTime: bookingStartTime,
          startDate: Moment(startDate).format("YYYY-MM-DD"),
          finalDate: Moment(endDate).format("YYYY-MM-DD"),
          user: {
            id: user.id,
            city: user.city
          },
          product: {
            id: accommodationId,
          },
        },
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `${user.type} ${user.token}`,
          },
        }
      )
      .then(() => {
        setStartDate(new Date());
        setEndDate(new Date());
        setFlagError(false);
        navigate(`/productos/:accommodationId/reserva/exitosa`);
      })
      .catch((error) => {
        console.error(error);
        Swal.fire({
          confirmButtonColor: '#FBC02D',
          width: '350px',
          icon: 'error',
          title: '',
          text: 'Las fechas seleccionadas contienen fechas no disponibles',
        })
      });
  };

  return product === "" ? (
    navigate(`/error404`)
  ) : (
    <div>
      <ProductName product={product} />
      <h3 className="reservation-data-title">Completá tus datos</h3>
      <form
        className="reservation-form"
        onSubmit={(event) => addReservation(event)}
      >
        <div className="container-data-calendar-checkin">
          <ReservationData setUserId={setUserId} setStateCity={setStateCity} />
          <div className="container-reservation-calendar">
            <h3 className="reservation-calendar-title">
              Seleccioná tu fecha de reserva
            </h3>
            <CalendarProduct product={product} />
          </div>
          <ReservationCheckIn setBookingStartTime={setBookingStartTime} />
        </div>
        <div className="container-details">
          <ReservationDetails product={product} flagError={flagError} />
        </div>
      </form>
      <ProductPolicies product={product} />
    </div>
  );
};

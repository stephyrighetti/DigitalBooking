import React, { useContext } from 'react'
import "../../../css/body/products/CalendarProduct.css"
import { Calendar } from 'react-calendar'
import { useState, useMemo, useCallback, useEffect } from "react";
import useCheckMobileScreen from '../../utilidades/useCheckMobileScreen';
import { DateContext } from "../../../context/DateContext"
import Moment from 'moment';
import { logRoles } from '@testing-library/react';

export const CalendarProduct = ({product, setStartDateForm, setEndDateForm}) => {
  const {startDate, endDate, setStartDate, setEndDate } = useContext(DateContext);
  const { today } = useMemo(() => {
      const now = new Date();
      now.setHours(0, 0, 0, 0);
        return {
          today: now
        };
      }, []);
    

  const [isMobile, setIsMobile] = useState(useCheckMobileScreen());

  function tileDisabled({ date, view }) {
    if (view === 'month') {
      console.log(date.toLocaleDateString());
      console.log(product.datesNotAvailable[0].dateNotAvailable);
      return product.datesNotAvailable.some(dateDisable=> date.toLocaleDateString() === dateDisable.dateNotAvailable)
    }
  }
  
  function tileClassName({ date, view }) {
    if (view === 'month') {
      if (product.datesNotAvailable?.find(dateDisable=> date.toLocaleDateString() === dateDisable.dateNotAvailable)) {
        return 'disable-dates';
      }
    }
  }

  const onChangeCalendar = (date) => {
    setStartDate(date[0]);
    setEndDate(date[1]);

  }


  return (

    <div className="container-calendar-product">

      <Calendar
        showDoubleView={!isMobile}
        showNeighboringMonth={true}
        selectRange={true}
        minDate={today}
        value={[startDate, endDate]}
        onChange={onChangeCalendar}
        formatShortWeekday={(locale, date) => date.toLocaleDateString('es-ES', { weekday: 'short' })[0]}
        calendarType={"US"}
        formatMonthYear={(locale, date) => date.toLocaleDateString('es-ES', { month: 'long' })}
        tileDisabled={(date, view) => tileDisabled(date, view)}
        tileClassName={tileClassName}
      />
      {/* <div className='calendar-hr'></div> */}

    </div>
  )
}

import React from 'react'
import { Calendar } from 'react-calendar'
import { useState, useMemo, useCallback, useEffect } from "react";
import '../../../css/body/searchBar/Calendar.css'
import useCheckMobileScreen from '../../utilidades/useCheckMobileScreen';
import {useLocation} from "react-router-dom";

export const CalendarLocation = ({changeFlagCalendar, newChosenDate}) => {
    const { today } = useMemo(() => {
    const now = new Date();
    now.setHours(0, 0, 0, 0);
      return {
        today: now
      };
    }, []);
  
    const [[startDate, endDate], onChange] = useState([today, today]);
  
    const onChangeCalendar = useCallback((value) => {
      onChange(value);

    }, []);
  

    //cambiar calendario de desktop a mobile
    const [isMobile, setIsMobile] = useState(useCheckMobileScreen());

    const location = useLocation();

    return (
      
      <div className="container-calendar">
        
        <Calendar
        showDoubleView={!isMobile}
        showNeighboringMonth={true}
        selectRange={true}
        minDate={today}
        value={[startDate, endDate]}
        onChange={onChangeCalendar}
        formatShortWeekday={(locale, date) => date.toLocaleDateString('es-ES', {weekday:'short'})[0]}
        calendarType={"US"}
        formatMonthYear={(locale, date) => date.toLocaleDateString('es-ES', {month: 'long'})}
        
        />
        <div className='calendar-hr'></div>
        <div className='calendar-aplicar'>
          <button onClick={(event)=>{
            newChosenDate(event,startDate,endDate)
            changeFlagCalendar()
          }}
            className="calendar-buttonAplicar">Aplicar</button>
        </div>
        
      </div>
      
    );

  }

import React,{ createContext } from "react";


export const DateContext = createContext();

export const DateProvider = ({children}) => {
    const [startDate, setStartDate] = React.useState(new Date());
    const [endDate, setEndDate] = React.useState(new Date());

    return (
        <DateContext.Provider value={{ 
            startDate,
            setStartDate,
            endDate,
            setEndDate
        }}>
            {children}
        </DateContext.Provider>
    );
}
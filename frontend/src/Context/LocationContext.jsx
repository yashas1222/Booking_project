import { createContext, useState } from "react";


export const LocationContext = createContext(
    {
        location:"",
        handleLocationChange:()=>{}
    }

);

const LocationContextAPI = ({children})=>{
    const [location,setLocation] = useState("");

    function handleLocationChange(value){
        setLocation(value);
    }

    const locationContextValue = {
        location:location,
        handleLocationChange,
    }

    return(
        <LocationContext.Provider value={locationContextValue} >
            {children}
        </LocationContext.Provider>
    )
}
export default LocationContextAPI;
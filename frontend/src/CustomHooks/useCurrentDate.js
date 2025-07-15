import { useEffect,useState } from "react";

export function useCurrentDate(){
    const [currentDate,setCurrentDate] =  useState(new Date());
    const duration = (3600 * 1000 * 12);
    useEffect(()=>{
        const timer = setInterval(()=>{
            setCurrentDate(new Date());
        },duration)

        return ()=>clearInterval(timer);

    },[])
    return currentDate;
        
        
}
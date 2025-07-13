import { Outlet } from "react-router-dom"
import Navbar from "../Components/Navbar"
import Categories from "../Components/Categories"
import { Box } from "@chakra-ui/react"

import SearchContextAPI from "../Context/SearchContextAPI"





const RootPage = () => {
  
  
  return (
    
    
    <SearchContextAPI>
      <div className="relative min-h-screen">
        <Navbar/>
        <Categories/>
        <Box marginLeft="10px">
          <Outlet/>
        </Box>
      </div>
    </SearchContextAPI>
      
  )
}

export default RootPage
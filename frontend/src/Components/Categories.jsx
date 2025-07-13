import { NavLink } from "react-router-dom"
import {Flex,Box}  from "@chakra-ui/react"
const Categories = () => {
  return (
    <Flex justifyContent="center" alignItems="center" padding="3px" marginBottom="2rem" bg="#A4133C">
        <ul className='flex items-center gap-10'>
          <NavLink><p>Movies</p></NavLink>
          <NavLink><p>Events</p></NavLink>
          <NavLink><p>Plays</p></NavLink>
          <NavLink><p>Sports</p></NavLink>
          <NavLink><p>Activites</p></NavLink>
           

        </ul>
        <Box></Box>
    </Flex>
  )
}

export default Categories
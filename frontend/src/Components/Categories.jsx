import { NavLink } from "react-router-dom"
import {Flex,Box}  from "@chakra-ui/react"
const Categories = () => {
  return (
    <Flex justifyContent="center" alignItems="center" padding="3px" marginBottom="2rem" bg="#A4133C">
        <ul className='flex items-center gap-10'>
          <NavLink to={`/`}><p>All</p></NavLink>
          <NavLink to={`/movies`}><p>Movies</p></NavLink>
          <NavLink to={`/events`}><p>Events</p></NavLink>
          <NavLink to={`/plays`}><p>Plays</p></NavLink>
          <NavLink to={`/sports`}><p>Sports</p></NavLink>
          <NavLink to={`/activities`}><p>Activites</p></NavLink>
           

        </ul>
        
    </Flex>
  )
}

export default Categories
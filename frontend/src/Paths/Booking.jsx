import { Box,Text,Heading,Button,Flex , Grid , GridItem} from '@chakra-ui/react'
import { NavLink } from 'react-router-dom'
import { useParams } from 'react-router-dom'
import { movies } from '../Data/data'
import {format} from 'date-fns'
import { useCurrentDate } from '../CustomHooks/useCurrentDate'
import LocationSelection from '../Components/LocationSelection'
import { useContext } from 'react'
import { LocationContext } from '../Context/LocationContext'
import { cinemaHalls } from '../Data/cinemas'


const Booking = () => {
    const {id} = useParams();
    const data = movies.find((value)=>(
        value.id.includes(id)
        
    ))

    //date updation logic
    const date = useCurrentDate();
    let i = 0;
    const fiveDates = [];
    while(i<5){
        const newDate = new Date(date);
        newDate.setDate(date.getDate()+i);

        const value = {
            day:format(newDate,"dd"),
            dayName:format(newDate,"EEE"),
            month:format(newDate,"MMM")
        }
        fiveDates.push(value);
        i++;
    }
    const {location} = useContext(LocationContext);
    
    const locationCinemaHalls = cinemaHalls.filter((value)=>value.location === location[0])
    console.log(locationCinemaHalls)
  return (
    <Box marginTop="-110px" color="black">
        <Flex padding="1rem" marginBottom="2rem" backgroundColor="blue.200">
            <NavLink to="/"><Button border="1px solid black" borderRadius="lg" backgroundColor="pink">Home</Button></NavLink>
        </Flex>
        <Box marginLeft="1rem" marginBottom="1rem"><Heading color="black" fontSize="3xl" fontWeight="bolder">{data.title}</Heading></Box>
        <Flex padding="1rem" backgroundColor="blue.400" alignItems="center" justifyContent="space-between">
            <Flex gap="1rem" >
                {/* dates logic */}
                {fiveDates.map((value,index)=>(
                    <Flex _hover={{
                            color:"white",
                            backgroundColor:"red.400"
                        }} border="1px solid black" padding="1rem" width="80px" height="100px" justifyContent="center" alignItems="center"  borderRadius="md" flexDirection="column" key={index}>
                        <Text  fontSize="1.3rem" fontWeight="bolder">{value.dayName}</Text>
                        <Text fontWeight="bold">{value.day}</Text>
                        <Text>{value.month}</Text>
                    </Flex>
                ))}


            </Flex>

            <Flex >
                
                <LocationSelection/>

                
                
            </Flex>

        </Flex>
        <Grid color="black" marginTop="1rem" templateColumns="1fr" gap="2rem" >
            { locationCinemaHalls.length > 0 ? 
                (locationCinemaHalls.map((cinema)=>{
                    const thisMovie = cinema.shows.find((movies)=>movies.movieId === data.id)
                    if(!thisMovie){
                        return null; 
                    }
                        // i am struggling to show a single message when a particular location does not host this movie <---------------------
                    return(
                        <GridItem padding="3rem" backgroundColor="pink.200" key={cinema.id}>
                            <Flex justifyContent="space-between" alignItems="center">
                                <Box>
                                    <Heading fontSize="1.5rem" fontWeight="bolder">{cinema.name}</Heading>
                                    <Text fontWeight="bold">{cinema.location}</Text>
                                    
                                </Box>
                                <Box>
                                    <Flex gap="1rem">

                                        {thisMovie.timings.map((time,index)=>(
                                            <Text _hover={{
                                                backgroundColor:"red.300",
                                                color:"white",
                                                cursor:"pointer"
                                            }} border="1px solid black" padding="1rem" borderRadius="md"  key={index}>{time}</Text>
                                        ))}
                                    </Flex>
                                </Box>

                            </Flex> 
                        </GridItem>

                    ) 
                    
                    
                })):<Heading color="black" fontSize="4rem">Sorry</Heading>
            }
            {/* <GridItem backgroundColor="pink.200" padding="1rem"></GridItem> */}
            
        </Grid>
    </Box>
  )
}

export default Booking
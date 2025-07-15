import { Box,Text,Heading,Button,Flex} from '@chakra-ui/react'
import { NavLink } from 'react-router-dom'
import { useParams } from 'react-router-dom'
import { movies } from '../Data/data'
import {format} from 'date-fns'
import { useCurrentDate } from '../CustomHooks/useCurrentDate'
import LocationSelection from '../Components/LocationSelection'
import { useContext } from 'react'
import { LocationContext } from '../Context/LocationContext'


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
                        <Text  fontSize="1.3rem" fontWeight="bolder"  >{value.dayName}</Text>
                        <Text fontWeight="bold">{value.day}</Text>
                        <Text>{value.month}</Text>
                    </Flex>
                ))}


            </Flex>

            <Flex >
                
                <LocationSelection/>
                
                
                
            </Flex>
        </Flex>
    </Box>
  )
}

export default Booking
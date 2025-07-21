import { Box,Flex,Heading } from "@chakra-ui/react"
import { Button, Card, Image, Text } from "@chakra-ui/react"
import { HiHeart } from "react-icons/hi"
import { NavLink } from "react-router-dom"



const Cards = ({title ,id, body, img, rating}) => {
  
  return (
    
        <Box >
         <Card.Root width="320px" variant="elevated" key="elevated">
            <Card.Body gap="2">
               <Image
                src={img}
                width="350px"
                height="400px"
                alt="Movie-image"
                objectFit="cover"
              />
              <Card.Title mb="2">{title}</Card.Title>
              <Card.Description height="100px">
                {body}
              </Card.Description>
              
              <Flex alignItems="center" textStyle="2xl" fontWeight="medium" letterSpacing="tight" mt="2" gap="5px">
                <Text color="red"> <HiHeart /></Text>  
                <Text>{`${rating}/10`}</Text>
              </Flex>
              
            </Card.Body>
            <Card.Footer justifyContent="flex-end">
              

              {/* Booking functionality */}
              <NavLink to={`/explore/${id}`}><Button>Explore</Button></NavLink>
              <NavLink to={`/booking/${id}`}><Button backgroundColor="red" color="white">Book</Button></NavLink>
              {/* Booking functionality */}

            </Card.Footer>
          </Card.Root>
      </Box>
    
  )
}

export default Cards
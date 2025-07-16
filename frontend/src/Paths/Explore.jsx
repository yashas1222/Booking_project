    import React from 'react'
    import { useParams } from 'react-router-dom'
    import { Box,Text,Button,Image,Heading,Flex} from '@chakra-ui/react'
    import { NavLink } from 'react-router-dom'
    import { movies } from '../Data/data'
    import { HiHeart } from "react-icons/hi"

    const Explore = () => {
        const {id} = useParams();
        console.log(id)
        const data = movies.find((value)=>(
            value.id.includes(id)
        ))
        console.log(data.languages)
        
    return (
        <Box marginTop="-110px"  minHeight="100vh">
            <Box backgroundColor="pink" padding="1rem">
                <NavLink to="/"><Button color="black" border="2px solid black" borderRadius="md" >Home</Button></NavLink>
            </Box>
            <Box className='poster' position="relative" borderRadius="md" backgroundColor="black" width="100%" height="60vh" marginTop="2rem" >
                
                <Box position="absolute" display="flex" gap="15rem"  alignItems="center" padding="1rem" top="0" left="0" width="100%" height="100%" backgroundColor="rgba(0.5, 0.5, 0.5, 0.8)"  backdropFilter="blur(1rem)"  zIndex="1" >
                    <Box height="500px" overflow="hidden"  width="350px" backgroundColor="white">
                        <Image objectFit="contain" objectPosition="center"  width="100%" height="100%" src={`/${data.img}`} />
                    </Box>
                    <Flex flexDirection="column" gap="3rem">
                        <Heading fontWeight="bolder" fontSize="3rem">{data.title}</Heading>
                        <Flex alignItems="center" fontSize="3xl" fontWeight="medium" letterSpacing="tight" mt="2" gap="5px">
                            <Text color="red"> <HiHeart /></Text>  
                            <Text>{`${data.rating}/10`}</Text>
                        </Flex>
                        
                        <Flex alignItems="center" fontSize="3xl" fontWeight="medium" letterSpacing="tight" mt="2" gap="20px">
                            {data.languages.map((value,index)=>(
                                <Text border="1x black solid" borderRadius="xl" padding="8px" backgroundColor="black"  key={index}>{`${value}`}</Text>
                            ))}
                        </Flex>
                        <NavLink to={`/booking/${id}`}><Button fontSize="5xl" borderRadius="2xl" padding="2rem" backgroundColor="rgb(248, 68, 100)" color="white">Book Tickets</Button></NavLink>
                        

                    </Flex>


                </Box>
                
                <Box width="100%" height="100%" >
                    <Image src={`/${data.img}`} width="100%" height="100%" alt={`${data.img}`+" image"} objectFit="cover"/>
                </Box>

                
            </Box>
            
        </Box>  
    )
    }

    export default Explore
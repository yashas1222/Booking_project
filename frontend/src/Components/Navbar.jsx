
import { Button,Dialog, CloseButton, Drawer, Image, Portal ,Box, Flex} from "@chakra-ui/react"
import { Input, InputGroup,Text} from "@chakra-ui/react"
import { LuSearch } from "react-icons/lu"

import { useContext } from "react"
import { SearchContext } from "../Context/SearchContextAPI"

import SignIn from "./SignIn";
import LocationSelection from "./LocationSelection";





const Navbar = () => {
    
    const {searchInput,handleChange} = useContext(SearchContext); 
    

  return (
    <Flex position="fixed" zIndex="10" top="0px"  justifyContent="center" width="100%"  alignItems="center"  bg="black" >
        <Box padding="10px" width="100%"  >
            <div  className="flex justify-around items-center ">
                <div className="flex items-center gap-10">

                    <div>
                        <Image border="1px solid black" rounded="full" src="booking_logo.png" width="90px" height="90px" fit="cover"/>
                    {/* <div className="logo w-[100px] h-[80px] overflow-hidden rounded-full"><img className="w-full h-full object-contain" src="booking_logo.png" alt="logo_icon" /></div> */}
                    </div>

                    <div>
                        {/* Search filter */}
                        <InputGroup size="lg" width="500px" startElement={<LuSearch />} >
                            <Input onChange={handleChange} value={searchInput} placeholder="Search for Movies, Events, Activities and Plays." />
                        </InputGroup >
                        
                    </div>
                </div>

                <div className="flex items-center gap-10">

                    <div>

                        <LocationSelection/>
                        
                    </div>

                    <div>

                        <Dialog.Root size="lg" closeOnInteractOutside={false}>
                            <Dialog.Trigger asChild>
                            <Button variant="outline" size="lg">
                                Sign-In
                            </Button>
                            </Dialog.Trigger>
                            <Portal>
                            <Dialog.Backdrop />
                            <Dialog.Positioner>
                                <Dialog.Content bg="white" color="black">
                                <Dialog.Header display="flex" justifyContent="center">
                                    <Dialog.Title fontWeight="bolder" fontSize="1.5rem" >Sign-In</Dialog.Title>
                                </Dialog.Header>
                                <Dialog.Body>
                                    <SignIn/>
                                </Dialog.Body>
                                <Dialog.Footer>
                                    <Dialog.ActionTrigger asChild>
                                        <Button variant="outline"  color="black" _hover={{color:"white"}}>Cancel</Button>
                                    </Dialog.ActionTrigger>
                                    
                                </Dialog.Footer>
                                <Dialog.CloseTrigger asChild>
                                    <CloseButton size="md" color="black" _hover={{color:"white"}} />
                                </Dialog.CloseTrigger>
                                </Dialog.Content>
                            </Dialog.Positioner>
                            </Portal>
                        </Dialog.Root>

                        
                    </div>

                    <div>
                        <Drawer.Root>
                            <Drawer.Trigger asChild>
                                <Button variant="ghost" size="sm">
                                    <Text fontSize="1rem">Side-bar</Text>
                                    
                                </Button>
                            </Drawer.Trigger>
                            <Portal>
                                <Drawer.Backdrop />
                                <Drawer.Positioner>
                                <Drawer.Content>
                                    <Drawer.Header>
                                    <Drawer.Title></Drawer.Title>
                                    </Drawer.Header>
                                    <Drawer.Body>
                                        <ul className="flex flex-col gap-5 justify-center">
                                            <li><p>Notifications</p></li>
                                            <li><p>Your Orders</p></li>
                                            <li><p>Contact Us</p></li>
                                            <li><p>Help & Support</p></li>
                                            <li><p>Booking Canellation</p></li>
                                        </ul>
                                    </Drawer.Body>
                                    <Drawer.Footer>
                                        <Drawer.ActionTrigger asChild>
                                            <Button variant="outline">Cancel</Button>
                                        </Drawer.ActionTrigger>
                                        
                                    </Drawer.Footer>
                                    <Drawer.CloseTrigger asChild>
                                    <CloseButton size="md" />
                                    </Drawer.CloseTrigger>
                                </Drawer.Content>
                                </Drawer.Positioner>
                            </Portal>
                        </Drawer.Root>
                        {/* <div  onClick={()=>setShowSideBar((value)=>(!value))} className="sidebar cursor-pointer border-1">sidebar</div> */}

                    </div>
                </div>

            </div>
        </Box>
    </Flex>
  )
}

export default Navbar
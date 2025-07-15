import { NavLink } from 'react-router-dom'
import { Box, Text,Heading,Button, Container, Flex,Input,Stack,Field} from '@chakra-ui/react'
import { useForm } from 'react-hook-form'


const LogIn = () => {
    const {register,handleSubmit,watch,formState:{errors}} = useForm();
    const password = watch("password");
    function onSubmitFun(data){
        
        console.log(data);
    }
  return (
    
    <Box className='login' margin="-110px"  fontSize="lg"  display="flex" justifyContent="center" alignItems="center" width="100vw" height="100vh"  color="white">
        <Box
            position="fixed"
            top={0}
            left={0}
            width="100vw"
            height="100vh"
            backgroundColor="rgba(0, 0, 0, 0.5)" 
            backdropFilter="blur(1rem)" 
            zIndex="1"
        />
            
        <Box paddingLeft="24" className='box'  position="absolute" top="40" left="50"  zIndex="1000" width="30%" height="50%">
            <Heading   fontSize="2rem" paddingBottom="3rem" fontWeight="bolder">Create An Account</Heading>
            <form  onSubmit={handleSubmit(onSubmitFun)}>
              <Stack gap="4" align="flex-start" maxW="sm">
                <Field.Root invalid={!!errors.name}>
                  <Field.Label>Name</Field.Label>
                  <Input {...register("name",{
                    required:"Invalid name",
                    validate:(value)=>{
                      if(value.trim()===""){
                        return "Enter a valid name"
                      }
                      else{
                        return true;
                      }
                    }
                  })}/>
                <Field.ErrorText>{errors.name?.message}</Field.ErrorText>
                </Field.Root>

                <Field.Root  invalid={!!errors.email}>
                  <Field.Label >Email</Field.Label>
                  <Input {...register("email",{
                    required:"Invalid Email ID",
                    validate:(value)=>{
                        if(!value.includes("@")){
                            return "Should contain '@'";
                        }
                        return true;
                    },
                    
                  })} />
                  <Field.ErrorText>{errors.email?.message}</Field.ErrorText>
                </Field.Root>
        
                <Field.Root invalid={!!errors.password}>
                  <Field.Label >Password</Field.Label>
                  <Input {...register("password",{
                    required:"Password is Incorrect"
                  })} type='password'/>
                  <Field.ErrorText>{errors.password?.message}</Field.ErrorText>
                </Field.Root>

                <Field.Root  invalid={!!errors.confirm_password}>
                  <Field.Label >Confirm Password</Field.Label>
                  <Input  {...register("confirm_password",{
                    required:"Password does not match.",
                    validate:(value)=>{
                        if(password !== value){
                            return "Password does not match";
                        }
                        return true;
                    }
                  })} type='password'/>
                  <Field.ErrorText >{errors.confirm_password?.message}</Field.ErrorText>
                </Field.Root>
                
                <Flex gap="1rem">
                    <Button variant="subtle" _hover={{
                        color:"black",
                        backgroundColor:"white"
                    }} type="submit">Submit</Button>
                    <Button variant="subtle" _hover={{
                        color:"black",
                        backgroundColor:"white"
                    }} ><NavLink to="/">Cancel</NavLink></Button>
                </Flex>
                
                  
                
                
              </Stack>
            </form>
        </Box>
        
        
    </Box>
    
  )
}

export default LogIn
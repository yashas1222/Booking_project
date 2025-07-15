import { Box, Text,Button, Container, Flex,Input,Stack,Field} from '@chakra-ui/react'
import { useForm } from 'react-hook-form'
import { NavLink } from 'react-router-dom';


const SignIn = () => {
    const {register,handleSubmit,formState:{errors}} = useForm();
    function onSubmitFun(data){
        console.log(data);
    }
  return (
    <form onSubmit={handleSubmit(onSubmitFun)}>
      <Stack gap="4" align="flex-start" maxW="sm">
        <Field.Root invalid={!!errors.email}>
          <Field.Label>Email</Field.Label>
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
          <Field.Label>Password</Field.Label>
          <Input {...register("password",{
            required:"Password is Incorrect"
          })} type='password'/>
          <Field.ErrorText>{errors.password?.message}</Field.ErrorText>
        </Field.Root>
        
        <Flex>
            <Button _hover={{
                color:"white",
                backgroundColor:"black"
            }} type="submit">Submit</Button>
            <Button _hover={{
                color:"white",
                backgroundColor:"black" 
            }} ><NavLink to="LogIn">No Account?</NavLink></Button>
        </Flex>
        
      </Stack>
    </form>
  )
}

export default SignIn 
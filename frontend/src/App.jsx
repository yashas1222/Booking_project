import { createBrowserRouter, RouterProvider  } from "react-router-dom"
import RootPage from "./Paths/RootPage";
import HomePage from "./Paths/HomePage";
import LogIn from "./Paths/LogIn";

const App = () => {
  const router = createBrowserRouter([
    {
      path:'/',
      element:<RootPage/>,  
      children:[
        {
          index:true,
          element:<HomePage/>,
        },
        
      ]
      
    },
    {
      path:"LogIn",
      element:<LogIn/>,
    },
  ]);
  return (
    <RouterProvider router={router}/>
  )
}

export default App
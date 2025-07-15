import { createBrowserRouter, RouterProvider  } from "react-router-dom"
import RootPage from "./Paths/RootPage";
import HomePage from "./Paths/HomePage";
import LogIn from "./Paths/LogIn";
import Explore from "./Paths/Explore";
import CategorySpecific from "./Paths/CategorySpecific";
import Booking from "./Paths/Booking";
import LocationContextAPI from "./Context/LocationContext";

const App = () => {
  const router = createBrowserRouter([
    {
      path:'/',
      element:<RootPage/>,  
      children:[
        {
          path:"/",
          element:<HomePage/>,
          
        },
        {
          path:"/:category",
          element:<CategorySpecific/>
        }
        
        
      ]
      
    },
    {
      path:"/LogIn",
      element:<LogIn/>,
    },
    {
      path:"/explore/:id",
      element:<Explore/>
    },
    {
      path:"/booking/:id",
      element:<Booking/>
    }
    
  ]);
  return (
    <LocationContextAPI>

        <RouterProvider router={router}/>
    </LocationContextAPI>
  )
}

export default App
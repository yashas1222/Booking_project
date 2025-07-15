import { createContext, useState } from "react"


export const SearchContext = createContext({
  searchInput:"",
  handleChange:()=>{},
});

const SearchContextAPI = ({children}) => {

    const [searchInput, setSeachInput] = useState("");
    function handleChange(e){
      
      setSeachInput(e.target.value);
      
    }
   

    const searchContextValue = {
      searchInput,
      handleChange,
    }

  return (
    <SearchContext.Provider value={searchContextValue}>
      {children}
    </SearchContext.Provider>
  )
}

export default SearchContextAPI
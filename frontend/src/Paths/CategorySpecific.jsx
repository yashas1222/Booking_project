import { Flex,Button } from "@chakra-ui/react";
import Cards from "../Components/Cards";
import { movies } from "../Data/data";
import { useContext, useEffect, useState,useRef } from "react";
import InfiniteScroll from "react-infinite-scroll-component";
import { SearchContext } from "../Context/SearchContextAPI";
import { useParams } from "react-router-dom";


const CategorySpecific = () => {
  const { searchInput } = useContext(SearchContext);
  const {category} = useParams();
  const categorySpecific = movies.filter((value=>value.tags.includes(category)));
  const filteredData = useRef(categorySpecific);
  const [newData, setNewData] = useState(categorySpecific.slice(0, 5));
  const [count, setCount] = useState(5);
  const [hasMore, setHasMore] = useState(true);
  function handleScrollTop(){
    window.scrollTo({
      top:"0",
      behavior:"smooth",
    })
  }

  useEffect(() => {
      const inputFilter = searchInput.trim().toLowerCase().split(/\s+/);
      
      const filtered = categorySpecific.filter((value) =>(
        inputFilter.every((tag)=>(

          value.tags.some((movietags)=>movietags.includes(tag)) ||
          value.title.toLowerCase().includes(tag)
        ))
      )
      );
    

    filteredData.current = filtered;
    setNewData(filtered.slice(0, 5));
    setCount(5);
    setHasMore(filtered.length > 5);
  }, [searchInput,category])

  function fetchData() {
    if (newData.length < filteredData.current.length) {
      setTimeout(() => {
        const nextCount = count + 5;
        setNewData(filteredData.current.slice(0, nextCount));
        setCount(nextCount);
      }, 1000);
    } else {
      setHasMore(false);
    }
  }

  return (
    <Flex flexDirection="column" px="100px" alignItems="center" minHeight="100vh">
      <InfiniteScroll
        dataLength={newData.length}
        next={fetchData}
        hasMore={hasMore}
        loader={<h4 style={{ color: "black" , textAlign:"center"}}>Loading...</h4>}
        endMessage={
          <p style={{ textAlign: 'center', color: "black", fontSize: "2rem" }}>
            <b>END</b>
          </p>
        }
      >
        <Flex gap="20px" flexWrap="wrap" justifyContent="center">
          {newData.map((value,index) => (
            <Cards key={index} id={value.id} title={value.title} body={value.body} img={value.img} rating={value.rating} />
          ))}
        </Flex>
      </InfiniteScroll>
      <Button boxShadow="0 0px 20px blue" _hover={{
        backgroundColor:"black",
        color:"white"
      }} onClick={handleScrollTop} position="fixed" bottom="2" right="2" color="black" border="1px solid black" borderRadius="md" fontSize="2rem">Go Top</Button>
    </Flex>
  );
};


export default CategorySpecific
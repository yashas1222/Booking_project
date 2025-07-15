import { Flex } from "@chakra-ui/react";
import Cards from "../Components/Cards";
import { movies } from "../Data/data";
import { useContext, useEffect, useState,useRef } from "react";
import InfiniteScroll from "react-infinite-scroll-component";
import { SearchContext } from "../Context/SearchContextAPI";

const HomePage = () => {
  const { searchInput } = useContext(SearchContext);
  
  const filteredData = useRef(movies);
  const [newData, setNewData] = useState(movies.slice(0, 4));
  const [count, setCount] = useState(4);
  const [hasMore, setHasMore] = useState(true);

  useEffect(() => {
    const inputFilters = searchInput.trim().toLowerCase().split(/\s+/);
    
    const filtered = movies.filter((value) =>(
      inputFilters.every((tag)=>(

        value.tags.some((movietag)=>movietag.includes(tag))  ||
        value.title.toLowerCase().includes(tag)
      ))
    )
    );
    

    filteredData.current = filtered;
    setNewData(filtered.slice(0, 4));
    setCount(4);
    setHasMore(filtered.length > 4);
  }, [searchInput]);

  function fetchData() {
    if (newData.length < filteredData.current.length) {
      setTimeout(() => {
        const nextCount = count + 4;
        setNewData(filteredData.current.slice(0, nextCount));
        setCount(nextCount);
      }, 500);
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
        loader={<h4 style={{ color: "black" }}>Loading...</h4>}
        endMessage={
          <p style={{ textAlign: 'center', color: "black", fontSize: "2rem" }}>
            <b>Yay! You have seen it all</b>
          </p>
        }
      >
        <Flex gap="20px" flexWrap="wrap" justifyContent="center">
          {newData.map((value,index) => (
            <Cards key={index} id={value.id} title={value.title} body={value.body} img={value.img} rating={value.rating} />
          ))}
        </Flex>
      </InfiniteScroll>
    </Flex>
  );
};

export default HomePage;

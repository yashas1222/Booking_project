import React from 'react'
import { LocationContext } from "../Context/LocationContext";
import {  Select, createListCollection, Portal } from "@chakra-ui/react"
import { useContext } from 'react';

const LocationSelection = () => {
    const {location,handleLocationChange} = useContext(LocationContext)
        
        const frameworks = createListCollection({
            items: [
                { label: "Bangalore", value: "Bangalore" },
                { label: "Delhi", value: "Delhi" },
                { label: "Chennai", value: "Chennai" },
                { label: "Mumbai", value: "Mumbai" },
                { label: "Goa", value: "Goa" }
                
            ],
        })
        
  return (
    <Select.Root
        collection={frameworks}
        width="200px"
        value={location}
        onValueChange={(e)=> handleLocationChange(e.value)}
    >
        {/* here e is nothing but { label: "Mumbai", value: "Mumbai" } this, not the event object like you get in normal elemnt's onChange(e) */}
        <Select.HiddenSelect />
        <Select.Control>
            <Select.Trigger>
            <Select.ValueText placeholder="Location" />
            </Select.Trigger>
            <Select.IndicatorGroup>
            <Select.Indicator />
            </Select.IndicatorGroup>    
        </Select.Control>
        <Portal>
            <Select.Positioner>
            <Select.Content>
                {frameworks.items.map((framework) => (
                <Select.Item item={framework} key={framework.value}>
                    {framework.label}
                    <Select.ItemIndicator />
                </Select.Item>
                ))}
            </Select.Content>
            </Select.Positioner>
        </Portal>
    </Select.Root>
  )
}

export default LocationSelection
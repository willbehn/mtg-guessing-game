import axios from "axios"
import {useEffect, useState } from 'react';
import debounce from 'lodash.debounce';
import "./Searchbar.css"

const Searchbar = ({query, setQuery}) =>{
    const [suggestions, setSuggestions] = useState([]);
    const [loading, setLoading] = useState(false);


    const fetchCardNames = debounce(async() => {
        if (query.length < 3){
            setSuggestions([])
            return
        }

        setLoading(true)


        await axios.get(`https://api.scryfall.com/cards/search?q=name:*${query}*`, 
           { headers: {
                'User-Agent': 'MTGGuessingApp/0.1',
                'Accept': 'application/json;q=0.9,*/*;q=0.8'
            }}
        )
            .then(response => {
                setSuggestions(response.data.data.map(item => item.name))

            })
            .catch(error =>{
                console.log(error)
            })
            .finally(() => {
                setLoading(false)
            });
    }, 1000)

    useEffect(() => {
        fetchCardNames();

        return () => {
            fetchCardNames.cancel();
        }

    }, [query])

    const handleSelectedCard = (cardName) => {
        setQuery(cardName)
        setSuggestions([])
    }


    const handleInputChange = (e) => {
        const value = e.target.value;
        setQuery(value);
    };

    return (
        <div className="search-container">
            <input
                className="inputarea"
                type="text"
                placeholder="Search for Magic Cards..."
                value={query}
                onChange={handleInputChange}
            />

    
            {suggestions.length > 0 && (
                <ul className="dropdown">

                    {loading && (
                        <li className="dropdown-item">
                        Loading...
                        </li>
                    )}

                    {!loading && suggestions.map((cardName, index) => (
                        <li 
                            key={index} 
                            onClick={() => handleSelectedCard(cardName)}
                            className="dropdown-item"
                        >
                            {cardName}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );

}


export default Searchbar
import { useEffect, useState } from 'react';
import axios from 'axios';
import Hint from './Hint';

const HintList = () => {
    const [hintData, setHintData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [currentHint, setCurrentHint] = useState(0);

    useEffect(() => {
        fetchCardHints();
    }, []);

    const fetchCardHints = async() => {
        await axios.get('http://localhost:8080/api/test')
            .then(response => {
                setHintData(response.data);
                setLoading(false);
            })
            .catch(error => {
                console.error("Error fetching card image:", error);
                setLoading(false);
            });
    };

    const nextHint = () => {
        setCurrentHint((prev) =>
            prev+1 
        )
    }

    return (
        <div>
            <h1>Magic: The gathering - guess the card</h1>
            {loading ? (
                <p>Loading...</p>
            ) : (
                <div>  
                    {currentHint < hintData.hints.length ? (
                        <>
                            <div>
                                <Hint hintData={hintData.hints[currentHint]} index={currentHint} />
                            </div>
                            <div>
                                <textarea placeholder='Type you guess here...'></textarea>
                                <button> Submit </button>
                            </div>
                            <button onClick={nextHint}>Next hint</button>
                        </>
                    ) : (
                        <>
                            <h2>The card was {hintData.cardName} </h2>
                            <img src={hintData.imageUri}></img>
                        </>
                    )}      
                   
                    
 
                </div>
                
            )}
        </div>
    );
}

export default HintList;
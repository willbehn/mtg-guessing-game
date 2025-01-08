import { useEffect, useState } from 'react';
import axios from 'axios';
import Hint from './Hint';

const HintList = () => {
    const [hintData, setHintData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [currentHint, setCurrentHint] = useState(0);
    const [guess, setGuess] = useState("");
    const [correctGuess, setCorrectGuess] = useState(false);

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

    const handleSumbit = () => {
        if (guess === hintData.cardName){
            setCorrectGuess(true);
            setCurrentHint(hintData.hints.length)
        } else {nextHint()}
    }


    return (
        <div style={styles.gameContainer}>
            <h1>Magic: The gathering - guess the card</h1>
            {loading ? (
                <p>Loading...</p>
            ) : (
                <div> 
                    {console.log(hintData.cardName)}
                    {currentHint < hintData.hints.length ? (
                        <>
                            <button onClick={nextHint}>Next hint</button>
                            <div>
                                <Hint hintData={hintData.hints[currentHint]} symbolData={hintData.currentSymbols} index={currentHint} />
                            </div>
                            <div>
                                <input style={styles.inputarea} value={guess} onChange={e => setGuess(e.target.value)} placeholder='Type you guess here...'></input>
                                <button onClick={handleSumbit}> Submit </button>
                            </div>
                        </>
                    ) : (
                        <> 
                            {correctGuess ? (
                                <h3>🎉 You guessed it right!</h3>
                            ) : (
                                <h3>❌ Better luck next time!</h3>
                            )}

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

const styles = {
    gameContainer: {
        height: 800,
        width: 600
    },
    inputarea: {
        width: '80%',
        padding: '10px',
        borderRadius: '8px',
        border: 'none',
        margin: '10px',
        fontSize: '20px'
    }}

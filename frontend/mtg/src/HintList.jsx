import { useEffect, useState } from 'react';
import axios from 'axios';
import Hint from './Hint';
import ProgressBar from './ProgressBar';

const HintList = () => {
    const [hintData, setHintData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [currentHint, setCurrentHint] = useState(1);
    const [totalProgress, setTotalProgress] = useState(0)
    const [guess, setGuess] = useState("");
    const [correctGuess, setCorrectGuess] = useState(false);

    useEffect(() => {
        fetchCardHints();
    }, []);

    const fetchCardHints = async() => {
        await axios.get('http://localhost:8080/api/test')
            .then(response => {
                setHintData(response.data);
                console.log(response.data)
                setLoading(false);
            })
            .catch(error => {
                console.error("Error fetching card image:", error);
                setLoading(false);
            });
    };

    useEffect(() => {
        setCurrentHint(totalProgress)
    }, [totalProgress]
    )

    const nextHint = () => {
        setTotalProgress((prev) =>
            prev+1 
        )
    }

    const handleProgressClick = (newIndex) => {
        setCurrentHint(newIndex)
    }

    const handleSumbit = () => {
        if (guess.toLowerCase() === hintData.cardName.toLowerCase()){
            setCorrectGuess(true);
            setTotalProgress(hintData.hints.length)
        } else {nextHint()}
    }


    return (
        <div>
            <h1>Magic: The gathering - guess the card</h1>
            {loading ? (
                <p>Loading...</p>
            ) : (
                <div style={styles.gameContainer}> 
                    {console.log(hintData.cardName)}
                    {totalProgress < hintData.hints.length ? (
                        <>
                            <div>
                                <Hint hintData={hintData.hints[currentHint]} symbolData={hintData.currentSymbols} index={currentHint+1} />
                            </div>
                            <div style={styles.inputContainer}>
                                <button onClick={nextHint}>Next hint</button>
                                <ProgressBar totalProgress={totalProgress} onProgressClick={handleProgressClick}></ProgressBar>
                            </div>
                            <input style={styles.inputarea} value={guess} onChange={e => setGuess(e.target.value)} placeholder='Type you guess here...'></input>
                            <button onClick={handleSumbit}> Submit </button>
                        </>
                    ) : (
                        <> 
                            {correctGuess ? (
                                <h3>🎉 You guessed it right!</h3>
                            ) : (
                                <h3>❌ Better luck next time!</h3>
                            )}

                            <h2>The card was {hintData.cardName} </h2>
                            <a href={hintData.edhrecUrl} rel="noreferrer">
                                EDHREC
                            </a>
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
        display: 'flex',
        flexDirection: 'column', 
        justifyContent: 'center', 
        alignItems: 'center', 
        height: '72vh', 
        width: '100%',   
    },
    inputarea: {
        width: '80%',
        padding: '10px',
        borderRadius: '8px',
        border: 'none',
        margin: '10px',
        fontSize: '20px'
    }, 
    inputContainer : {
        width: '80%'
    }
}

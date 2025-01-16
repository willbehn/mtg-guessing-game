import { useEffect, useState } from 'react';
import axios from 'axios';
import Hint from './Hint';
import ProgressBar from './ProgressBar';
import "./GuessTheMtgCard.css"
import Searchbar from './Searchbar';

const GuessTheMtgCard = () => {
    const [hintData, setHintData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [currentHint, setCurrentHint] = useState(1);
    const [totalProgress, setTotalProgress] = useState(0)
    const [query, setQuery] = useState("");
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
        if (query.toLowerCase() === hintData.cardName.toLowerCase()){
            setCorrectGuess(true);
            setTotalProgress(hintData.hints.length)
        } else {nextHint()}
    }


    return (
        <div>
            <h1>Mana Guess - The MTG card guessing game</h1>
            {loading ? (
                <p>Loading...</p>
            ) : (
                <div className='gameContainer'> 
                    {console.log(hintData.cardName)}
                    {totalProgress < hintData.hints.length ? (
                        <>
                            <div>
                                <Hint hintData={hintData.hints[currentHint]} symbolData={hintData.currentSymbols} index={currentHint+1} />
                            </div>
                            <div className='inputContainer'>
                                <ProgressBar currentHint={currentHint} totalProgress={totalProgress} onProgressClick={handleProgressClick}></ProgressBar>
                                <Searchbar query={query} setQuery={setQuery}></Searchbar>
                                
                            </div>

                            <div className='buttonContainer'>
                                <button className='actionButton' onClick={handleSumbit}> Submit </button>
                                <button className='actionButton' onClick={nextHint}>Next hint</button>
                            </div>


                        </>
                    ) : (
                        <div className='solutionContainer'> 
                            {correctGuess ? (
                                <h3>🎉 You guessed it right! 🎉 </h3>
                            ) : (
                                <h3>❌ Better luck next time! ❌</h3>
                            )}

                            <div>
                                <h2>
                                    The card was: <span style={{ color: "purple" }}>{`${hintData.cardName}`}</span>
                                </h2>

                                <img style={{width:360, height: 'auto'}} src={hintData.imageUri}></img>

                                <a href={hintData.edhrecUrl} rel="noreferrer">
                                    <h3>EDHREC</h3>
                                </a>

                            </div>


                        </div>
                    )}      
                </div>
            )}

            <p style={{color: 'gray'}}>Card Data provided by Scryfall</p>
        </div>
    );
}

export default GuessTheMtgCard;



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
    const [showDialog, setShowDialog] = useState(true)

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
            {showDialog && (<div className="dialog-overlay">
                    <dialog className="dialog-box" open>
                        <h3>Welcome to Gather & Guess, the Magic: The Gathering card-guessing game!</h3>
                        <ul>
                            <li>The goal of the game is to guess the Magic: The Gathering card using as few hints as possible.</li>
                            <li>Hints are based on various details about the card, such as mana cost, keywords, artwork, and more.</li>
                            <li>The current selection of cards consists of the top 70 creatures eligible as commander cards.</li>
                            <li>This is an early test version of Gather & Guess, so some features may not work as intended (e.g., on mobile).</li>
                            <li>
                                Check out the GitHub repository: <a href="https://github.com/willbehn/mtg">GitHub</a>
                            </li>

                        </ul>
                        <form method="dialog">
                            <button className='confirmButton' onClick={() => setShowDialog(false)}>Got it!</button>
                        </form>
                    </dialog>
                </div>)}


            {loading ? (
                <p>Loading...</p>
            ) : (
                <div className='gameContainer'> 
                    <header>
                        <h1>Gather & Guess - Test</h1>
                    </header>

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
                                <button className='actionButton' onClick={()=> setShowDialog(true)}>Help</button>
                            </div>
                        </>
                    ) : (
                        <div className='solutionContainer'> 
                            {correctGuess ? (
                                <h2>🎉 You guessed it right! 🎉 </h2>
                            ) : (
                                <h2>❌ Better luck next time! ❌</h2>
                            )}

                            <>
                                <h3>
                                    The card was: <span style={{ color: "purple" }}>{`${hintData.cardName}`}</span>
                                </h3>

                                <img style={{width:360, height: 'auto'}} src={hintData.imageUri}></img>

                                <div className='shareContainer'>
                                    <a href={hintData.edhrecUrl} rel="noreferrer">
                                        <h3>EDHREC</h3>
                                    </a>
                                    <button className='shareButton'>Share your result</button>
                                </div>

                            </>
                        </div>
                    )}      
                </div>
            )}

            <footer>
                <p style={{color: 'gray'}}>Card Data provided by Scryfall</p>
            </footer>
        </div>
    );
}

export default GuessTheMtgCard;



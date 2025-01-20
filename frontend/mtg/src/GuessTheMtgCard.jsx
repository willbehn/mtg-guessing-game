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

    const resetCard = () => {
        setLoading(true)
        setCurrentHint(1)
        setTotalProgress(0)
        setQuery("")
        setCorrectGuess(false)
        fetchCardHints();
    }

    return (
        <div>
            {showDialog && (<div className="dialog-overlay">
                    <dialog className="dialog-box" open>
                        <h3>Welcome to Gather & Guess, the Magic: The Gathering card-guessing game!</h3>
                        <ul>
                            <li>The goal of the game is to guess the Magic: The Gathering card using as few hints as possible.</li>
                            <li>Hints are based on various details about the card, such as mana cost, keywords, artwork, and more.</li>
                            <li>The current selection of cards consists of the top 100 played cards on EDHREC</li>
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
                    <div className='headerContainer'>

                        <h1>Gather & Guess - Test</h1>

                        <div className="navigation">
                            <a>Previous</a>
                            <a>Next</a>

                        </div>
                        
                    </div>

                    {console.log(hintData.cardName)}
                    {totalProgress < hintData.hints.length ? (
                        <div>
                            <div>
                                <Hint hintData={hintData.hints[currentHint]} index={currentHint+1} />
                            </div>
                            <div className='inputContainer'>
                                <ProgressBar currentHint={currentHint} totalProgress={totalProgress} onProgressClick={handleProgressClick}></ProgressBar>
                                <Searchbar query={query} setQuery={setQuery}></Searchbar>

                                <div className={'buttonContainer'}>
                                
                                    <button className='actionButton' onClick={handleSumbit}> Submit </button>
                                    <button className='actionButton' onClick={nextHint}>Next hint</button>
                                    <button className='actionButton' onClick={()=> setShowDialog(true)}>Help</button>
                                    
                                </div>

                            </div>

                            
                        </div>
                    ) : (
                        <div className='solutionContainer'> 
                            {correctGuess ? (
                                <h4>🎉 You guessed it right! 🎉 </h4>
                            ) : (
                                <h4>❌ Better luck next time! ❌</h4>
                            )}

                                <h4>
                                    The card was: <span style={{ color: "#D4AF37" }}>{`${hintData.cardName}`}</span>
                                </h4>

                                <div className='imageCard'>
                                    <img className='responsiveImageCard' src={hintData.imageUri}></img>
                                </div>


                                <div className='shareContainer'>
                                    <a href={hintData.edhrecUrl} rel="noreferrer">
                                        <h3>EDHREC</h3>
                                    </a>
                                    <button className='shareButton'>Share your result</button>
                                    <button onClick={(()=> resetCard())} className='shareButton'>Play again</button>
                                </div>

                        </div>
                        
                    )}      
                </div>
            )}

            <footer style={{ padding: 4, margin: 0, gap: 0, display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                <p style={{ color: 'black', margin: 0 }}>Card Data provided by Scryfall</p>
                <p style={{ color: 'black', margin: 0 }}>Background image artist: Rob Alexander</p>
            </footer>
        </div>
    );
}

export default GuessTheMtgCard;



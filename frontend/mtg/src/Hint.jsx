import "./Hint.css"
import allSymbols from './assets/symbols'

const symbols = allSymbols.currentSymbols;

const displaySymbol = (symbolString) => {
    const symbolList = symbolString.match(/\{.*?\}/g);
    return (
        <div style={{flexDirection: 'row'}}>  
            {symbolList.map((element, index) => (
                <img 
                    key={index} 
                    src={findSymbol(element,symbols)?.svg_uri}
                    style={{width : 100, height : 100, padding: 4}}>
                </img>
            ))}
        </div>
        )
}


const findSymbol = (sm, symbols) =>{
    return symbols.data.find((element) => {
        return element.symbol === sm;
    })
}

const Hint = ({hintData, symbolData, index}) => {
    return (
        <div className="hintContainer">
            {hintData.type === "ARTWORK" ? ((() => {
                //temp løsning
                const [artworkUri, artistName] = hintData.data.split("&");
                return (
                    <> 
                        <h3>Hint {index} - Artwork</h3>
                        <div className="imageContainer">
                            <img className="responsiveImage" src={artworkUri} alt="Artwork" />
                            <p style={{color: 'gray'}}>Artist: {artistName}</p>
                        </div>
                    </>
                );
            })()
        ) : hintData.type === "CARD_TYPE" ? (  
                    <>
                        <h3>Hint {index} -  Card type</h3> 
                        <p style={{fontSize: 30}}>{hintData.data}</p>
                    </>

                ) : hintData.type === "SET_EXPANSION" ? (
                    <>
                        <h3>Hint {index} - last printed in set </h3>
                        <img src={hintData.data} style={{width: 200, height: 200}}></img>
                    </>
                ) :  hintData.type === "MANA_COST" ? (
                    <>
                        <h3>Hint {index} - Mana cost</h3>
                        {displaySymbol(hintData.data, symbolData)}
                    </>
        
                ) : hintData.type === "ORACLE_TEXT" ? (
                    <>
                        <h3>Hint {index} - Oracle text</h3>    
                        <p style={{fontSize: 20}}>{hintData.data}</p>
                    </>

                ) : hintData.type === "POWER_THOUGHNESS" ? (  
                    <>
                        <h3>Hint {index} - Statline</h3> 
                        {displaySymbol(hintData.data, symbolData)}
                    </>

                ) : hintData.type === "RARITY" ? (  
                    <>
                        <h3>Hint {index} - Rarity</h3> 
                        <p style={{fontSize: 30}}>{hintData.data}</p>
                    </>

                ): hintData.type === "RELEASED_AT" ? (  
                    <>
                        <h3>Hint {index} - Released at</h3> 
                        <p style={{fontSize: 30}}>{hintData.data}</p>
                    </>

                ):hintData.type === "KEYWORDS" ? (  
                    <>
                        <h3>Hint {index} - Keywords</h3> 
                        <p style={{fontSize: 30}}>{hintData.data}</p>
                    </>

                ):null}
        </div>

    );
}

export default Hint;

//  <p>Hint Response: {JSON.stringify(hintData)}</p> 
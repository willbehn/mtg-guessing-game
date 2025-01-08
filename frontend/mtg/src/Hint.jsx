const displaySymbol = (symbolString, symbols) => {
    const symbolList = symbolString.match(/\{.*?\}/g);
    return (
        <>  
            {symbolList.map((element, index) => (
                <img 
                    key={index} 
                    src={findSymbol(element,symbols)?.svg_uri}
                    style={{width : 60, height : 60}}>
                </img>
            ))}
        </>
        )
}


const findSymbol = (sm, symbols) =>{
    return symbols.data.find((element) => {
        return element.symbol === sm;
    })
}

const Hint = ({hintData, symbolData, index}) => {
    return (
        <>
            <h2>Hint {index}: </h2>
                {hintData.type === "ARTWORK" ? (
                    <img src={hintData.data}></img>

                ) : hintData.type === "CARD_TYPE" ? (   
                    <h3>The type of the card is: {hintData.data}</h3>

                ) : hintData.type === "SET_EXPANSION" ? (
                    <>
                        <h3>The set the card was last printed in: </h3>
                        <img src={hintData.data} style={{width: 200, height: 200}}></img>
                    </>
                ) :  hintData.type === "MANA_COST" ? (
                    <>
                        <h3>The mana cost of the card:</h3>
                        {displaySymbol(hintData.data, symbolData)}
                    </>
        
                ) : hintData.type === "ORACLE_TEXT" ? (   
                    <h3>Oracle text: "{hintData.data}"</h3>

                ) : hintData.type === "POWER_THOUGHNESS" ? (  
                    <>
                        <h3>The statline of the card: </h3>
                        {displaySymbol(hintData.data, symbolData)}
                    </>

                ) : null}
        </>

    );
}

export default Hint;

//  <p>Hint Response: {JSON.stringify(hintData)}</p> 
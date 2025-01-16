const displaySymbol = (symbolString, symbols) => {
    const symbolList = symbolString.match(/\{.*?\}/g);
    return (
        <>  
            {symbolList.map((element, index) => (
                <img 
                    key={index} 
                    src={findSymbol(element,symbols)?.svg_uri}
                    style={{width : 100, height : 100, padding: 4}}>
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
        <div style={{height:'50vh', width:'50vw', padding:10, alignItems: 'center'}}>
                {hintData.type === "ARTWORK" ? (
                    <>
                        <h3>Hint {index} - Artwork</h3>
                        <img style={{width:460, height: 'auto'}} src={hintData.data}></img>
                    </>
                ) : hintData.type === "CARD_TYPE" ? (  
                    <>
                        <h3>Hint {index} -  Card type</h3> 
                        <p style={{fontSize: 20}}>{hintData.data}</p>
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
                        <h3>Hint {index} -  Oracle text</h3>    
                        <p style={{fontSize: 20}}>{hintData.data}</p>
                    </>

                ) : hintData.type === "POWER_THOUGHNESS" ? (  
                    <>
                        <h3>Hint {index} -  Statline</h3> 
                        {displaySymbol(hintData.data, symbolData)}
                    </>

                ) : null}
        </div>

    );
}

export default Hint;

//  <p>Hint Response: {JSON.stringify(hintData)}</p> 
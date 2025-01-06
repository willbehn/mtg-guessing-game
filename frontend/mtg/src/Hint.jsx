const Hint = ({hintData, index}) => {

    return (
        <>
            <h2>Hint {index+1}: </h2>
                {hintData.type === "ARTWORK" ? (
                    <img style={{ filter: 'blur(5px)' }} src={hintData.data}></img>

                ) : hintData.type === "CARD_TYPE" ? (   
                    <h3>The type of the card is: {hintData.data}</h3>

                ) : hintData.type === "SET_EXPANSION" ? (
                    <div>
                        <h3>The set the card was last printed in: </h3>
                        <img src={hintData.data} style={{width: 300, height: 300}}></img>
                    </div>
                ) : hintData.type === "CARD_TYPE" ? (   
                    <h3>The type of the card is: {hintData.data}</h3>

                ) : hintData.type === "MANA_COST" ? (   
                    <h3>The mana cost of the card: {hintData.data}</h3>

                ) : hintData.type === "ORACLE_TEXT" ? (   
                    <h3>Oracle text: "{hintData.data}"</h3>

                ) : hintData.type === "POWER_THOUGHNESS" ? (   
                    <h3>Power and thoughness: "{hintData.data}"</h3>

                ) : null}
        </>

    );
}

export default Hint;

//  <p>Hint Response: {JSON.stringify(hintData)}</p> 
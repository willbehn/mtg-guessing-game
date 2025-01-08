const ProgressBar = ({currentHint, totalProgress, onProgressClick}) => {
    return (
        <div>
            {Array.from({length: totalProgress}).map((_,index) =>
                (   
                    <button 
                        onClick={() => onProgressClick(index)}
                        key={index} 
                        style={{width : 30, height : 30}}> {index+1}  
                        
                    </button>
                )
            )}

        </div>
    )
}

export default ProgressBar;
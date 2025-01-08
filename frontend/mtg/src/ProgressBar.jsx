import './ProgressBar.css';

const ProgressBar = ({currentHint, totalProgress, onProgressClick}) => {
    return (
        <div className="progressContainer">
            {Array.from({length: totalProgress+1}).map((_,index) =>
                (   
                    <button 
                        onClick={() => onProgressClick(index)}
                        key={index} 
                        className={"progressButton"}
                        > 
                        {index+1}  
                        
                    </button>
                )
            )}

        </div>
    )
}

export default ProgressBar;
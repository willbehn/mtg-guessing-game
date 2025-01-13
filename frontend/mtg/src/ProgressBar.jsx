import './ProgressBar.css';

const ProgressBar = ({ currentHint, totalProgress, onProgressClick }) => {
    return (
        <div className="progressContainer">
            {Array.from({ length: totalProgress + 1 }).map((_, index) => (
    
                currentHint === index ? (
                    <button
                        onClick={() => 
                            onProgressClick(index)}
                        key={index}
                        className="currentProgressButton"
                    >
                        {index + 1}
                    </button>
                ) : (
                    <button
                        onClick={() => onProgressClick(index)}
                        key={index}
                        className="progressButton"
                    >
                        {index + 1}
                    </button>
                )
            ))}
        </div>
    );
};

export default ProgressBar;

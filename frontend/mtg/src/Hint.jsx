import { useEffect, useState } from 'react';
import axios from 'axios';

const Hint = () => {
    const [hintData, setHintData] = useState(null);
    const [loading, setLoading] = useState(true);


    const fetchCardHints = async() => {
        await axios.get('http://localhost:8080/api/test')
            .then(response => {
                setHintData(response.data);
                setLoading(false);
            })
            .catch(error => {
                console.error("Error fetching card image:", error);
                setLoading(false);
            });
    };

    useEffect(() => {
        fetchCardHints();
    }, []);


    return (
        <div>
            <h1>Hints:</h1>
            {loading ? (
                <p>Loading...</p>
            ) : (
                <div>
                    <p>API Response: {JSON.stringify(hintData)}</p> 
                    <img src={hintData.imageUri}></img>
                </div>
            )}
        </div>
    );
}

export default Hint;
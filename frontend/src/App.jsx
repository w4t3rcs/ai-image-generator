import {useEffect, useState} from "react";
import axios from "axios";

const App = () => {
    const [image, setImage] = useState({id: 0, imageBase64: ''})
    useEffect(() => {
        async function fetchData() {
            const response = await axios.post("http://localhost:8080/api/v1.0/images",
                {
                    id: 1,
                    prompt: "I need an image of a dog",
                    imageStyle: "ANIME"
                });
            const data = await response.data;
            if (data !== null && data.length > 0) setImage(data);
        }

        fetchData();
    });
    return (
        <img src={`data:image/jpeg;base64,${image.imageBase64}`} alt="nothing's here ehehe"></img>
    )
}

export default App
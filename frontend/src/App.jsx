import {useEffect, useState} from "react";
import axios from "axios";

const App = () => {
    const [image, setImage] = useState({id: 0, base64Json: null, url: null});
    useEffect(() => {
        async function fetchData() {
            const response = await axios.post("http://localhost:8080/api/v1.0/images",
                {
                    id: 1,
                    prompt: "I need an image of a dog",
                });
            const data = await response.data;
            console.log(data);
            setImage(data);
            console.log(image);
        }

        fetchData();
    });
    return (
        <img src={`data:image/jpeg;base64,${image.base64Json}`} alt="nothing's here ehehe"></img>
    )
}

export default App
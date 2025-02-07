import {useState} from "react";
import axios from "axios";

export const PromptInput = ({messagesHandler, messages}) => {
    const [prompt, setPrompt] = useState("")

    const handleEvent = (event) => {
        if (event.key === 'Enter' && prompt !== '') {
            const userRequest = {text: prompt, image: null, from: "user"};
            messagesHandler((prev) => [...prev, userRequest])
            const fetchData = async () => {
                const lastIndex = messages.length - 1;
                const image = messages[lastIndex]?.image;
                try {
                    const response = await axios.post("http://localhost:8080/api/v1.0/images", {prompt: prompt});
                    const data = await response.data;
                    const aiResponse = {text: null, image: data, from: "bot"};
                    if (image !== null && image?.id === -1) {
                        messagesHandler((prev) => [...prev, aiResponse]);
                    } else {
                        messagesHandler((prev) => [...prev, aiResponse]);
                    }
                } catch (err) {
                    if (image === null || image?.id !== -1) {
                        const aiResponse = {text: null, image: {id: -1, base64Json: null, url: null}, from: "bot"};
                        messagesHandler((prev) => [...prev, aiResponse]);
                    }
                    console.log(err);
                    await fetchData();
                }
            };

            fetchData();
            setPrompt("");
            event.target.value = "";
        }
    }

    return (
        <input onKeyPress={handleEvent} onChange={event => {setPrompt(event.target.value)}} type="text" placeholder="Type here" className="input input-bordered w-full max-w-xs" />
    )
}
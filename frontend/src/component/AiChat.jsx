import {ChatMessage} from "./ChatMessage.jsx";
import {PromptInput} from "./PromptInput.jsx";
import {useState} from "react";

export const AiChat = () => {
    const [messages, setMessages] = useState([]);
    console.log(messages);
    return (
        <>
            <ul>
                {messages.map((message, index) => (
                    <li key={index}>
                        <ChatMessage message={message} icon={message.from === "bot" ? "https://cdn-icons-png.flaticon.com/512/8131/8131880.png" : "https://icons.veryicon.com/png/o/miscellaneous/administration/account-25.png"} />
                    </li>
                ))}
            </ul>
            <br/>
            <PromptInput messages={messages} messagesHandler={setMessages} />
        </>
    )
}
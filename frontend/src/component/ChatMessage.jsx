import {AiImage} from "./AiImage.jsx";

export const ChatMessage = ({icon, message}) => {
    return (
        <div className="chat chat-start">
            <div className="chat-image avatar">
                <div className="w-10 rounded-full">
                    <img alt="icon :D" src={icon} />
                </div>
            </div>
            {message.from === "user" && <div className="chat-bubble">{message.text}</div>}
            {message.from === "bot" && <div className="chat-bubble"><AiImage image={message.image} /></div>}
        </div>
    )
}
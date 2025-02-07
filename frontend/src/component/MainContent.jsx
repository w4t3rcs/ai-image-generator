import {AiChat} from "./AiChat.jsx";
import {ThemeController} from "./ThemeController.jsx";

export const MainContent = () => {
    return (
        <main>
            <div className="sticky mockup-browser bg-base-300 ">
                <div className="mockup-browser-toolbar">
                    <div className="input">AI IMAGE GENERATOR</div>
                    <ThemeController />
                </div>
                <div className="hero bg-base-200 min-h-screen">
                    <div className="text-center">
                        <div className="max-w-md">
                            <AiChat />
                        </div>
                    </div>
                </div>
            </div>
        </main>
    )
}
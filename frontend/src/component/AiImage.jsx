export const AiImage = ({image}) => {
    console.log(image);
    return (
        <div>
            {image.id !== -1 ? <img src={`data:image/jpeg;base64,${image.base64Json}`} alt="nothing's here ehehe" /> : <div className="skeleton h-32 w-32" /> }
        </div>
    )
}
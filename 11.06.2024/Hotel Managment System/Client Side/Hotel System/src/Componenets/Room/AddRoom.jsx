import { useState } from 'react';
import { addRoom } from '../utils/ApiFunctions';
import RoomTypeSelector from '../Common/RoomTypeSelector';

const AddRoom = () => {
    const [newRoom, setNewRoom] = useState({
        photo: null,
        roomType: "",
        roomPrice: ""
    });

    const [imagePreview, setImagePreview] = useState("");
    const [successMessage, setSuccessMessage] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    const handleRoomInputChange = (e) => {
        const name = e.target.name
        let value=e.target.value

        if (name === "roomPrice") {
            if(!isNaN(value)){
                value.parseInt(value)
        } else {
            value=""
        }
    }
    setNewRoom({...newRoom,[name]: value})
}

    const handleImageChange = (e) => {
        const selectedImage = e.target.files[0];
        if (selectedImage && selectedImage.type.startsWith("image/")) {
            setNewRoom({ ...newRoom, photo: selectedImage });
            setImagePreview(URL.createObjectURL(selectedImage));
        } else {
            setErrorMessage("Please upload a valid image file.");
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setSuccessMessage(""); // Clear previous messages
        setErrorMessage("");

        if (!newRoom.roomType || !newRoom.roomPrice || !newRoom.photo) {
            setErrorMessage("All fields are required.");
            return;
        }

        try {
            const success = await addRoom(newRoom.photo, newRoom.roomType, newRoom.roomPrice);
            if (success) {
                setSuccessMessage("A new room has been added to the database.");
                setNewRoom({ photo: null, roomType: "", roomPrice: "" });
                setImagePreview("");
            } else {
                setErrorMessage("Error adding room. Please try again.");
            }
        } catch (error) {
            setErrorMessage("Error: " + error.message);
        }

        // Clear success or error messages after 3 seconds
        setTimeout(() => {
            setSuccessMessage("");
            setErrorMessage("");
        }, 3000);
    };

    return (
        <section className="Container mt-5 mb-5">
            <div className="row justify-content-center">
                <div className="col-md-8 col-lg-6">
                    <h2 className="mt-5 mb-2">Add a new Room</h2>
                    <form onSubmit={handleSubmit}>
                        <div className="mb-3">
                            <label htmlFor="roomType" className="form-label">Room Type</label>
                            <RoomTypeSelector handleRoomInputChange={handleRoomInputChange} newRoom={newRoom} />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="roomPrice" className="form-label">Room Price</label>
                            <input  
                                className="form-control"
                                required
                                id="roomPrice"
                                type="number"
                                name="roomPrice"
                                value={newRoom.roomPrice}
                                onChange={handleRoomInputChange}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="photo" className="form-label">Room Photo</label>
                            <input
                                id="photo"
                                name="photo"
                                type="file"
                                className="form-control"
                                accept="image/*" // Only allow images
                                onChange={handleImageChange}
                            />
                            {imagePreview && (
                                <img
                                    src={imagePreview}
                                    alt="Preview room photo"
                                    style={{ maxWidth: "400px", maxHeight: "400px" }}
                                    className="mb-3"
                                />
                            )}
                        </div>
                        <div className="d-grid d-md-flex mt-2">
                            <button className="btn btn-outline-primary ml-5">
                                Save Room
                            </button>
                        </div>
                    </form>
                    {successMessage && <div className="alert alert-success mt-3">{successMessage}</div>}
                    {errorMessage && <div className="alert alert-danger mt-3">{errorMessage}</div>}
                </div>
            </div>
        </section>
    );
};

export default AddRoom;

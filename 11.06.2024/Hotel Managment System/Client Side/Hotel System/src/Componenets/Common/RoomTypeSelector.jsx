import { useEffect, useState } from 'react';
import PropTypes from 'prop-types'; 
import { getRoomTypes } from '../utils/ApiFunctions';

const RoomTypeSelector = ({ handleRoomInputChange, newRoom }) => {
    const [roomTypes, setRoomTypes] = useState([]);
    const [showNewRoomTypeInput, setShowNewRoomTypeInput] = useState(false);
    const [newRoomType, setNewRoomType] = useState("");
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchRoomTypes = async () => {
            try {
                const data = await getRoomTypes();
                setRoomTypes(data);
            } catch (error) {
                setError("Error fetching room types.");
                console.error("Error fetching room types:", error);
            } finally {
                setLoading(false);
            }
        };
        fetchRoomTypes();
    }, []);

    const handleNewRoomInputChange = (e) => {
        setNewRoomType(e.target.value);
    };

    const handleAddNewRoomType = () => {
        const trimmedRoomType = newRoomType.trim().toLowerCase();
        const isDuplicate = roomTypes.some(
            (type) => type.trim().toLowerCase() === trimmedRoomType
        );

        if (trimmedRoomType && !isDuplicate) {
            setRoomTypes([...roomTypes, newRoomType]);
            setNewRoomType("");
            setShowNewRoomTypeInput(false);
        } else if (isDuplicate) {
            alert("This room type already exists.");
        }
    };

    if (loading) {
        return <p>Loading room types...</p>;
    }

    if (error) {
        return <p>{error}</p>;
    }

    return (
        <>
            {roomTypes.length > 0 && (
                <div>
                    <select
                        id="form-select"
                        name="roomType"
                        value={newRoom.roomType}
                        onChange={(e) => {
                            if (e.target.value === "Add new") {
                                setShowNewRoomTypeInput(true);
                            } else {
                                handleRoomInputChange(e);
                            }
                        }}
                    >
                        <option value="">Select a room type</option>
                        <option value="Add new">Add New</option>
                        {roomTypes.map((type, index) => (
                            <option key={index} value={type}>
                                {type}
                            </option>
                        ))}
                    </select>

                    {showNewRoomTypeInput && (
                        <div className="input-group mt-2">
                            <input
                                className="form-control"
                                type="text"
                                placeholder="Enter a new room type"
                                value={newRoomType}
                                onChange={handleNewRoomInputChange}
                                aria-label="New room type input"
                            />
                            <button
                                className="btn btn-hotel"
                                type="button"
                                onClick={handleAddNewRoomType}
                            >
                                Add
                            </button>
                        </div>
                    )}
                </div>
            )}
        </>
    );
};

// Define PropTypes for the component
RoomTypeSelector.propTypes = {
    handleRoomInputChange: PropTypes.func.isRequired,
    newRoom: PropTypes.shape({
        roomType: PropTypes.string,
    }).isRequired,
};

export default RoomTypeSelector;

import React, { useEffect, useState } from "react";
import "./criminal.css";
import { FaToggleOn, FaToggleOff } from "react-icons/fa";
import AddCriminalModal from "./AddCriminal";

const Criminal = () => {
  const [criminals, setCriminals] = useState(() => {
    // Retrieve criminals from local storage or initialize with an empty array
    const savedCriminals = localStorage.getItem("criminals");
    return savedCriminals ? JSON.parse(savedCriminals) : [];
  });
  const [showAddCriminalModal, setShowAddCriminalModal] = useState(false);

  const handleClick = (id) => {
    setCriminals((prevCriminals) =>
      prevCriminals.map((criminal) =>
        criminal.id === id
          ? { ...criminal, isWanted: !criminal.isWanted }
          : criminal
      )
    );
  };

  const handleAddCriminalClick = () => {
    setShowAddCriminalModal(true);
  };

  const handleCloseAddCriminalModal = () => {
    setShowAddCriminalModal(false);
  };

  const handleAddCriminal = (newCriminal) => {
    const newCriminalWithId = { id: criminals.length + 1, ...newCriminal };
    const updatedCriminals = [...criminals, newCriminalWithId];
    setCriminals(updatedCriminals);
    localStorage.setItem("criminals", JSON.stringify(updatedCriminals)); // Save to local storage
    setShowAddCriminalModal(false);
  };

  const handleClearCriminals = () => {
    localStorage.removeItem("criminals"); // Remove criminals from local storage
    setCriminals([]); // Clear the state
  };

  useEffect(() => {
    // Retrieve criminals from local storage on component mount
    const savedCriminals = localStorage.getItem("criminals");
    if (savedCriminals) {
      setCriminals(JSON.parse(savedCriminals));
    }
  }, []);

  // Function to divide data into chunks of 3
  const chunkData = (arr, chunkSize) => {
    const result = [];
    for (let i = 0; i < arr.length; i += chunkSize) {
      result.push(arr.slice(i, i + chunkSize));
    }
    return result;
  };

  const rows = chunkData(criminals, 3);
  return (
    <div className="criminal-container">
      <div className="criminal-header">
        <h1>Criminal Records</h1>
      </div>
      <div className="criminal-content">
        <div className="criminals">
          {rows.map((row, rowIndex) => (
            <div key={rowIndex} className="criminal-row">
              {row.map((criminal) => (
                <div key={criminal.id} className="criminal-list">
                  <div className="criminal-list-content">
                    <div className="criminal-image">Image</div>
                    <ul>
                      <li type="none" className="criminal-list-item">
                        <h3>Name: {criminal.name}</h3>
                        <h3>Gender: {criminal.gender}</h3>
                        <h3>Age: {criminal.age}</h3>
                        <h3>Crime: {criminal.crime}</h3>
                        <div className="toggle-content">
                          <p> wanted: </p>

                          <div
                            className="icon"
                            onClick={() => handleClick(criminal.id)}
                          >
                            {criminal.isWanted ? (
                              <FaToggleOn className="toggle-icon" />
                            ) : (
                              <FaToggleOff className="toggle-icon" />
                            )}
                          </div>
                        </div>
                      </li>
                    </ul>
                  </div>
                </div>
              ))}
            </div>
          ))}
        </div>
      </div>
      <button onClick={handleAddCriminalClick} className="add-criminal">
        Add criminal
      </button>
     
      {showAddCriminalModal && (
        <AddCriminalModal
          show={showAddCriminalModal}
          onClose={handleCloseAddCriminalModal}
          onAddCriminal={handleAddCriminal}
        />
      )}
    </div>
  );
};

export default Criminal;
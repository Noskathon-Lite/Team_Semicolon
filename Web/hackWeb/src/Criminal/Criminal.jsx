import React, { useEffect, useState } from "react";
import "./criminal.css";

import { FaToggleOn } from "react-icons/fa";
import { FaToggleOff } from "react-icons/fa";
import AddCriminalModal from "./AddCriminal";

const Criminal = () => {
  const [criminals, setCriminals] = useState([]);
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
    setCriminals((prevCriminals) => [
      ...prevCriminals,
      { id: prevCriminals.length + 1, ...newCriminal },
    ]);
    setShowAddCriminalModal(false);
  };

 

  useEffect(() => {
    // Simulated data fetching
    const fetchData = async () => {
      const data = [
        {
          id: 1,
          name: "John Doe",
          address: "123 Main St",
          age: 30,
          crime: "Theft",
          isWanted: "null",
        },
        {
          id: 2,
          name: "Jane Smith",
          address: "456 Maple Ave",
          age: 25,
          crime: "Fraud",
          isWanted: "false",
        },
        {
          id: 3,
          name: "Mark Johnson",
          address: "789 Oak Blvd",
          age: 40,
          crime: "Burglary",
          isWanted: "false",
        },
        {
          id: 4,
          name: "Lucy Brown",
          address: "321 Pine St",
          age: 35,
          crime: "Arson",
          isWanted: "false",
        },
      ];
      setCriminals(data);
    };
    fetchData();
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
                        <h3>Gender: {criminal.address}</h3>
                        <h3>Age: {criminal.age}</h3>
                        <h3>Crime: {criminal.crime}</h3>
                        <div className="toggle-content">
                          <p> wanted: </p>

                          <div
                            className="icon"
                            onClick={() => handleClick(criminal.id)}
                          >
                            {criminal.isWanted ? (
                              <FaToggleOn  className="toggle-icon"/>
                            ) : (
                              <FaToggleOff className="toggle-icon"/>
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
      <button onClick={handleAddCriminalClick} className="add-criminal">Add criminal</button>
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

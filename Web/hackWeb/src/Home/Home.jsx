import React, { useEffect, useState } from "react";
import "./Home.css";
import Modal from "react-modal";
import { Link } from "react-router-dom";
import axios from "axios";
import AlertModal from "../Component/AlertModal";
import { FaTrashRestoreAlt } from "react-icons/fa";
import AddOfficerModal from "./AddOfficerModal";

const Home = () => {
  const [showAddOfficerModal, setShowAddOfficerModal] = useState(false);
  const [showModal, setShowModal] = useState(false);
  const [selectedAlert, setSelectedAlert] = useState(null);
const[loading, setLoading] = useState(false);
  const [alertList, setAlertList] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [officers, setOfficers] = useState([]);

  

  // const [feedbacks, setFeedbacks] = useState([
  //   { id: "1", name: "name one", number: " 9876477428" },
  //   { id: "2", name: "name two", number: " 9876477428" },
  //   { id: "3", name: "name three", number: " 9876477428" },
  // ]);

  

 

  const handleTrash = (id) => {
    setOfficers((prevFeedbacks) =>
      prevFeedbacks.filter((feedback) => feedback.id !== id)
    );
  };

  const handleAlertClick = (alert) => {
    setSelectedAlert(alert);
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
    setSelectedAlert(null);
  };
  const handleAddOfficerClick = () => {
    setShowAddOfficerModal(true);
  };

  const handleCloseAddOfficerModal = () => {
    setShowAddOfficerModal(false);
  };

  const handleAddOfficer = (newOfficer) => {
    const updatedOfficers = [
      ...officers,
      { id: officers.length + 1, ...newOfficer },
    ];
    setOfficers(updatedOfficers);
    localStorage.setItem("officers", JSON.stringify(updatedOfficers)); // Save to local storage
    setShowAddOfficerModal(false);
  };

  useEffect(() => {
    // Retrieve officers from local storage on component mount
    const savedOfficers = localStorage.getItem("officers");
    if (savedOfficers) {
      setOfficers(JSON.parse(savedOfficers));
    }
  }, []);


  // const handleAddOfficer = (newOfficer) => {
  //   setOfficers((prevOfficers) => [
  //     ...prevOfficers,
  //     { id: prevOfficers.length + 1, ...newOfficer },
  //   ]);
  //   setShowAddOfficerModal(false);
  // };

  const token =
    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzY4MjgzNDYxLCJpYXQiOjE3MzY3NDc0NjEsImp0aSI6ImVkNWJhMGNjN2I0NTRmZjQ5MDAxYWYyMjVjYTVkMGQyIiwidXNlcl9pZCI6M30.9ufWjo6vmET7eGB7j_cuz0TsQbTaxeMWoTWZcNC6py0";

    useEffect(() => {
      const fetchData = async () => {
        try {
          const response = await fetch("http://192.168.23.44:8000/api/list/feedback/", {
            method: "GET", // or "POST" or other HTTP methods as needed
            headers: {
              "Authorization": `Bearer ${token}`, // Include the Authorization header
              "Content-Type": "application/json", // Optional, based on your API
            },
          });
  
          if (!response.ok) {
            throw new Error(`Error: ${response.statusText}`);
          }
  
          const result = await response.json();
          setAlertList(result.data.videos);
          console.log(result)
          console.log(result.data.videos)
        } catch (err) {
          setError(err.message);
        } finally {
          setLoading(false);
        }
      };
  
      fetchData();
    }, [token]);
  

  return (
    <>
      <div className="home">
        <div className="home-container">
          {/* alert and nepal police section logo  */}
          <div className="home-content-alert">
            {/* <div className="home-logo">
              <img src="assets/img/nepal-police.png" alt="logo" />
            </div> */}
            <div className="home-alert">
              <div className="alert-content">
                <h1>Recent Alert List</h1>
                <hr />
                <ol>
                {alertList && alertList.length > 0 ? (
                  alertList.map((alert, index) => (
                    <li key={index} type="number" className="alert-list">
                      {alert.user_name}
                      <button>
                        <div onClick={() => handleAlertClick(alert)}>
                          {alert.title}
                        </div>
                      </button>
                    </li>
                  ))
                ) : (
                  <p>No alerts available.</p>
                )}
                </ol>
              </div>
            </div>
            <AlertModal
              show={showModal}
              onClose={handleCloseModal}
              alert={selectedAlert}
            />
          </div>

          {/* feedback section and criminal record section */}

          <div className="home-content-feedback">
            <div className="home-feedback">
              {/* feedback section */}
              <div className="feedback-content">
                <h1>On Duty Officer</h1>
                <hr />
                <table>
                 
                  <tbody>
                    {officers.map((officer, index) => (
                      <tr key={index} className="feedback-list">
                        <td>{index+1} {" "} </td>
                        <td>{officer.name}</td>
                        <td>{officer.number}</td>
                        <td>
                          <FaTrashRestoreAlt
                            className="trash-icon"
                            onClick={() => handleTrash(officer.id)}
                          />
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
                <button onClick={handleAddOfficerClick} className="add-officer">Add Officer</button>
              </div>
            </div>
          </div>
        </div>
      </div>
      {showAddOfficerModal && (
        <AddOfficerModal
          show={showAddOfficerModal}
          onClose={handleCloseAddOfficerModal}
          onAddOfficer={handleAddOfficer}
        />
      )}
    </>
  );
};

export default Home;

const feedbacks = [
  { name: "name one", number: " 9876477428" },
  { name: "name two", number: " 9876477428" },
  { name: "name three", number: " 9876477428" },
];

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
  

  // const [feedbacks, setFeedbacks] = useState([
  //   { id: "1", name: "name one", number: " 9876477428" },
  //   { id: "2", name: "name two", number: " 9876477428" },
  //   { id: "3", name: "name three", number: " 9876477428" },
  // ]);

  

 

  const handleTrash = (id) => {
    setFeedbacks((prevFeedbacks) =>
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

  const handleAddOfficer = (officer) => {
    setFeedbacks((prevFeedbacks) => [
      ...prevFeedbacks,
      { id: prevFeedbacks.length + 1, ...officer },
    ]);
  };
  

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
                  {alertlist.map((alert, index) => (
                    <li key={index} type="number" className="alert-list">
                      <button>
                        <div onClick={() => handleAlertClick(alert)}>
                          {alert.alerts}
                        </div>
                      </button>
                    </li>
                  ))}
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
                    {feedbacks.map((feedback, index) => (
                      <tr key={index} className="feedback-list">
                        <td>{index+1} {" "} </td>
                        <td>{feedback.name}</td>
                        <td>{feedback.number}</td>
                        <td>
                          <FaTrashRestoreAlt
                            className="trash-icon"
                            onClick={() => handleTrash(feedback.id)}
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
      <AddOfficerModal
        show={showAddOfficerModal}
        onClose={handleCloseAddOfficerModal}
        onAddOfficer={handleAddOfficer}
      />
    </>
  );
};

export default Home;

const feedbacks = [
  { name: "name one", number: " 9876477428" },
  { name: "name two", number: " 9876477428" },
  { name: "name three", number: " 9876477428" },
];

const alertlist = [
  { id: 1, alerts: "Alert 1", details: "Details of Alert 1" },
  { id: 2, alerts: "Alert 2", details: "Details of Alert 2" },
  // Add more alerts as needed
];

import React, { useState } from "react";
import "./Home.css";
import Modal from "react-modal";
import { Link } from "react-router-dom";
import AlertModal from "../Component/AlertModal";

const Home = () => {
  const [showModal, setShowModal] = useState(false);
  const [selectedAlert, setSelectedAlert] = useState(null);

  const handleAlertClick = (alert) => {
    setSelectedAlert(alert);
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
    setSelectedAlert(null);
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
                <ol>
                  {/* render feedback  from backend */}
                  {feedbacks.map((feedback, index) => (
                    <li key={index} type="number" className="feedback-list">
                      <div className="officer-detail">
                        <span type="none"> {feedback.name} </span>
                        <span type="none">{feedback.number} </span>
                        <span type="none"><FaTrashRestoreAlt  className="trash-icon"/></span>
                      </div>
                     
                    </li>
                  ))}

                 
                </ol>
              </div>
            </div>
          </div>
        </div>
      </div>
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

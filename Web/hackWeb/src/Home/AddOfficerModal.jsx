import React, { useState } from "react";
import ReactModal from "react-modal";
import Modal from "react-modal";
import { IoClose } from "react-icons/io5";
import "./addOfficer.css";

ReactModal.setAppElement("#root");

const AddOfficerModal = ({ show, onClose, onAddOfficer }) => {
  const [name, setName] = useState("");
  const [number, setNumber] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    onAddOfficer({ name, number });
    onClose();
  };

  return (
    <Modal
      isOpen={show}
      onRequestClose={onClose}
      contentLabel="Add Officer"
      className="modal-content-addofficer"
      overlayClassName="modal-overlay-addofficer"
    >
      <div className="officer-content">
        <div className="officer-header">
        <h1>Add Officer</h1>
      <button className="officer-modal-close" onClick={onClose}><IoClose  className="close-icon"/></button>
      
        </div>
     <hr className="add-horizontal" />
      <form onSubmit={handleSubmit} className="officer-form">
        <label className="officer-label">
          <p>Officer Name:</p> 
          <input
          className="officer-input"
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />
        </label>
        <label className="officer-label">
         <p>Officer Number: </p> 
          <input
          className="officer-input"
            type="text"
            value={number}
            onChange={(e) => setNumber(e.target.value)}
            required
          />
        </label>
        <button type="submit" className="add-btn">Add</button>
      </form>
      
      </div>
    </Modal>
  );
};

export default AddOfficerModal;

import React, { useState } from "react";
import ReactModal from "react-modal";
import Modal from "react-modal";
import { IoClose } from "react-icons/io5";
import "./addCriminal.css";

ReactModal.setAppElement("#root");

const AddCriminalModal = ({ show, onClose, onAddCriminal }) => {
  const [name, setName] = useState("");
  const [gender, setGender] = useState("");
  const [age, setAge] = useState("");
  const [isWanted, setIsWanted] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();
    onAddCriminal({ name, gender, age, isWanted });
    onClose();
  };

  return (
    <Modal
      isOpen={show}
      onRequestClose={onClose}
      contentLabel="Add Officer"
      className="modal-content-addCriminal"
      overlayClassName="modal-overlay-addCriminal"
    >
      <div className="officer-content">
        <div className="officer-header">
        <h1>Add Criminal</h1>
      <button className="officer-modal-close" onClick={onClose}><IoClose  className="close-icon"/></button>
      
        </div>
     <hr className="add-horizontal" />
      <form onSubmit={handleSubmit} className="officer-form">
        <label className="officer-label">
          <p> Name:</p> 
          <input
          className="officer-input"
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />
        </label>
        <label className="officer-label">
            <p>Gender:</p>
            <div>
              <label>
                <input
                  type="radio"
                  name="gender"
                  value="Male"
                  checked={gender === "Male"}
                  onChange={(e) => setGender(e.target.value)}
                  
                />
                Male
              </label>
              <label>
                <input
                  type="radio"
                  name="gender"
                  value="Female"
                  checked={gender === "Female"}
                  onChange={(e) => setGender(e.target.value)}
                  
                />
                Female
              </label>
            </div>
          </label>
        <label className="officer-label">
         <p>Age </p> 
          <input
          className="officer-input"
            type="number"
            value={age}
            onChange={(e) => setNumber(e.target.value)}
            required
          />
        </label>
        <label className="officer-label">
         <p>Case </p> 
          <input
          className="officer-input"
            type="text"
            value={crime}
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

export default AddCriminalModal;

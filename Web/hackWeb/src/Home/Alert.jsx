import React from "react";
import ReactModal from "react-modal";
import "./Alert.css";

ReactModal.setAppElement("#root");

const Alert = ({ show, onClose, alert }) => {
  return (
    <ReactModal
      isOpen={show}
      onRequestClose={onClose}
      contentLabel="Alert Detail"
      className="modal-content"
      overlayClassName="modal-overlay"
    >
      <h1>Alert Detail</h1>
      {alert && (
        <>
          <p>{alert.details}</p>
          {/* Display other alert details as needed */}
        </>
      )}
      <button onClick={onClose}>Close</button>
    </ReactModal>
  );
};

export default Alert;
//SignUp.js
import { React, useEffect, useState } from "react";
import Modal from "react-modal";
import "./signup.css";
import { Link } from "react-router-dom";
// import axios from 'axios';

Modal.setAppElement("#root");
const SignUp = ({ show, onClose }) => {
  const [input, setInput] = useState({
    userName: "",
    phoneNumber: "",
    email: "",
    password: "",
    confirmPassword: "",
  });

  const [isFormValid, setIsFormValid] = useState(false);

  const [errors, setErrors] = useState({});

  //   const handleShow = () => {
  //     if (isFormValid) {
  //       setModalOpen(true);
  //     }
  //   };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setInput((prevInput) => ({
      ...prevInput,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (isFormValid) {
      onClose();
    }
    //  validation
    let formErrors = {};
    if (input.userName.trim()) {
      formErrors.userName = "User name is required";
    }
    if (input.phoneNumber.trim()) {
      formErrors.phoneNumber = "Phone Number  is required";
    } else if (
      !/(?:\(?\+977\)?)?[9][6-9]\d{8}|01[-]?[0-9]{7}/.test(input.phoneNumber)
    ) {
      formErrors.phoneNumber = "Phone number is invalid";
    }
    if (input.email.trim()) {
      formErrors.email = "Email is required";
    } else if (!/\S+@\S+\.\S+/.test(input.email)) {
      formErrors.email = "Email is invalid";
    }
   

    if (Object.keys(formErrors).length === 0) {
      // Form is valid, proceed with submission
      setIsFormValid(true);
      console.log(input);
      setSubmit(true);
    } else {
      // Form has errors, update state to display error messages
      setIsFormValid(false);
      setErrors(formErrors);
      setSubmit(false);
    }

    // sending data to server
    // axios
    //   .post("https://localhost:5000/signup/signup", {
    //     userName: input.userName,
    //     phoneNumber: input.phoneNumber,
    //     email: input.email,
    //     password: input.password,
    //     confirmPassword: input.confirmPassword,
    //   })
    //   .then((response) => {
    //     console.log(response);
    //     setPosts(response.data);
    //     setSubmit(true);
    //   })
    //   .catch((error) => {
    //     console.log(error);
    //   });

    // setInput({
    //   userName: "",
    //   phoneNumber: "",
    //   email: "",
    //   password: "",
    //   confirmPassword: "",
    // });

    setInput("");
  };

  return (
    <>
      <Modal
        isOpen={show}
        onRequestClose={onClose}
        contentLabel="Sign Up"
        className="modal-content-signup"
        overlayClassName="modal-overlay-signup"
      >
        <div className="signup-header">
          <h1>Sign Up</h1>
          <button onClick={onClose}>Close</button>
        </div>
        <hr />
        <form onSubmit={handleSubmit} className="signup-form">
          <label>
             Name:
            <input
              type="text"
              name="userName"
              value={input.userName}
              onChange={handleChange}
              required
            />
          </label>
          <label>
            Phone Number:
            <input
              type="text"
              name="phoneNumber"
              value={input.phoneNumber}
              onChange={handleChange}
              required
            />
          </label>
          <label>
            Email:
            <input
              type="email"
              name="email"
              value={input.email}
              onChange={handleChange}
              required
            />
          </label>
          
         <Link to="/">
          <button type="submit">Create Account</button>
          </Link>
        </form>
      </Modal>
    </>
  );
};

export default SignUp;

import React, { useEffect, useState } from "react";
import "./layout.css";
import axios from "axios";
// import { IoNotifications } from "react-icons/io5";
import Home from "../Home/Home";
import Criminal from "../Criminal/Criminal";
import { FaRegHandshake } from "react-icons/fa";
// import Notification from "../Notification/Notification";
// import Criminal from "../Criminal/Criminal";
// import { CgProfile } from "react-icons/cg";

const Layout = () => {
  const [toggleState, setToggleState] = useState(1);

  const [feedbacks, setFeedbacks] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

 
  const token =
    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzY4Mjc4NTM3LCJpYXQiOjE3MzY3NDI1MzcsImp0aSI6ImFiMjFjYWMyZDQwMzQxODA5NWYyOTIzZmNiMGY0ZDk3IiwidXNlcl9pZCI6Mn0.vVOJ348exaOW3Fzn6y7JvIoVzeYpRue9M_2flEMjzOs";

  useEffect(() => {
   

    const fetchFeedbacks = async () => {
      setLoading(true);
      setError(null);
      try {
        const response = await axios.get(
          "http://192.168.23.44:8000/api/list/feedback/",
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
        setFeedbacks(response.data);
        console.log(response.data);
      } catch (err) {
        setError(err.message || "Failed to fetch data");
      } finally {
        setLoading(false);
      }
    };

    fetchFeedbacks();
  }, [token]);

  

  const toggleTab = (index) => {
    setToggleState(index);

  };

  

  return (
    <>
      {/* Header Starts Here */}
      <header id="header" className="header ">
        <div className="container  ">
          <div className="logo-sn ">
            <div className="nepal-police">
              <div className="img-container">
                <img
                  src="assets/img/nepal-police.png"
                  className="sn-logo-nepal-police"
                  alt="surakshit"
                />
              </div>
            </div>
            <div className="handshake-icon">
              {" "}
              <FaRegHandshake className="handshake" />
            </div>

            <div className="suraksit-nepal">
              <img
                src="assets/img/snlogo.png"
                className="sn-logo"
                alt="surakshit"
              />
            </div>
          </div>

          <div className="navbar-container">
            <nav id="navbar" className="navbar ">
              <div className="navbar-nav mr-auto mt-2 mt-lg-0">
                <ul>
                  <li type="none" className="nav-item">
                    <button
                      className={
                        toggleState === 1
                          ? "tabs active-tabs nav-link"
                          : "tabs nav-link"
                      }
                      onClick={() => toggleTab(1)}
                    >
                      Home
                    </button>
                  </li>
                  <li type="none" className="nav-item ">
                    <button
                      className={
                        toggleState === 2
                          ? "tabs active-tabs nav-link"
                          : "tabs nav-link"
                      }
                      onClick={() => toggleTab(2)}
                    >
                      Criminal
                    </button>
                  </li>
                  <li type="none" className="nav-item">
                    <button
                      className={
                        toggleState === 3
                          ? "tabs active-tabs nav-link"
                          : "tabs nav-link"
                      }
                      onClick={() => toggleTab(3)}
                    >
                      Feedback
                    </button>
                  </li>

                  <li type="none" className="nav-item">
                    <a to="/about" className="nav-link">
                      <button className="tabs nav-link">Location</button>
                    </a>
                  </li>

                  {/* <li type="none" className="nav-item notification">
                    {unreadCount > 0 && (
                      <span className="unread-count">{unreadCount}</span>
                    )}
                    <div
                      className={
                        toggleState === 4
                          ? "tabs active-tabs nav-link"
                          : "tabs nav-link"
                      }
                      onClick={handleNotificationClick}
                    >
                      <IoNotifications />
                    </div>
                  </li> */}
                  {/* <li type="none" className="nav-item">
                    <a to="/about" className="nav-link">
                      <div><CgProfile /></div>
                    </a>
                  </li> */}
                </ul>
              </div>
            </nav>
          </div>
        </div>
      </header>

      {/* Main  starts here */}
      <main id="main">
        {/*  {routing || <Home />}   Render Home by default if no route matches */}
        {/* <Outlet /> */}

        <div className="container-tabs">
          <div
            className={toggleState === 1 ? "content active-content" : "content"}
          >
            <Home />
          </div>
          <div
            className={toggleState === 2 ? "content active-content" : "content"}
          >
            <Criminal />
          </div>

          {/* feedback section */}
          <div
            className={toggleState === 3 ? "content active-content" : "content"}
          >
            <div className="home-content-feedback">
              <div className="home-feedback">
                {/* feedback section */}
                <div className="feedback-content">
                  <h1>Feedback</h1>
                  <hr />
                  <ol>
                    {loading && <p>Loading...</p>}
                    {error && <p style={{ color: "red" }}>{error}</p>}
                    {/* render feedback  from backend */}
                    {feedbacks.map((feedback, index) => (
                      <li key={index} type="number" className="feedback-list">
                        {feedback.name} {feedback.subject}
                        {/* Adjust based on your data structure */}
                      </li>
                    ))}

                    {/* <li type="number" className="feedback-list">
                {" "}
                feedback 1
              </li>
               */}
                  </ol>
                </div>
              </div>
            </div>
          </div>
          {/* <div
            className={
              toggleState === 4 && showNotifications
                ? "content active-content"
                : "content"
            }
          >
            {showNotifications && <Notification />}
          </div> */}
        </div>
      </main>
    </>
  );
};

export default Layout;



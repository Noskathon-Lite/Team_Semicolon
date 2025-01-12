import React from "react";
import "./Home.css";
import { Link } from "react-router-dom";

const Home = () => {
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
                    {alertlist.map((allertname, index)=>(
                        <li key={index} type="number" className="alert-list">
                            {allertname.alerts}
                        </li>
                    ))}
                </ol>
                {/* <ol>
                  <li type="number" className="alert-list">
                    {" "}
                    Alert 1
                  </li>
                  <li type="number" className="alert-list">
                    {" "}
                    Alert 1
                  </li>
                  <li type="number" className="alert-list">
                    {" "}
                    Alert 1
                  </li>
                  <li type="number" className="alert-list">
                    {" "}
                    Alert 1
                  </li>
                  <li type="number" className="alert-list">
                    {" "}
                    Alert 1
                  </li>
                </ol> */}
              </div>
            </div>
          </div>

          {/* feedback section and criminal record section */}

          <div className="home-content-feedback">
            <div className="home-feedback">
              {/* feedback section */}
              <div className="feedback-content">
                <h1>Feedback</h1>
                <hr />
                <ol>
                  {/* render feedback  from backend */}
                  {feedbacks.map((feedback, index) => (
                    <li key={index} type="number" className="feedback-list">
                      {feedback.message}{" "}
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
      </div>
    </>
  );
};

export default Home;

const feedbacks = [
  { message: "feedback 1" },
  { message: "feedback 2" },
  { message: "feedback 3" },
  { message: "feedback 4" },
  { message: "feedback 5" },
];



const alertlist = [
  { alerts: "alert 1" },
  { alerts: "alert 2" },
  { alerts: "alert 3" },
  { alerts: "alert 4" },
  { alerts: "alert 5" },
];

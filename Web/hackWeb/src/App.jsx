import React from "react";
import "./App.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Layout from "./Layout/Layout";
import DetectNotification from "./CriminalDetect/DetectedNotification";

const App = () => {
  return (
    <>
      <Router>
        <Routes>
          {/* <Route path="/" element={<Login/>}> */}
          <Route path="/" element={<Layout />} > 
            {/* <Route path="home" element={<Home />} />
            <Route path="criminal" element={<Criminal />} />*/}
            <Route path="notification" element={<DetectNotification />} /> 
          </Route>
        </Routes>
      </Router>

      {/* <Layout /> */}
    </>
  );
};

export default App;

import React from "react";
import "./App.css";
import { BrowserRouter as Router , Route , Routes } from "react-router-dom";
import Layout from "./Layout/Layout";
import Login from "./Login/Login";




const App = ()=> {
  return (
    <>
    <Router>
      <Routes>
        {/* <Route path="/" element={<Login/>}> */}
        <Route path="/" element={<Layout/>}/>
      

      </Routes>
    </Router>

      
      {/* <Layout /> */}
     
    </>
  );
}

export default App;

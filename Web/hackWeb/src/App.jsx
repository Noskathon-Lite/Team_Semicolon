import React from "react";
import "./App.css";
import { BrowserRouter as Router , Route , Routes } from "react-router-dom";
import Layout from "./Layout/Layout";




const App = ()=> {
  return (
    <>
    <Router>
      <Routes>
        <Route path="/" element={<Layout/>}/>
       

      </Routes>
    </Router>

      
      {/* <Layout /> */}
     
    </>
  );
}

export default App;

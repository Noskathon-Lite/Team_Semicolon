import React, { useState } from "react";
import "./layout.css";
import { IoNotifications } from "react-icons/io5";

const Layout = () => {
  const [toggleState, setToggleState] = useState(1);

  const toggleTab = (index) => {
    setToggleState(index);
  };

  return (
    <>
      {/* Header Starts Here */}
      <header id="header" className="header ">
        <div className="container  ">
          <div className="logo-sn ">
            <img
              src="assets/img/nepal-police.png"
              className="sn-logo-nepal-police"
              alt="surakshit"
            />
            <img
              src="assets/img/snlogo.png"
              className="sn-logo"
              alt="surakshit"
            />
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
                      Criminals
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
                      Tab3
                    </button>
                  </li>
                  <li type="none" className="nav-item">
                    <div
                      className={
                        toggleState === 4
                          ? "tabs active-tabs nav-link"
                          : "tabs nav-link"
                      }
                      onClick={() => toggleTab(1)}
                    >
                      <IoNotifications />
                    </div>
                  </li>
                  <li type="none" className="nav-item">
                    <a to="/about" className="nav-link">
                      <div>Location</div>
                    </a>
                  </li>
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
            {/* <Home /> */} Home
          </div>
          <div
            className={toggleState === 2 ? "content active-content" : "content"}
          >
            {/* <Criminal /> */} Criminal
          </div>
          <div
            className={toggleState === 3 ? "content active-content" : "content"}
          ></div>
        </div>
      </main>
    </>
  );
};

export default Layout;

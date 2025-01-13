import React, { useState, useEffect } from "react";

import { listenForMessages } from "./Firebase";
import "./notification.css";

const Notification = () => {
  const [notifications, setNotifications] = useState([]);
  const [unreadCount, setUnreadCount] = useState(0);

  useEffect(() => {
    listenForMessages();
  }, []);
  //  notification after every 10 seconds for testing
  useEffect(() => {
    const interval = setInterval(() => {
      const newNotification = {
        id: Date.now(),
        title: "Fake Notification",
        body: "This is a fake notification to simulate real-time behavior.",
        time: new Date().toLocaleString(),
        date: new Date().toLocaleDateString(),
      };

      setNotifications((prevNotifications) => [
        newNotification,
        ...prevNotifications,
      ]);
      setUnreadCount((prevCount) => prevCount + 1);
    }, 0);

    return () => clearInterval(interval); // Clear interval when msg read/ component unmount
  }, []);

  const handleNotificationClick = (index) => {
    setUnreadCount(0); // Mark all notifications as read
    setNotifications((prevNotifications) =>
      prevNotifications.map((notification, i) =>
        i === index ? { ...notification, read: true } : notification
      )
    );
  };

  const sortedNotifications = [...notifications].sort(
    (a, b) => a.read - b.read
  );
  return (
    <div className="notification-container">
      {/* <div
        className="notification-icon"
        onClick={handleNotificationClick}
        style={{ position: "relative", cursor: "pointer" }}
      >
        <span>🔔</span>
        {unreadCount > 0 && (
          <span
            className="unread-count"
            style={{
              position: "absolute",
              top: "-5px",
              right: "-5px",
              backgroundColor: "red",
              borderRadius: "50%",
              color: "white",
              padding: "2px 6px",
              fontSize: "12px",
            }}
          >
            {unreadCount}
          </span>
        )}
      </div> */}

      {notifications.length > 0 && (
        <div
          className="notification-popup"
        //   style={{
        //     position: "absolute",
        //     top: "30px",
        //     right: "0",
        //     width: "300px",
        //     backgroundColor: "white",
        //     border: "1px solid #ccc",
        //     borderRadius: "8px",
        //     zIndex: 1000,
        //     maxHeight: "400px",
        //     overflowY: "scroll",
        //   }}
        >
          <p className="notify">Notifications</p>
          <ul className="notification-list">
            {sortedNotifications.map((notif, index) => (
              <li
                key={index}
                className={`notification-item ${
                  notif.read ? "read" : "unread"
                }`}
                onClick={() => handleNotificationClick(index)}
              >
                <strong>{notif.title}</strong>
                <p>{notif.body}</p>
                <small style={{ color: "#888" }}>
                  {notif.date} at {notif.time}
                </small>
              </li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
};

export default Notification;

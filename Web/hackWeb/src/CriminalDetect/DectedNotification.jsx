import React, { useEffect, useState } from 'react';

const DetectNotification = () => {
  const [message, setMessage] = useState(null);

  useEffect(() => {
    // Establish WebSocket connection
    const socket = new WebSocket('ws://192.168.23.44:8000/ws/notifications/');

    // On message from WebSocket
    socket.onmessage = function (event) {
      const data = JSON.parse(event.data);
      if (data.message === 'Criminal Detected') {
        setMessage('Criminal Detected or Matched!');
        // Optionally, trigger a visual notification like an alert or modal.
        alert('Criminal Detected!');
      }
    };

    // Close connection on cleanup
    return () => socket.close();
  }, []);

  return (
    <div>
      {message && <h1>{message}</h1>}
    </div>
  );
};

export default DetectNotification;

import React, { useEffect, useState } from 'react';

const DetectNotification = () => {
  const [message, setMessage] = useState(null);

  useEffect(() => {
    // Establish WebSocket connection
    const socket = new WebSocket('ws://127.0.0.1/ws/alerts/');

   
    socket.onmessage = function(event) {
      const data = JSON.parse(event.data);
      console.log('Alert:', data.message);
  };
  
  socket.onclose = function(event) {
      console.error('WebSocket closed unexpectedly');
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

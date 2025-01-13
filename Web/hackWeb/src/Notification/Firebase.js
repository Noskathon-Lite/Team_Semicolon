import { getMessaging, getToken, onMessage } from "firebase/messaging";
import { initializeApp } from "firebase/app";

const firebaseConfig = {
  apiKey: "AIzaSyBtk_GG-x-uCmIIdk-lA5DmfcRs9buHmBs",
  authDomain: "fir-project-e13f3.firebaseapp.com",
  projectId: "fir-project-e13f3",
  storageBucket: "fir-project-e13f3.firebasestorage.app",
  messagingSenderId: "58066396982",
  appId: "1:58066396982:web:a65a289a4ada373799f597",
};

// Initialize Firebase
export const app = initializeApp(firebaseConfig);
export const messaging = getMessaging(app);

if ('serviceWorker' in navigator) {
    navigator.serviceWorker.register('./firebase-messaging-sw.js')
      .then((registration) => {
        console.log('Service Worker registered with scope:', registration.scope);
      }).catch((err)=> {
        console.log('Service Worker registration failed:', err);
      });
  }

export const generateToken = async () => {
  const permission = await Notification.requestPermission();
  if (permission === "granted") {
    try {
      const token = await getToken(messaging, {
        vapidKey: "BGOlblrhME2SGo_8DP3-ZIUVRBxg8MTr6l8IEygiNW8iLVa-VzY2iZ1rfY0GXrdwswpHvwsKo2nrkNWw2IPW-8M",
      });
      console.log("FCM Token:", token);
      // Send the token to your server or save it for later use
    } catch (error) {
      console.error("Error getting FCM token:", error);
    }
  } else {
    console.log("Unable to get permission to notify.");
  }
  
  onMessage(messaging, (payload) => {
    console.log("Message received. ", payload);
    // Customize notification here
    const notificationTitle = payload.notification.title;
    const notificationOptions = {
      body: payload.notification.body,
      icon: payload.notification.icon,
    };
  
    new Notification(notificationTitle, notificationOptions);
  });
};


// Export the listenForMessages function
export const listenForMessages = (callback) => {
    onMessage(messaging, (payload) => {
      console.log("Message received: ", payload);

      const notificationWithTimestamp = {
        ...payload.notification,
        time: new Date().toLocaleString(), 
      };
  
     
      updateNotifications(notificationWithTimestamp);
      
      const notification = {
        title: payload.notification.title,
        body: payload.notification.body,
        timestamp: new Date(),
      };
      callback(notification); // Pass notification to the callback function
    });
  };

importScripts('https://www.gstatic.com/firebasejs/9.0.0/firebase-app-compat.js');
importScripts('https://www.gstatic.com/firebasejs/9.0.0/firebase-messaging-compat.js');


firebase.initializeApp({
  apiKey: 'AIzaSyBtk_GG-x-uCmIIdk-lA5DmfcRs9buHmBs',
  authDomain: 'fir-project-e13f3.firebaseapp.com',
  projectId: 'fir-project-e13f3',
  storageBucket: 'fir-project-e13f3.firebasestorage.app',
  messagingSenderId: '58066396982',
  appId: '1:58066396982:web:a65a289a4ada373799f597',
  measurementId: 'G-measurement-id'
});

const messaging = firebase.messaging();

messaging.onBackgroundMessage((payload) => {
  console.log('[firebase-messaging-sw.js] Received background message ', payload);
 
  const notificationTitle = payload.notification.title;
  const notificationOptions = {
    body: payload.notification.body,
    icon: payload.notification.icon,
    sound: '/assets/sound/notification-sound1.mp3',
    data: payload.data,
    
  };
 

  self.registration.showNotification(notificationTitle, notificationOptions);
});

const audio = new Audio('/assets/sound/notification-sound1.mp3');
audio.play();

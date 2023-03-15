importScripts('https://www.gstatic.com/firebasejs/8.8.0/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/8.8.0/firebase-messaging.js');

firebase.initializeApp({
  apiKey: "AIzaSyC8WuxeTauC8ZOwQ46IRnR-DQKrgfdXT3g",
  authDomain: "sprint2-afb08.firebaseapp.com",
  projectId: "sprint2-afb08",
  storageBucket: "sprint2-afb08.appspot.com",
  messagingSenderId: "882585379191",
  appId: "1:882585379191:web:efa4b79459b8afcf4f8172",
  measurementId: "G-M8WQ03Q0Z3"
});
const messaging = firebase.messaging();

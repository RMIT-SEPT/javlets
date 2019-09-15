import React from 'react';
import './App.css';
import HeaderComponent from './components/HeaderComponent';
import WallComponent from './components/wall/WallComponent';
import ChatComponent from './components/chat/ChatComponent';
import WebSocketComponent from './components/chat/WebSocketComponent';
// import LoginandRegistration from './components/LoginandRegistration';

function App() {

  // Main structure
  return (
    <div className="App">
      <header className="App-header">
        <HeaderComponent />
      </header>
      <body className="App-body">
      <WebSocketComponent/>
      <WallComponent />
      {/* <LoginandRegistration /> */}
      </body>
    </div>
  );
}

export default App;


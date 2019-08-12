import React from 'react';
import './App.css';
import HeaderComponent from './components/HeaderComponent';
import WallComponent from './components/WallComponent';
import ChatComponent from './components/ChatComponent';

function App() {

  // Main structure
  return (
    <div className="App">
      <header className="App-header">
        <HeaderComponent />
      </header>
      <body className="App-body">
      <ChatComponent />
      <WallComponent />
      </body>
    </div>
  );
}

export default App;

import React from 'react';
import './App.css';
import HeaderComponent from './components/HeaderComponent';
import WallComponent from './components/wall/WallComponent';
import ChatComponent from './components/chat/ChatComponent';

import cookie from 'js-cookie';

// import LoginandRegistration from './components/LoginandRegistration';


function BodyContent(props) {
  if ( props.isloggedin) {
    return <React.Fragment>
      <ChatComponent />
      <WallComponent />
    </React.Fragment>;
  }else{
  return <React.Fragment>
    <h2 className="warning">Please sign in</h2>
  </React.Fragment>;
}
}

function App() {
  // Main structure
  return (
    <div className="App">
      <div className="App-header">
        <HeaderComponent />
      </div>
      <div className="App-body">
        <BodyContent isloggedin={cookie.get('id')}/>
      </div>
    </div>
  );
}

export default App;

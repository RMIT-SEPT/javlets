import React, { Component } from 'react'
import ConnectionListComponent from './ConnectionListComponent';

class ChatComponent extends Component{
    state = {  }
render(){
   return(
    <div className="body-item chat">
      <h1>Chat</h1>

      <h2>Your connections</h2>
      <ConnectionListComponent type={1} />
      <h2>Add connections</h2>
      <ConnectionListComponent type={0} />
      <h2>Message</h2>


    </div>
   );
}
}
export default ChatComponent;
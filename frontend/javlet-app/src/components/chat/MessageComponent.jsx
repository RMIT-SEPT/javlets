import React, { Component } from 'react'

class MessageComponent extends Component{
    state = {  }
render(){
   return(
    <div className="body-item chat">
      <p>{this.props.message}</p>
    </div>
   );
}
}
export default MessageComponent;
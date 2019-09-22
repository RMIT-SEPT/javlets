import React, { Component } from 'react'

class MessageComponent extends Component{
    state = {  }
render(){
   return(
    <div className="chat">
        <h4>{this.props.sender}</h4>
      <p> <img className='w3-circle' alt="" src={'https://loremflickr.com/100/100/person' + this.props.message} />{this.props.message}</p>
    </div>
   );
}
}
export default MessageComponent;
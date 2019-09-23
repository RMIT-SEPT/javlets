import React, { Component } from 'react'

class MessageComponent extends Component{
    state = {  }
render(){
   return(
    <div className="chat">
        <h4>{this.props.sender} | {this.props.time}</h4>
      <p> <img className='w3-circle' alt="" src={'https://img.icons8.com/cotton/64/000000/name--v2.png'} />{this.props.message}</p>
    </div>
   );
}
}
export default MessageComponent;
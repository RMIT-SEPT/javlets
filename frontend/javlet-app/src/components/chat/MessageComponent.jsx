import React, { Component } from 'react'

class MessageComponent extends Component{
    state = {  }
render(){
   return(
    <div className="chat container darker">
    <img src={'https://img.icons8.com/cotton/64/000000/name--v2.png'} alt="" className="right"/><span className="name">{this.props.sender}</span>
    <p className="w3-blue w3-round-large">{this.props.message} </p>
    <span className="w3-tiny">{this.props.datetime}</span>
  </div>
   
    );
}
}
export default MessageComponent;
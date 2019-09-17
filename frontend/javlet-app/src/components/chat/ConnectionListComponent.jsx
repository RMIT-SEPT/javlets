import React, { Component } from 'react'
import ConnectComponent from './ConnectComponent';

class ConnectionListComponent extends Component{
    state = {  }
render(){
   return(
    <div className="connectList">
      <ConnectComponent type={this.props.type}/>
      <ConnectComponent type={this.props.type}/>
      <ConnectComponent type={this.props.type}/>
      <ConnectComponent type={this.props.type}/>
      
    </div>
   );
}
}
export default ConnectionListComponent;
import React, { Component } from 'react'
import ConnectComponent from './ConnectComponent';
import axios from "axios";
import API from '../../Constants.js'
import cookie from 'js-cookie';

class ConnectionListComponent extends Component{
    state = { 
      connections: [],
      user: []

     }

     componentDidMount(){
      this.refreshConnections();

      if(cookie.get('id')){
        axios.get(API + '/auth/get/?id=' + cookie.get('id'))
        .then((response) => {
            this.setState({user: response.data});
        });
      }
    }
  
    refreshConnections() {
        axios.get(API + '/auth/userdb')
        .then((response) => {
    Object.entries(response.data).forEach(
      ([key, value]) => {
        this.setState(state => ({ connections: [value, ...state.connections] }))
        console.log(this.state.connections)
      } 
  );
        });
    }

render(){
   return(
    <div className="connectList">
      <ul id="" ref={(div) => {this.connections = div;}}>
                {this.state.connections.slice(0).reverse().map((obj, index) => {

                  if(obj.id !== this.state.user.id){
                    return <ConnectComponent key={Math.random()} name={obj.givenName + " " +  obj.familyName} type={this.props.type}/>
                  }
                  return ""
                  }
          )}
        </ul>


    </div>
   );
}
}
export default ConnectionListComponent;
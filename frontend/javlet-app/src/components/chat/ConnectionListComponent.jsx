import React, { Component } from 'react'
import ConnectComponent from './ConnectComponent';
import axios from "axios";
import { API } from '../../Constants.js'
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
        if(cookie.get('id')){
          axios.get(API + '/connection/connections/?id=' + cookie.get('id'))
          .then((response) => {
            Object.entries(response.data).forEach(
              ([key, value]) => {
                this.setState(state => ({ connections: [value, ...state.connections] }));
              } 
          );
          });
        }
    }

    printList(){
        if(this.state.connections.length > 0){
            let buffer = [];

            this.state.connections.slice(0).reverse().forEach((obj, index) => {
                buffer.push(<ConnectComponent key={Math.random()} name={obj.givenName + " " +  obj.familyName} conID={obj.id} type={0}/>);
            });
              return buffer;
        }else{
            return <h3>You have no connections</h3>
        }
    }

render(){
   return(
    <div className="connectList">
          {this.printList()}
    </div>
   );
}
}
export default ConnectionListComponent;
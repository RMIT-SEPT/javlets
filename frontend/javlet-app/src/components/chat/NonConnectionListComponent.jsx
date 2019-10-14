import React, { Component } from 'react'
import ConnectComponent from './ConnectComponent';
import axios from "axios";
import API from '../../Constants.js'
import cookie from 'js-cookie';

class NonConnectionListComponent extends Component{
    state = { 
      connections: [],
      connectSent: [],
      connectRequest: [],
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

          // Get connections which user haven't added/connected with
          axios.get(API + '/connection/nonconnection/?id=' + cookie.get('id'))
          .then((response) => {
            Object.entries(response.data).forEach(
              ([key, value]) => {
                this.setState(state => ({ connections: [value, ...state.connections] }));
              } 
          );
          });

          // Get connections which user has sent
          axios.get(API + '/connection/connectRequest/?id=' + cookie.get('id'))
          .then((response) => {
            Object.entries(response.data).forEach(
              ([key, value]) => {
                this.setState(state => ({ connectRequest: [value, ...state.connectRequest] }));
              } 
          );
          });



          // Get connection requests
          axios.get(API + '/connection/connectSent/?id=' + cookie.get('id'))
          .then((response) => {
            Object.entries(response.data).forEach(
              ([key, value]) => {
                this.setState(state => ({ connectSent: [value, ...state.connectSent] }));
              } 
          );
          });

        }

    }

    printList(){
      if(this.state.connections.length > 0 || this.state.connectSent.length > 0 || this.state.connectRequest.length > 0){
        let buffer = [];

        this.state.connectRequest.slice(0).reverse().forEach((obj, index) => {
          buffer.push(<ConnectComponent key={Math.random()} name={obj.givenName + " " +  obj.familyName} conID={obj.id} type={3}/>);
      });

        this.state.connectSent.slice(0).reverse().forEach((obj, index) => {
          buffer.push(<ConnectComponent key={Math.random()} name={obj.givenName + " " +  obj.familyName} conID={obj.id} type={2}/>);
      });

        this.state.connections.slice(0).reverse().forEach((obj, index) => {
            buffer.push(<ConnectComponent key={Math.random()} name={obj.givenName + " " +  obj.familyName} conID={obj.id} type={1}/>);
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
export default NonConnectionListComponent;
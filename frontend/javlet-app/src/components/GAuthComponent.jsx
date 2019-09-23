import React, { Component } from 'react'
import GoogleLogin from 'react-google-login';
import axios from "axios";

import cookie from 'js-cookie';

const responseGoogle = (response) => {

  console.log(response);

  var ID = response.profileObj.googleId

  const newItem = {
    ID: ID,
    email: response.profileObj.email,
    name: response.profileObj.name,
    imageUrl: response.profileObj.imageUrl,
  };


  // Send to server to authenicate
  axios.post('http://localhost:8080/user/login', newItem)
  .then(function (response) {
    if(response.data){
      // Valid login
      cookie.set('ID', ID)
      alert("Logged in");
      window.location.reload();
    }else{
      // Invalid (Not rmit student email)
      alert ("Please sign in using RMIT student email");
    }
  })
}

class GAuthComponent extends Component{

  logout() {
    cookie.remove('id');
    window.location.reload();
  }

  componentDidMount() {
    alert("Trigger");
    
    axios.get('http://localhost:8080/user/get?Id=' + cookie.get('ID'))
    .then(function (response) {
      console.log(response);
    })
  }

render(){
  if(cookie.get('id')){
 return(

<React.Fragment>
  {this.getUserObj}
  <p>You are logged in</p>
  <button onClick={this.logout}>
      Logout
    </button>
</React.Fragment>

 );

  }else{
    return(

      <GoogleLogin
      clientId="891671559740-o137u8iumah93hhfvhf93hp4l8hvjfg2.apps.googleusercontent.com"
      buttonText="Login"
      onSuccess={responseGoogle}
      cookiePolicy={'single_host_origin'}
    />
    )

  }
}
}
export default GAuthComponent;
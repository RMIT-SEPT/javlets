import React, { Component } from 'react'
import GoogleLogin from 'react-google-login';
import axios from "axios";
 
const responseGoogle = (response) => {
  console.log(response.profileObj);

  const newItem = {
    email: response.profileObj.email,
    name: response.profileObj.name,
    imageUrl: response.profileObj.imageUrl,
  };

  return axios.post("http://localhost:8080/login", newItem);
}

class GAuthComponent extends Component{
  state = {  }
render(){
 return(
  <GoogleLogin
    clientId="891671559740-o137u8iumah93hhfvhf93hp4l8hvjfg2.apps.googleusercontent.com"
    buttonText="Login"
    onSuccess={responseGoogle}
    //onFailure={responseGoogle}
    cookiePolicy={'single_host_origin'}
  />
 );
}
}
export default GAuthComponent;
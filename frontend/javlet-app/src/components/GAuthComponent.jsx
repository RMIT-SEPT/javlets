import React, { Component } from 'react'
import GoogleLogin from 'react-google-login';
import axios from "axios";

import cookie from 'js-cookie';

const responseGoogle = (response) => {

  const newItem = {
    email: response.profileObj.email,
    name: response.profileObj.name,
    imageUrl: response.profileObj.imageUrl,
  };

  cookie.set('isLogged', true)
  window.location.reload();
  return axios.post("http://javlet.social:8080/login", newItem);
}



class GAuthComponent extends Component{

  logout() {
    cookie.remove('isLogged');
    window.location.reload();
  }

render(){
  if(cookie.get('isLogged')){
 return(

<React.Fragment>
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
      //onFailure={responseGoogle}
      cookiePolicy={'single_host_origin'}
    />
    )

  }
}
}
export default GAuthComponent;
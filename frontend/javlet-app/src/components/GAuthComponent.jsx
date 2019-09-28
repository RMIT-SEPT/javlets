import React, { Component } from 'react'
import GoogleLogin from 'react-google-login';
import axios from "axios";

import cookie from 'js-cookie';

const responseGoogle = (response) => {
  var id = response.profileObj.googleId;
  var studentId = (response.profileObj.email.split('@'))[0];

  const newItem = {
    id: id,
    email: response.profileObj.email,
    name: response.profileObj.name,
    imageUrl: response.profileObj.imageUrl,
  };


  // Send to server to authenicate
  axios.post('http://javlet.social:8080/auth/login', newItem)
  .then(function (response) {
    if(response.data){
      // Valid login
      cookie.set('id', id);
	  cookie.set('studentId', studentId);
      window.location.reload();
    }else{
      // Invalid (Not rmit student email)
      alert ("Please sign in using RMIT student email");
    }
  })
}

class GAuthComponent extends Component{
 
  state = {
    user: [],
    count: 0
  }

  logout() {
    cookie.remove('id');
	  cookie.remove('studentId');
    window.location.reload();
  }

  componentDidMount() {
    if(cookie.get('id')){
    axios.get('http://javlet.social:8080/auth/get?id=' + cookie.get('id'))
    .then((response) => {
      if(response.data.id != null){
        this.setState({user: response.data});
      }else{
        this.logout();
      }
    });
    axios.get('http://javlet.social:8080/auth/count')
    .then((response) => {
      console.log(response.data);
      this.setState({count: response.data});
    });
  }
}

render(){
  if(cookie.get('id')){
 return(

<React.Fragment>
  <div className = "userLogout">
  <p>Logged in as: {this.state.user.givenName + " " + this.state.user.familyName} (<b>{this.state.user.username}</b>)</p>
  <p><b>User count:</b> {this.state.count}</p>
  <button onClick={this.logout}>Logout</button>
  </div>
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
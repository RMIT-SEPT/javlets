import React, { Component } from "react";
import GoogleLogin from "react-google-login";
import axios from "axios";

import cookie from "js-cookie";
import { API } from "../Constants.js";

const responseGoogle = response => {
  var id = response.profileObj.email.split("@")[0];

  const newItem = {
    id: id,
    email: response.profileObj.email,
    name: response.profileObj.name,
    imageUrl: response.profileObj.imageUrl
  };
 

  // Send to server to authenicate
  axios
    .post(API + "/auth/login", newItem)
    .then(response => {
      if (response.data) {
        // Valid login
        cookie.set("id", id);
        window.location.reload();
      } else {
        // Invalid (Not rmit student email)
        alert("Please sign in using RMIT student email");
      }
    })
    .catch(error => {
      alert(error + "\nUnable to connect to server.");
    });
};

class GAuthComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      user: 0,
      count: 0
    };
    this.makeMentor = this.makeMentor.bind(this);
    this.makeStudent = this.makeStudent.bind(this);
  }

  logout() {
    cookie.remove("id");
    window.location.reload();
  }

  checkLogin() {
    if (cookie.get("id")) {
      axios
        .get(API + "/auth/get/?id=" + cookie.get("id"))
        .then(response => {
          if (response.data.id != null) {
            this.setState({ user: response.data });
            // Only get user count if ID can be validated
            axios.get(API + "/auth/count").then(response => {
              this.setState({ count: response.data });
            });
          } else {
            this.logout();
          }
        })
        .catch(error => {
          this.logout();
        });
    }
  }

  componentDidMount() {
    this.checkLogin();

    // Automatically log out
    this.intervalID = setInterval(this.checkLogin.bind(this), 2000);
  }

  render() {
    if (cookie.get("id")) {
      return (
        <React.Fragment>
          <div className="userLogout">
            <p>
              Logged in as:{" "}
              {this.state.user.givenName + " " + this.state.user.familyName} (
              <b>{this.state.user.id}</b>)
            </p>
            <p>
              <b>User count:</b> {this.state.count}
            </p>
            {this.state.user.mentor ? (
              <button type="disablePost" onClick={this.makeStudent}>
                {" "}
                Show Student View{" "}
              </button>
            ) : (
              <button type="enablePost" onClick={this.makeMentor}>
                {" "}
                Show Mentor View{" "}
              </button>
            )}
            <button onClick={this.logout}>Logout</button>
          </div>
        </React.Fragment>
      );
    } else {
      return (
        <GoogleLogin
          clientId="891671559740-o137u8iumah93hhfvhf93hp4l8hvjfg2.apps.googleusercontent.com"
          buttonText="Login"
          onSuccess={responseGoogle}
          cookiePolicy={"single_host_origin"}
        />
      );
    }
  }

  makeMentor() {
    const promoted = {
      id: this.state.user.id
    };
    axios.post(API + "/auth/promote", promoted);
  }

  makeStudent() {
    const demoted = {
      id: this.state.user.id
    };
    axios.post(API + "/auth/demote", demoted);
  }
}

export default GAuthComponent;

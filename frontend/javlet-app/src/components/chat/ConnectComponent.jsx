import React, { Component } from "react";
import cookie from "js-cookie";
import axios from "axios";
import { API } from "../../Constants.js";

class ConnectComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {};
    this.handleMessage = this.handleMessage.bind(this);
    this.handleRequest = this.handleRequest.bind(this);
  }

  render() {
    let button;

    switch (this.props.type) {
      case 0:
        button = (
          <button
            className="w3-btn w3-blue btn-primary btn-lg btn-block"
            type="button"
            onClick={this.handleMessage}
            value="Message"
          >
            Message
          </button>
        );
        break;
      case 1:
        button = (
          <button
            className="w3-btn w3-red btn-primary btn-lg btn-block"
            type="button"
            onClick={this.handleRequest}
            value="Add"
          >
            Request
          </button>
        );
        break;
      case 2:
        button = (
          <span className="w3-grey btn-primary btn-lg btn-block">Sent</span>
        );
        break;
      case 3:
        button = (
          <button
            className="w3-btn w3-green btn-primary btn-lg btn-block"
            type="button"
            onClick={this.handleRequest}
            value="Add"
          >
            Accept
          </button>
        );
        break;
      default:
        break;
    }

    return (
      <div className="connection">
        {button}
        <h4 className="w3-panel w3-blue">{this.props.name}</h4>
        <span className="w3-tiny">{this.props.conID}</span>
      </div>
    );
  }

  handleRequest(event) {
    const newItem = {
      senderId: cookie.get("id"),
      recipientId: this.props.conID
    };

    return axios.post(API + "/connection/add", newItem);
  }

  handleMessage(event) {
    cookie.set("rID", this.props.conID);
  }
}
export default ConnectComponent;

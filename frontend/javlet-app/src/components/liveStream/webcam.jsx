import React, { Component } from 'react'
import Webcam from "react-webcam";
import LiveChatCommenting from './LiveChatCommenting';


class WebCamCapture extends Component {

  enableWebcam = () => this.setState({ webcamEnabled: true });
  disableWebCam = () => this.setState({webcamEnabled: false});

  constructor(props) {
    super(props);
    this.state = { webcamEnabled: false };
  }

  render() {
    const videoConstraints = {
      facingMode: "user"
    };
    return (
      <div>
        {this.state.webcamEnabled ? (
          <>
          <button type="disable" onClick={this.disableWebCam}>
            Disable webcam
          </button>
           <br /><Webcam videoConstraints={videoConstraints} />

          <h2>Comments </h2>
          <LiveChatCommenting />
          </>
        ):(
          <>
          <button type="create" onClick={this.enableWebcam}>
            Create Live Stream
          </button>
          <h2>Upcoming Streams</h2>
          <list>
            <ul>0900h 11th October by Samwise</ul>
            <ul>1730h 3rd November by Elia</ul>
            <ul>1400h 1st Decemeber by Jason</ul>
            <ul>1100h 2nd Decemeber by Jason</ul>
          </list>
          </>
        )}

      </div>
    );
  }    
}

export default WebCamCapture;

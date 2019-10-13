import React, { Component } from 'react';
import axios from 'axios';
import cookie from 'js-cookie';
import { API } from '../../Constants.js'

import Iframe from 'react-iframe';
import LiveChatCommenting from './LiveChatCommenting';
import ScheduleForm from './ScheduleForm';
import ScheduledLiveStream from './ScheduledLiveStream';


class WebCamCapture extends Component {

  enableWebcam = () => this.setState({ webcamEnabled: true });
  disableWebCam = () => this.setState({webcamEnabled: false});
  schedule = () => this.setState({scheduleStream: true});
  showschedule = () => this.setState({scheduleStream: false});

  constructor(props) {
    super(props);
    this.state = { 
      user: [],
      webcamEnabled: false,
      scheduleStream: false
    };
  }

  componentDidMount() {
    axios.get(API + '/auth/get/?id=' + cookie.get('id'))
      .then((response) => {
        this.setState({user: response.data});
    });
  }

  render() {
    return (
      <div>
        {this.state.webcamEnabled  ? (
          <>
          <button type="disable" onClick={this.disableWebCam}>
            Disable webcam
          </button>
           <br />
           {/* Make current stream */}
          <Iframe allow="camera;microphone" src="http://localhost:3001/streamer" width="426px" height="240px"/>
          
          <LiveChatCommenting />
          </>
        ):(
          <>
          {this.state.user.mentor ? (
          <button type="create" onClick={this.enableWebcam}>
            Start Live Stream
          </button>):('')}
          
          
          {this.state.scheduleStream ? (
            <>
            <button type="create" onClick={this.showschedule}> Show Live Stream Schedules </button>
            <ScheduleForm/>
            </>
            ):(
            <>
            {/* Show current stream */}
            {/* <Websocket/> */}
            <Iframe src="http://localhost:3001/client" width="426px" height="240px" />
            <LiveChatCommenting />
            {this.state.user.mentor ? (
            <button type="create" onClick={this.schedule}> Schedule a Live Stream </button>
            ):('')}
            <h2>Upcoming Streams</h2>
            <ScheduledLiveStream/>
            </>
          )}
          </>
        )}

      </div>
    );
  }    
}

export default WebCamCapture;

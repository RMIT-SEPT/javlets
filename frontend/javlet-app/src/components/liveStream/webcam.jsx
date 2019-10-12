import React, { Component } from 'react';

// import Webcam from 'react-webcam';
import Iframe from 'react-iframe';
import LiveChatCommenting from './LiveChatCommenting';
import ScheduleForm from './ScheduleForm';
import ScheduledLiveStream from './ScheduledLiveStream';
// import LivestreamWebSocket from './LivestreamWebSocket';

class WebCamCapture extends Component {

  enableWebcam = () => this.setState({ webcamEnabled: true });
  disableWebCam = () => this.setState({webcamEnabled: false});
  schedule = () => this.setState({scheduleStream: true});
  showschedule = () => this.setState({scheduleStream: false});

  constructor(props) {
    super(props);
    this.state = { 
      webcamEnabled: false,
      scheduleStream: false
    };
  }

  render() {
    // const videoConstraints = {
    //   facingMode: "user"
    // };
    return (
      <div>
        {this.state.webcamEnabled ? (
          <>
          <button type="disable" onClick={this.disableWebCam}>
            Disable webcam
          </button>
           <br />
           {/* <Webcam videoConstraints={videoConstraints} /> */}
           {/* Make current stream */}
          <Iframe allow="camera;microphone" src="http://localhost:3001/streamer" width="426px" height="240px"/>
          
          <LiveChatCommenting />
          </>
        ):(
          <>
          <button type="create" onClick={this.enableWebcam}>
            Start Live Stream
          </button>
          
          {this.state.scheduleStream ? (
            <>
            <button type="create" onClick={this.showschedule}> Show Live Stream Schedules </button>
            <ScheduleForm/>
            </>
            ):(
            <>
            {/* Show current stream */}
            {/* <LivestreamWebSocket /> */}
            <Iframe src="http://localhost:3001/client" width="426px" height="240px" />
            <LiveChatCommenting />
            <button type="create" onClick={this.schedule}> Schedule a Live Stream </button>
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

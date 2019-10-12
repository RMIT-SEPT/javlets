import React, { Component } from 'react';
import Webcam from 'react-webcam';
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
      webcamEnabled: false,
      scheduleStream: false
    };
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
            Start Live Stream
          </button>
          
          {this.state.scheduleStream ? (
            <>
            <button type="create" onClick={this.showschedule}> Show Live Stream Schedules </button>
            <ScheduleForm/>
            </>
            ):(
            <>
            {/* // Show current stream */}
		        <Iframe src="./webcam-base64-streaming/client.html" width="426px" height="240px"/>
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

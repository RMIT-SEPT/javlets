import React, { Component } from 'react'
import Webcam from "react-webcam";
// import NewWindow from 'react-new-window';
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
          <button type="enable" onClick={this.enableWebcam}>
            Make Live Stream
          </button>
          <button type="watch" onClick={this.enableWebcam}>
            Watch Live Steam
          </button>
          </>
        )}
        {/* ... */}
      </div>
    );
  }

      // <NewWindow>
      //   <div>
      //     <h1>Live Stream</h1>
      //     <Webcam videoConstraints={videoConstraints} />
      //     <LiveChatCommenting />
      //   </div>
      // </NewWindow>
    
}

// const WebcamComponent = () => <Webcam />;

// const videoConstraints = {
//     width: 1280,
//     height: 720,
//     facingMode: "user"
//   };
   
// const WebcamCapture = () => {
//   const webcamRef = React.useRef(null);

//   const capture = React.useCallback(
//       () => {
//       const imageSrc = webcamRef.current.getScreenshot();
//       },
//       [webcamRef]
//   );

//   return (
//       <>      
//       <Webcam
//           audio={false}
//           height={720}
//           ref={webcamRef}
//           screenshotFormat="image/jpeg"
//           width={1280}
//           videoConstraints={videoConstraints}
//       />
//       <button onClick={capture}>Capture photo</button>
      
//       </>
//   );
// };

  // class WebcamCapture extends Component {
  //   render() {
  //     const videoConstraints = {
  //       facingMode: { exact: "environment" }
  //     };
   
  //     return <Webcam videoConstraints={videoConstraints} />;
  //   }
  // }

export default WebCamCapture;

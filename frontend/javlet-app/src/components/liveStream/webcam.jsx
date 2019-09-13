import React, { Component } from 'react'
import Webcam from "react-webcam";
import NewWindow from 'react-new-window'

// const WebcamComponent = () => <Webcam />;

const videoConstraints = {
    width: 1280,
    height: 720,
    facingMode: "user"
  };
   
const WebcamCapture = () => {
  const webcamRef = React.useRef(null);

  const capture = React.useCallback(
      () => {
      const imageSrc = webcamRef.current.getScreenshot();
      },
      [webcamRef]
  );

  return (
      <>      
      <Webcam
          audio={false}
          height={720}
          ref={webcamRef}
          screenshotFormat="image/jpeg"
          width={1280}
          videoConstraints={videoConstraints}
      />
      <button onClick={capture}>Capture photo</button>
      
      </>
  );
};

  class WebCamCapture extends Component {
    render() {
      const videoConstraints = {
        facingMode: "user"
      };
   
      return (
        <NewWindow>
        <div className="body-item wall">
          <h1>Live Stream</h1>
          <Webcam videoConstraints={videoConstraints} />
        </div>
        </NewWindow>
          
      );
    }
  }

  // class WebcamCapture extends Component {
  //   render() {
  //     const videoConstraints = {
  //       facingMode: { exact: "environment" }
  //     };
   
  //     return <Webcam videoConstraints={videoConstraints} />;
  //   }
  // }

export default WebCamCapture;

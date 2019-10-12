import React, { Component } from 'react'

class ReactCam extends Component {

    sendMessage=()=>{
        // const {websocket} = this.props // websocket instance passed as props to the child component.

        // const data = 232;
        // try {
        //     const video = document.querySelector('video');
        
        // // request access to webcam
        // navigator.mediaDevices.getUserMedia({video: {width: 426, height: 240}}).then((stream) => video.srcObject = stream);
        
        // returns a frame encoded in base64
        //     const getFrame = () => {
        //         const canvas = document.createElement('canvas');
        //         canvas.width = video.videoWidth;
        //         canvas.height = video.videoHeight;
        //         canvas.getContext('2d').drawImage(video, 0, 0);
        //         const data = canvas.toDataURL('image/png');
        //         return data;
        //     }
        //     websocket.send(data) //send data to the server
        // } catch (error) {
        //     console.log(error) // catch error
        // }
    }
    render() {
        return (
            <div>
                {/* <video autoPlay></video> */}
            </div>
        );
    }
}

export default ReactCam;
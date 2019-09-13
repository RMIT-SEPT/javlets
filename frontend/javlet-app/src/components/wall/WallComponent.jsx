import React, { Component } from 'react'
// import WallPostInputForm from './WallPostInputForm';
import PostComponent from './PostComponent';
import WebCamCapture from './../liveStream/webcam';


class WallComponent extends Component{
  constructor(props) {
    super(props);
    this.state = {
      liveStream: false
    };
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  render(){

    return(
      <div className="body-item wall">

        {this.state.liveStream ? (<WebCamCapture />) : ( false )}

        <h1>The Wall <input className="w3-btn w3-blue" type="submit" value="Live Stream" onClick={this.handleSubmit}/></h1>
          {/* <WallPostInputForm />
          <br /> */}
          <div className="postList">
          {/* <div> */}
          <PostComponent posts={this.state.posts} />
          </div>
      </div>
    );
  }

  handlePostTypeChange(event) {
    this.setState({ postType: event.target.value });
  }

  handleSubmit(event) {
    event.preventDefault();
    this.setState({ liveStream: true }); 
  }
}

export default WallComponent;
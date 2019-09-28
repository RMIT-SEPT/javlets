import React, { Component } from 'react';
import PostComponent from './PostComponent';
import WebCamCapture from './../liveStream/webcam';
import WallPostInputForm from './WallPostInputForm';


class WallComponent extends Component{

  enableLiveStream = () => this.setState({ liveStream: true });
  disableLiveStream = () => this.setState({liveStream: false});
  makeAPost = () => this.setState({makePost: true});
  hideForm = () => this.setState({makePost: false});

  constructor(props) {
    super(props);
    this.state = {
      liveStream: false,
      makePost: false
    };
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  render(){

    return(
      <div className="body-item wall">
        {this.state.liveStream ? (
          <livestream>
            <h1>Live Stream</h1>
            <button type="disable" onClick={this.disableLiveStream}> Display The Wall </button>
            <WebCamCapture />
          </livestream>
          ):(
          <wall>
            <h1>The Wall </h1>
            {this.state.makePost ?( 
              <button type="disablePost" onClick={this.hideForm}> Show Wall </button>
            ):(
              <button type="enablePost" onClick={this.makeAPost}> Create Post </button>
            )}
              <button type="enable" onClick={this.enableLiveStream}> Live Stream </button>
              {this.state.makePost ? (<WallPostInputForm />):( <PostComponent className="postList" posts={this.state.posts} />)}
          </wall>
          )}
      </div>
    );
  }


  refreshPage() {
    window.location.reload();
  }

  handlePostTypeChange(event) {
    this.setState({ postType: event.target.value });
  }

  handleSubmit(event) {
    event.preventDefault();
    this.setState({ liveStream: true }); 
    this.setState({ showWall: false });
  }
}

export default WallComponent;
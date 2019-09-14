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
            <button type="disable" onClick={this.disableLiveStream}>
              Display The Wall
            </button>
            ) : ( 
              <>
              {this.state.makePost ?( 
                  <button type="disablePost" onClick={this.hideForm}>
                    Hide Form
                  </button>
                  ):(
                  <button type="enablePost" onClick={this.makeAPost}>
                    Make a Post
                </button>
                  )}
              <button type="enable" onClick={this.enableLiveStream}>
                Display Video
              </button>
              </>
          )}
        
          
          {this.state.liveStream ? (
                <WebCamCapture /> 
              ) : ( 
                <>
                <h1>The Wall </h1>
                {this.state.makePost ? (<WallPostInputForm />):(false)}
                <div className="postList">
                <PostComponent posts={this.state.posts} />
                </div>
              </>
            )}

      </div>
    );
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
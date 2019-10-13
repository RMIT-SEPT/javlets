import React, { Component } from 'react';
import axios from 'axios';
import cookie from 'js-cookie';
import { API } from '../../Constants.js'

import PostComponent from './PostComponent';
import WebCamCapture from './../liveStream/webcam';
import WallPostInputForm from './WallPostInputForm';


class WallComponent extends Component{

  enableLiveStream = () => this.setState({ liveStream: true, makePost: false });
  disableLiveStream = () => this.setState({liveStream: false});
  makeAPost = () => this.setState({makePost: true});
  hideForm = () => this.setState({makePost: false});

  constructor(props) {
    super(props);
    this.state = {
      user: [],
      liveStream: false,
      makePost: false
    };
  }
  componentDidMount() {
    axios.get(API + '/auth/get/?id=' + cookie.get('id'))
      .then((response) => {
        this.setState({user: response.data});
    });
  }

  render(){
    console.log(this.state.user);
    return(
      <div className="body-item wall">
        
        {this.state.liveStream ? (
          <div>
            <h1>Live Stream</h1>
            <button type="disable" onClick={this.disableLiveStream}> Show Wall </button>
            <WebCamCapture />
          </div>
          ):(
          <div>
            <h1>The Wall</h1>
            {this.state.makePost ?( 
              <button type="disablePost" onClick={this.hideForm}> Show Wall </button>
            ):(
              <button type="enablePost" onClick={this.makeAPost}> Create Post </button>
            )}
              <button type="enable" onClick={this.enableLiveStream}> Show Live Stream </button>
              {this.state.makePost ? (<WallPostInputForm />):( <PostComponent className="postList" posts={this.state.posts} />)}
              
          </div>
          )}
      </div>
    );
  }


  refreshPage() {
    window.location.reload();
  }
}

export default WallComponent;
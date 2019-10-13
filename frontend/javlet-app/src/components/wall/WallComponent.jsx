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

    this.makeMentor = this.makeMentor.bind(this);
    this.makeStudent = this.makeStudent.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
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
            {this.state.user.mentor ?( 
              <button type="disablePost" onClick={this.makeStudent}> Show Student View </button>
            ):(
              <button type="enablePost" onClick={this.makeMentor}> Show Mentor View </button>
            )}
            <WebCamCapture />
          </div>
          ):(
          <div>
            <h1>The Wall </h1>
            {this.state.makePost ?( 
              <button type="disablePost" onClick={this.hideForm}> Show Wall </button>
            ):(
              <button type="enablePost" onClick={this.makeAPost}> Create Post </button>
            )}
              <button type="enable" onClick={this.enableLiveStream}> Show Live Stream </button>
              {this.state.user.mentor ?( 
              <button type="disablePost" onClick={this.makeStudent}> Show Student View </button>
            ):(
              <button type="enablePost" onClick={this.makeMentor}> Show Mentor View </button>
            )}
              {this.state.makePost ? (<WallPostInputForm />):( <PostComponent className="postList" posts={this.state.posts} />)}
              
          </div>
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

  makeMentor(){
    console.log(this.state.user)
    const promoted = {
      id:this.state.user.id
    }
    console.log(promoted)
    axios.post(API + '/auth/promote', promoted);
    window.location.reload();
  }

  makeStudent(){
    const demoted = {
      id:this.state.user.id
    }
    axios.post(API + '/auth/demote', demoted);
    window.location.reload();
  }

  handleSubmit(event) {
    event.preventDefault();
    this.setState({ liveStream: true }); 
    this.setState({ showWall: false });
  }
}

export default WallComponent;
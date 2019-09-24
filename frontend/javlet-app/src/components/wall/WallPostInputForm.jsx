import React, { Component } from 'react'
import axios from 'axios';
import cookie from 'js-cookie';

class WallPostInputForm extends Component {
    constructor(props) {
      super(props);
      this.state = {
        user: [],
        newPost: '', 
        postType: '',
        title: '', 
        body: '',
        id: 0
      };  
      this.handlePostTypeChange = this.handlePostTypeChange.bind(this);
      this.handleTitleChange = this.handleTitleChange.bind(this);
      this.handleBodyChange = this.handleBodyChange.bind(this);
      // this.handleAuthorChange = this.handleAuthorChange.bind(this);
      this.handleSubmit = this.handleSubmit.bind(this);
    }
    componentDidMount() {
      if(cookie.get('id')){
        axios.get('http://javlet.social:8080/auth/get?id=' + cookie.get('id'))
        .then((response) => {
          this.setState({user: response.data});
        });
      }
    }
  
    render() {
      return (
        <div>          
          <form onSubmit={this.handleSubmit}>            
            <label> Title </label> <br />
            <input onChange={this.handleTitleChange} value={this.state.title}/>
            <br />
            
            <label> Body </label>
            <br />
            <input onChange={this.handleBodyChange} value={this.state.body} />
            <br />
            
            {/* <label> Author </label>
            <br />
            <input onChange={this.handleAuthorChange} value={this.state.value}/>
            <br /> */}

            <br />
            <input className="w3-radio" type="radio" value="Mentor"  name="formSelect" onClick={this.handlePostTypeChange} /> Posting as a Mentor <br />
            <input className="w3-radio" type="radio" value="Student" name="formSelect" onClick={this.handlePostTypeChange} /> Posting as a Student <br />
            <br />
            
            <input className="w3-btn w3-blue" type="submit" value="Submit" />
          </form>
        </div>
        
      );
    }
    
    handlePostTypeChange(event) {
        this.setState({ postType: event.target.value });
    }

    handleTitleChange(event) {
        this.setState({ title: event.target.value });
    }

    handleBodyChange(event) {
      this.setState({ body: event.target.value });
    }

    // handleAuthorChange(event) {
    //     this.setState({ author: this.state.user.givenName });
    // }
  
    handleSubmit(event) {
      // event.preventDefault();
      if (!this.state.title.length) {
        return;
      }
      const newItem = {
        postType: this.state.postType,
        title: this.state.title,
        body: this.state.body,
        author: this.state.user.givenName + " " + this.state.user.familyName,
        postId: Date.now(),
        userId: this.state.user.username
      };

      // window.location.reload();
      return axios.post('http://javlet.social:8080/wall/newPost', newItem);
      // return axios.post('http://localhost:8080/wall/newPost', newItem);
    }
  }
  

  export default WallPostInputForm;
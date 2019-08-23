import React, { Component } from 'react'
import axios from 'axios';

import PostComponent from './PostComponent'

class WallPostInputForm extends Component {
    constructor(props) {
      super(props);
      this.state = { 
          newPost: '', 
          postType: '',
          title: '', 
          body: '',
          author: '',
          id: 0
      };  
      this.handlePostTypeChange = this.handlePostTypeChange.bind(this);
      this.handleTitleChange = this.handleTitleChange.bind(this);
      this.handleBodyChange = this.handleBodyChange.bind(this);
      this.handleAuthorChange = this.handleAuthorChange.bind(this);
      this.handleSubmit = this.handleSubmit.bind(this);
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
            
            <label> Author </label>
            <br />
            <input onChange={this.handleAuthorChange} value={this.state.value}/>
            <br />

            <br />
            <input className="w3-radio" type="radio" value="Mentor"  name="formSelect" onClick={this.handlePostTypeChange} /> Posting as a Mentor <br />
            <input className="w3-radio" type="radio" value="Student" name="formSelect" onClick={this.handlePostTypeChange} /> Posting as a Student <br />
            <br />
            
            <input className="w3-btn w3-blue" type="submit" value="Submit" />
          </form>
          <br />
          <PostComponent posts={this.state.posts} />
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

    handleAuthorChange(event) {
        this.setState({ author: event.target.value });
    }
  
    handleSubmit(event) {
      event.preventDefault();
      
      if (!this.state.title.length) {
        return;
      }
      const newItem = {
        postType: this.state.postType,
        title: this.state.title,
        body: this.state.body,
        author: this.state.author,
        id: 1
      };

      axios.post('http://localhost:8080/wall/newPost', newItem);
    }
  }

  export default WallPostInputForm;
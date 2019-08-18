import React, { Component } from 'react'
import PostComponent from './PostComponent';

class WallPostInputForm extends Component {
    constructor(props) {
        super(props);
        this.state = {
            posts: [],
            body: '',
            postType: '',
            title: '',
            author:''
        };

        this.onPostTypeClick = this.onPostTypeClick.bind(this);
        this.handlePostTitleChange = this.handlePostTitleChange.bind(this);
        this.handleBodyChange = this.handleBodyChange.bind(this);
        this.handleAuthorChange = this.handleAuthorChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
  
    render() {
        return (
            <div>
            <form onSubmit={this.handleSubmit} >
                <input type="radio" value="Mentor" name="formSelect" onClick={this.onPostTypeClick} /> Posting as a Mentor <br />
                <input type="radio" value="Student" name="formSelect" onClick={this.onPostTypeClick} /> Posting as a Student <br />
                <br />
                <label>
                    Title of post
                    <br />
                    <input type="text" value={this.state.value} onChange={this.handlePostTitleChange} />
                </label>
                <br />
                <label>
                    What would you like to say 
                    <br />
                    <input type="textarea" value={this.state.value} onChange={this.handleBodyChange} />
                </label> 
                <br/>
                <label>
                    Author (eventually this will be auto populated by the user object)
                    <br />
                    <input type="text" value={this.state.value} onChange={this.handleAuthorChange} />
                </label>
                <br />
                <input type="submit" value="Submit" />
            </form>
            <br />
                <PostComponent title={this.state.title} body={this.state.body} 
                        author={this.state.author} postType={this.state.postType}/>
            </div>
            
        );
    }

    onPostTypeClick(event) {
        this.setState({postType: event.target.value});
    }

    handlePostTitleChange(event) {
        this.setState({title: event.target.value});
    }
  
    handleBodyChange(event) {
        this.setState({body: event.target.value});
    }
    handleAuthorChange(event) {
        this.setState({author: event.target.value});
    }
  
    handleSubmit(event) {
        // event.preventDefault();
        const newPost = {
            body: this.state.body,
            id: Date.now()
          };
          this.setState(state => ({
            posts: state.posts.concat(newPost),
            body: ''
          }));
    }
}

export default WallPostInputForm;
import React, { Component } from 'react'

class WallPostInputForm extends Component {
    constructor(props) {
      super(props);
      this.state = { 
          posts: [], 
          postType: '',
          title: '', 
          body: '',
          author: ''
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
            
            <label> Author (eventually this will be auto populated by the user object) </label>
            <br />
            <input  onChange={this.handleAuthorChange} value={this.state.value}/>
            <br />

            <br />
            <input type="radio" value="Mentor"  name="formSelect" onClick={this.handlePostTypeChange} /> Posting as a Mentor <br />
            <input type="radio" value="Student" name="formSelect" onClick={this.handlePostTypeChange} /> Posting as a Student <br />
            <br />
            
            <button> Submit </button>
          </form>
          <TodoList posts={this.state.posts} />
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
        id: Date.now()
      };

      this.setState(state => ({
        posts: state.posts.concat(newItem),
        postType: '',
        title: '',
        body: '',
        author: ''
      }));
    }
  }
  
  class TodoList extends React.Component {
    render() {
      return (
            <div className="post">                
                {this.props.posts.map(item => (
                    <div>
                    <h2 key={item.id}>{item.title}</h2>
                    <p key={item.id}>{item.body}</p>
                    <h4 key={item.id}> By {item.author} ({item.postType})</h4>
                    </div>
                ))}
        </div>
      );
    }
  }
  
  export default WallPostInputForm;
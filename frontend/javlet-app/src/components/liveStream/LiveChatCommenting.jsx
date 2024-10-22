import React, { Component } from 'react'

class LiveChatCommenting extends Component {
    constructor(props) {
      super(props);
      this.state = {
          posts: [],
          newPost: '',
          body: '',
          id: 0
      }; 
      this.handleBodyChange = this.handleBodyChange.bind(this);
      this.handleSubmit = this.handleSubmit.bind(this);
    }
  
    render() {
      return (
        <div>
          <div>        
            <form id="form" onSubmit={this.handleSubmit} >              
              <label> Comment </label>
              <br />
              <input onChange={this.handleBodyChange} value={this.state.body} />
              <input className="w3-btn w3-blue" type="submit" value="Submit" />
            </form>
          </div>

          <div>
            {this.state.posts.map(item => (  
              <div className="post" key={item.id}>
                <h2>{item.title}</h2>
                <p>{item.body}</p>
              </div>
            ))}
        </div>
      </div>
      );
    }

    handleTitleChange(event) {
        this.setState({ title: event.target.value });
    }

    handleBodyChange(event) {
      this.setState({ body: event.target.value });
    }
  
    handleSubmit(event) {
      event.preventDefault();
      if (!this.state.body.length) {
        return;
      }
      const newItem = {
        body: this.state.body,
        id: Date.now()
      };
      this.setState( {posts: this.state.posts.concat(newItem)})
      this.setState({title: "", body: ""});
      
    }
  }

  export default LiveChatCommenting;
import React, { Component } from 'react'

class ScheduleForm extends Component {
    constructor(props) {
      super(props);
      this.state = {
          posts: [],
          newPost: '',
          title: '', 
          body: '',
          id: 0
      };  
      this.handleTitleChange = this.handleTitleChange.bind(this);
      this.handleBodyChange = this.handleBodyChange.bind(this);
      this.handleSubmit = this.handleSubmit.bind(this);
    }
  
    render() {
      return (
        <div>
          <div>        
            <form id="form" onSubmit={this.handleSubmit} >  
              <h2>Enter Details of Your Stream</h2>        
              <label> Title </label> <br />
              <input onChange={this.handleTitleChange} value={this.state.title}/>
              <br />

              <label> Date/Time </label> <br />
              <input onChange={this.handleTitleChange} value={this.state.title}/>
              <br />
              
              <label> Details </label>
              <br />
              <input onChange={this.handleBodyChange} value={this.state.body} />
              <br />
              <input className="w3-btn w3-blue" type="submit" value="Submit" />
            </form>
          </div>

          <div>
            {this.state.posts.reverse().map(item => (  
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
      document.getElementById("form").reset();
      if (!this.state.title.length) {
        return;
      }
      const newItem = {
        title: this.state.title,
        body: this.state.body,
        id: Date.now()
      };
      this.setState( {posts: this.state.posts.concat(newItem)})
      this.setState({title: "", body: ""});
      
    }
  }

  export default ScheduleForm;
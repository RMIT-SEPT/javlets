import React, { Component } from 'react'
import axios from 'axios';
import cookie from 'js-cookie';
import { API } from '../../Constants.js'

class WallPostInputForm extends Component {
    constructor(props) {
      super(props)
      this.state = {
        user: [],
        posts: [],
        postType: '',
        title: '', 
        body: '',
        mentor: true,
        id: 0
      }; 

      this.handleTitleChange = this.handleTitleChange.bind(this);
      this.handleBodyChange = this.handleBodyChange.bind(this);
      this.handleSubmit = this.handleSubmit.bind(this);
    }
    componentDidMount() {
      if(cookie.get('id')){
        axios.get(API + '/auth/get/?id=' + cookie.get('id'))
        .then((response) => {
            this.setState({user: response.data});
        });
      }
    }
  
    render() {
      console.log(this.state.posts)
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
            
            <input className="w3-btn w3-blue" type="submit" value="Submit" />
            
          </form>
          <div>
            {this.state.posts.map(item => (  
              <div className="post" key={item.postId}>
                <h5>{item.selectDate}</h5>
                <h2>{item.title}</h2>
                <p>{item.body}</p>
                <h4> By {item.author} ({item.postType})</h4>
              </div>
            ))}
        </div>
        </div>
        
      );
    }

    handleTitleChange(event) {
        this.setState({ title: event.target.value });
        if(this.state.user.mentor === false){
          this.setState({ postType: 'Student' });
        }
        else{
          this.setState({ postType: 'Mentor' });
        }
    }

    handleBodyChange(event) {
      this.setState({ body: event.target.value });
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
        author: this.state.user.givenName + " " + this.state.user.familyName,
        postId: Date.now(),
        userId: this.state.user.id,
        category: 'wallpost',
        selectDate: ''
      };
      this.setState({title: '', body: ''})
      this.setState( {posts: this.state.posts.concat(newItem)})
      return axios.post(API + '/wall/newPost', newItem);
    }
  }
  
  export default WallPostInputForm;
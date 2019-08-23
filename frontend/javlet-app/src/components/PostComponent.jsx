import React, { Component } from 'react'
import axios from 'axios';

class PostComponent extends Component{
  constructor(props) {
    super(props)
    this.state = {
        posts: [],  
        error: ''
    };
    this.refreshWall = this.refreshWall.bind(this)
  }

  componentDidMount(){
    this.refreshWall();
  }

  refreshWall() {
      return axios
      .get(
        'http://localhost:8080/wall'
      )
      .then(result => {
        console.log(result);
        const posts = result.data.map(obj => ({type: obj.type, title: obj.title, body: obj.body, author: obj.authorAccount.username, id: obj.id}));
        this.setState({ posts });
      })
      .catch(error => {
        console.error("error: ", error);
        this.setState({
          error: `${error}`
        });
      });
  }

  render(){
    return(
     <div>   
        {this.state.posts.map(item => (
          <div className="post" key={item.id}>
              <h2>{item.title}</h2>
              <p>{item.body}</p>
              <h4> By {item.author} ({item.type})</h4>
          </div>
          ))}
        </div>
    
    );
  }

}

export default PostComponent;
import React, { Component } from 'react'

import axios from 'axios';

class PostComponent extends Component{
    
    state = {
        wallPosts: []
        
      }
      componentDidMount() {
        axios.get('http://localhost:8080/wall')
          .then(res => {
            const posts = res.data;
            this.setState({ posts });
          })
      }

render(){
   return(
    <div className="post">                
        {this.state.wallPosts.map(item => (
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

PostComponent.defaultProps = { title: 'title', postType: 'student', body: 'body', author: 'author' };


export default PostComponent;
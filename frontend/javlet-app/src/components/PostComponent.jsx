import React, { Component } from 'react'

class PostComponent extends Component{
    state = {  }

render(){
   return(
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

PostComponent.defaultProps = { title: 'title', postType: 'student', body: 'body', author: 'author' };


export default PostComponent;
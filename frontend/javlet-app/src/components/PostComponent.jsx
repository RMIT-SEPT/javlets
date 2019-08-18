import React, { Component } from 'react'

class PostComponent extends Component{
    state = {  }

render(){
   return(
    <div className="post">
    <h2>{this.props.title}</h2>
    <p>{this.props.body}</p>
    <h4>By {this.props.author}  ({this.props.postType})</h4>
  </div>
   );
}

}

PostComponent.defaultProps = { title: 'title', postType: 'student', body: 'body', author: 'author' };


export default PostComponent;
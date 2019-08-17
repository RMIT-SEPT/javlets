import React, { Component } from 'react'

class PostComponent extends Component{
    state = {  }

render(){
   return(
    <div className="post">
    <h2>{this.props.title}</h2>
    <p>{this.props.content}</p>
    <h4>By {this.props.name}</h4>
  </div>
   );
}

}

PostComponent.defaultProps = { title: "NULL", content: "NULL", name: "NULL" };


export default PostComponent;
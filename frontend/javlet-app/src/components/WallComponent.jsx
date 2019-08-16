import React, { Component } from 'react'
import PostComponent from './PostComponent';
import PostingComponent from './PostingComponent';

class WallComponent extends Component{
    state = {  }
render(){
   return(
    <div className="body-item wall">
      <h1>Wall</h1>
      <PostingComponent />
      <ul>
        <PostComponent />
        <br />
        <PostComponent />
        <br />
        <PostComponent />
        <br />
        <PostComponent />
        <br />
        <PostComponent />
        
      </ul>
    </div>
   );
}
}
export default WallComponent;
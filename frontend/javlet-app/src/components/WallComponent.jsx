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
        <PostComponent name="John" content="Hey" title="Looking for group" />
        <br />
        <PostComponent name="Lilly" content="Hello" title="Looking for assignment partner" />
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
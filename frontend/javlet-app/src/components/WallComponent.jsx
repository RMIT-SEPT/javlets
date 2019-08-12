import React, { Component } from 'react'
import PostComponent from './PostComponent';

class WallComponent extends Component{
    state = {  }
render(){
   return(
    <div className="body-item wall">
      <h1>Wall</h1>

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
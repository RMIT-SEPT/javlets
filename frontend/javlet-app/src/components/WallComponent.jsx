import React, { Component } from 'react'
import WallPostInputForm from './WallPostInputForm';
import PostComponent from './PostComponent'

class WallComponent extends Component{
    state = {  }
render(){
   return(
    <div className="body-item wall">
      <h1>The Wall</h1>
      <ul>
        <WallPostInputForm />
        <br />
        <PostComponent posts={this.state.posts} />
      </ul>
    </div>
   );
}
}
export default WallComponent;
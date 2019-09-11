import React, { Component } from 'react'
import WallPostInputForm from './WallPostInputForm';
import PostComponent from './PostComponent'

class WallComponent extends Component{
    state = {  }
render(){
   return(
    <div className="body-item wall">
      <h1>The Wall</h1>
        {/* <WallPostInputForm />
        <br /> */}
        <div className="postList">
        {/* <div> */}
        <PostComponent posts={this.state.posts} />
        </div>
    </div>
   );
}
}
export default WallComponent;
import React, { Component } from 'react'
import WallPostInputForm from './WallPostInputForm';
import TodoApp from './WallPostInputForm';

class WallComponent extends Component{
    state = {  }
render(){
   return(
    <div className="body-item wall">
      <h1>The Wall</h1>
      <ul>
        <WallPostInputForm />
        <br />
        {/* <PostComponent />
        <br />
        <PostComponent name="Lilly" content="Hello" title="Looking for assignment partner" />
        <br />
        <PostComponent />
        <br />
        <PostComponent />
        <br />
        <PostComponent /> */}
      </ul>
    </div>
   );
}
}
export default WallComponent;
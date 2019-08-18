import React, { Component } from 'react'
import WallPostInputForm from './WallPostInputForm';

class WallComponent extends Component{
    state = {  }
render(){
   return(
    <div className="body-item wall">
      <h1>The Wall</h1>
      <ul>
        <WallPostInputForm />
        <br />
      </ul>
    </div>
   );
}
}
export default WallComponent;
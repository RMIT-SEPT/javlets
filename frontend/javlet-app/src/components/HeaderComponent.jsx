import React, { Component } from 'react'

class HeaderComponent extends Component{
    state = {  }
render(){
   return(
    <React.Fragment>
    <h1 className="title">Javlets</h1>
    <p>The RMIT social media platform</p>
    </React.Fragment>
   );
}
}
export default HeaderComponent;
import React, { Component } from 'react'
import LoginandRegistration from './LoginandRegistration';

class HeaderComponent extends Component{
    state = {  }
render(){
   return(
    <React.Fragment>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <h1 class="navbar-brand title">Javlet</h1>
  <div>
    <ul class="navbar-nav">
      <li class="nav-item active">
          <h2>Insert inspiring slogan here</h2>
      </li>
    </ul>
  </div>
  <div>
    <LoginandRegistration />
  </div>
</nav>
    </React.Fragment>
   );
}
}
export default HeaderComponent;
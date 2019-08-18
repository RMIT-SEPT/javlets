import React, { Component } from 'react'
import LoginandRegistration from './LoginandRegistration';

class HeaderComponent extends Component{
    state = {  }
render(){
   return(
    <React.Fragment>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <h1 class="navbar-brand title">Javlet</h1>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
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
import React, { Component } from 'react'
import GAuthComponent from './GAuthComponent';

class HeaderComponent extends Component{
    state = {  }
render(){
   return(
    <React.Fragment>
<nav className="navbar navbar-expand-lg navbar-light bg-light">
  <h1 className="navbar-brand title">Javlet</h1>
  <div className="collapse navbar-collapse" id="navbarNavDropdown">
    <ul className="navbar-nav">
      <li className="nav-item active">
          <h2>Insert inspiring slogan here</h2>
      </li>
    </ul>
  </div>
  <div>
    <GAuthComponent />
  </div>
</nav>
    </React.Fragment>
   );
}
}
export default HeaderComponent;
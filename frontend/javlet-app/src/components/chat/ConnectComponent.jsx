import React, { Component } from 'react'

class ConnectComponent extends Component{

    state = {  }
    
render(){

    let button;
        if (this.props.type === 1) {
          button = <button className="w3-btn w3-green btn-primary btn-lg btn-block" type="button" form="form1" value="Add">Add</button>;
        }else{
            button = <button className="w3-btn w3-blue btn-primary btn-lg btn-block" type="button" form="form1" value="Message">Message</button>;
        }

   return(
    <div className="connection">
        <h5>John Doe</h5>
        <img className='bg' alt="" src={'https://loremflickr.com/100/100/person'} />
        {button}
    </div>
   );
}
}
export default ConnectComponent;
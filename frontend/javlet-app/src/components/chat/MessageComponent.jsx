import React, { Component } from 'react'

class MessageComponent extends Component{
    
    constructor(props) {
        super(props);
        this.state = {
            app: require('express')(),
            http: require('http').createServer(this.app),
            io: require('socket.io')(this.http)
        };
        this.submitForm = this.submitForm.bind(this);
        
    }

    render(){
        return(
            <React.Fragment>
                <ul id="messages"></ul>
                <form action="">
                <input id="m" autocomplete="off" /><button onClick={this.submitForm}>Send</button>
                </form>
            </React.Fragment>
        );
    }

    submitForm() {
        console.log("I'm here\n");
    }
 
}
export default MessageComponent;




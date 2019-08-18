import React, { Component } from 'react'

class AccessForm extends Component {
    constructor(props) {
        super(props);
        this.state = {
            name: '', 
            email:'',
            loginMethod:''
        };

        this.onRegistrationClick = this.onRegistrationClick.bind(this);
        this.onLoginClick = this.onLoginClick.bind(this);
        this.handleNameChange = this.handleNameChange.bind(this);
        this.handleEmailChange = this.handleEmailChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    onRegistrationClick(event) {
        this.setState({loginMethod: event.target.value});
    }

    onLoginClick(event) {
        this.setState({loginMethod: event.target.value});
    }
  
    handleNameChange(event) {
        this.setState({name: event.target.value});
    }
    handleEmailChange(event) {
        this.setState({email: event.target.value});
    }
  
    handleSubmit(event) {
        // alert('Users details to be saved: \nName: ' + this.state.name + '\nEmail: ' + this.state.email
        //         + '\nLogin Type: ' + this.state.loginMethod);
        alert('Email: ' + this.state.email);
        // event.preventDefault();
    }
  
    render() {
        return (
            <form onSubmit={this.handleSubmit} class="form-group form-control-lg">
                {/* <input type="radio" value="New User" name="formSelect" onClick={this.onRegistrationClick} /> New User Registration <br />
                <input type="radio" value="Returning User" name="formSelect" onClick={this.onLoginClick} /> Returning User <br /> */}
                {/* <label> */}
                    {/* Name: 
                    <input type="text" value={this.state.value} onChange={this.handleNameChange} /> */}
                {/* </label> */}
                {/* <br /> */}
                <label>
                    Enter an RMIT email:
                    <input type="email" value={this.state.value} onChange={this.handleEmailChange} />
                </label>
                <input type="submit" value="Submit" />
            </form>
        );
    }
}

export default AccessForm;
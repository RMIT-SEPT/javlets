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
        
    }
  
    render() {
        return (
            <form onSubmit={this.handleSubmit} className="form-group form-control-lg">
                <label>
                    Enter an RMIT email:
                    <input className="w3-input" type="email" value={this.state.value} onChange={this.handleEmailChange} />
                </label>
                <input className="w3-btn w3-blue-grey" type="submit" value="Login" />
            </form>
        );
    }
}

export default AccessForm;
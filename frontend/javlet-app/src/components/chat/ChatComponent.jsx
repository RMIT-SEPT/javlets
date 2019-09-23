import React, { Component } from "react";
import ConnectionListComponent from "./ConnectionListComponent";
import Stomp from 'webstomp-client';
import MessageComponent from "./MessageComponent";

class ChatComponent extends Component {

  constructor(props) {
    super(props);
    this.state = {
      messages: [],
      error: "",

      msg: "",
      sender: "John",
      recipient: "Jill",
      id: 0
    };

    this.handleSubmit = this.handleSubmit.bind(this);
  }
  
  
  componentDidMount() {
    console.log('Component did mount');


    this.client = Stomp.over(new WebSocket("ws://javlet.social:8080/socket/websocket"));

    this.client.connect({ login: null, passcode: null }, () => {
      console.log("Connected");

      this.client.subscribe('/chat', message => {
        this.addMessage(message.body);
    });


  });
  }

  render() {
    return (
      <div className="body-item">
        <h1>Chat</h1>
        <div className="innerChat">
          <div className="innerChatChild">
            <h2>Your connections</h2>
            <ConnectionListComponent type={0} />
            <h2>Add connections</h2>
            <ConnectionListComponent type={1} />
          </div>

          <div className="innerChatChild">
            <h2>Message</h2>
            <div id="dialogue-page" className="chatArea">
              <div className="dialogue-container">
                <ul id="messageList">
                {this.state.messages.slice(0).reverse().map((message, index) =>
          <MessageComponent message={message} sender="user"/> // {item.sender.username}
        )}
                </ul>
                    <div className="input-group clearfix">
                    <form
                      action="."
                      onSubmit={e => {
                        e.preventDefault()
                        this.setState({ message: e.target.value })
                        this.handleSubmit(e)
                        this.setState({ message: '' })
                      }}
                      >
                      <input
                        type="text"
                        id="chatMessage"
                        placeholder={'Enter message...'}
                        value={this.state.message}
                        onChange={e => this.setState({ message: e.target.value })}
                        className="w3-input form-control"
                      />
                      <input type="submit"
                       className="w3-btn w3-blue" 
                       value={'Send'} />
                    </form>
                      
                  </div>
              </div>
            </div>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
            <script src="/js/script.js"></script>
          </div>
        </div>
      </div>
    );
  }

  handleSubmit(event) {
    var msg = this.state.message;
        if(msg !== ""){
          
    const newItem = {
      msg: msg,
      sender: this.state.sender,
      recipient: this.state.recipient,
      id: Date.now()
    };

    console.log(JSON.stringify(newItem));

    this.client.send('/app/message', JSON.stringify(newItem));
    }
  }

  addMessage = message =>
  this.setState(state => ({ messages: [message, ...state.messages] }))
}
export default ChatComponent;

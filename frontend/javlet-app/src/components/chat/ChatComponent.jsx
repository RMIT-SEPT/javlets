import React, { Component } from "react";
import ConnectionListComponent from "./ConnectionListComponent";
import Stomp from 'webstomp-client';
import MessageComponent from "./MessageComponent";
import cookie from 'js-cookie';
import API from "../../Constants.js"

class ChatComponent extends Component {

  constructor(props) {
    super(props);
    this.state = {
      messages: [],

      msg: "",
      sender: "John",
      recipient: "Jill",
    };

    this.handleSubmit = this.handleSubmit.bind(this);
  }
  
  
  componentDidMount() {
    console.log('Component did mount');

    this.client = Stomp.over(new WebSocket(
	"ws://" + API.slice(7) + "/socket/websocket"
));

    this.client.connect({ login: null, passcode: null }, () => {
      console.log("Connected");

      this.client.subscribe('/chat', response => {
        console.log(response);
        this.setState(state => ({ messages: [JSON.parse(response.body), ...state.messages] }))
    });
  });
  }

  scrollToBottom() {
    const scrollHeight = this.messageList.scrollHeight;
    const height = this.messageList.clientHeight;
    const maxScrollTop = scrollHeight - height;
    this.messageList.scrollTop = maxScrollTop > 0 ? maxScrollTop : 0;
  }
  
  componentDidUpdate() {
    this.scrollToBottom();
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
            <h2>Javlets Conversation</h2>
            <div id="dialogue-page" className="chatArea">
              <div className="dialogue-container">
                <ul id="messageList" ref={(div) => {this.messageList = div;}}>
                {this.state.messages.slice(0).reverse().map((message, index) =>
          <MessageComponent message={message.messageContent} datetime={message.dateTime} sender={message.sender.givenName}/>
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
                        autoComplete="off"
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
      messageContent: msg,
      senderId: cookie.get('id'),
      recipientId: this.state.recipient,
    };

    console.log(JSON.stringify(newItem));

    this.client.send('/app/message', JSON.stringify(newItem));
    }
  }

}
export default ChatComponent;

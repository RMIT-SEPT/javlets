import React, { Component } from "react";
import ConnectionListComponent from "./ConnectionListComponent";
import axios from "axios";
import MessageComponent from "./MessageComponent";

class ChatComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      messages: [],
      error: "",

      messageContent: "",
      sender: "",
      recipient: "",
      id: 0
    };
    this.handleBodyChange = this.handleBodyChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  test() {
    return axios
      .get("http://localhost:8080/chat")
      .then(result => {
        console.log(result);
        const messages = result.data.map(obj => ({
          messageContent: obj.messageContent,
          sender: obj.sender,
          recipient: obj.recipient
        }));
        this.setState({ messages });
      })
      .catch(error => {
        console.error("error: ", error);
        this.setState({
          error: `${error}`
        });
      });
  }

  componentDidMount() {
    console.log("I RAN");
    this.test();
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
            {this.handleSubmit}
          </div>

          <div className="innerChatChild">
            <h2>Message</h2>
            <div id="dialogue-page" className="chatArea">
              <div className="dialogue-container">
                <ul id="messageList">
                  {this.state.messages.map(item => (
                    <MessageComponent message={item.messageContent} sender={item.sender.username}/>
                  ))}
                </ul>
                <form
                  id="dialogueForm"
                  onSubmit={this.handleSubmit}
                  name="dialogueForm"
                  nameForm="dialogueForm"
                >
                    <div className="input-group clearfix">
                      <input
                        className="w3-input form-control"
                        type="text"
                        id="chatMessage"
                        placeholder="Enter a message...."
                        autoComplete="off"
                        onChange={this.handleBodyChange}
                      />
                      <input
                        className="w3-btn w3-blue"
                        type="submit"
                        value="Send"
                      />
                  </div>
                </form>
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

  handleBodyChange(event) {
    this.setState({ body: event.target.value });
  }

  handleSubmit(event) {
    const newItem = {
      body: this.state.body,
      from: this.state.from,
      to: this.state.to,
      id: Date.now()
    };

    console.log("handleSubmitted");

    return axios.post("http://localhost:8080/chat/newMessage", newItem);
  }
}
export default ChatComponent;

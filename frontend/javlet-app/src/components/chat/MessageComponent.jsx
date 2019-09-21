import React, { Component } from 'react'
import SockJS from "sockjs-client"
import Stomp from "@stomp/stompjs"

class MessageComponent extends Component{
    state = {  }

    constructor(props) {
        super(props);
        this.state = {
            usernamePage: document.querySelector('#username-page'),
            chatPage: document.querySelector('#chat-page'),
            usernameForm: document.querySelector('#usernameForm'),
            messageForm: document.querySelector('#messageForm'),
            messageInput: document.querySelector('#message'),
            messageArea: document.querySelector('#messageArea'),
            connectingElement: document.querySelector('.connecting'),
            
            stompClient: null,
            username: null,
            
            colors: [
                '#2196F3', '#32c787', '#00BCD4', '#ff5652',
                '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
            ]
        };  
      }

render(){
   return(
    <React.Fragment>
    <div id="username-page">
        <div class="username-page-container">
            <h1 class="title">Type your username</h1>
            <form id="usernameForm" name="usernameForm">
                <div class="form-group">
                    <input type="text" id="name" placeholder="Username" autocomplete="off" class="form-control" />
                </div>
                <div class="form-group">
                    <button type="submit" class="accent username-submit">Start Chatting</button>
                </div>
            </form>
        </div>
    </div>

    <div id="chat-page" class="hidden">
        <div class="chat-container">
            <div class="chat-header">
                <h2>Spring WebSocket Chat Demo</h2>
            </div>
            <div class="connecting">
                Connecting...
            </div>
            <ul id="messageArea">

            </ul>
            <form id="messageForm" name="messageForm">
                <div class="form-group">
                    <div class="input-group clearfix">
                        <input type="text" id="message" placeholder="Type a message..." autocomplete="off" class="form-control"/>
                        <button type="submit" class="primary">Send</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="/js/main.js"></script>
    </React.Fragment>
   );
}

connect(event) {
    this.username = document.querySelector('#name').value.trim();

    if(this.username) {
        this.usernamePage.classList.add('hidden');
        this.chatPage.classList.remove('hidden');

        var socket = new SockJS('/javlets');
        this.stompClient = Stomp.over(socket);

        this.stompClient.connect({}, this.onConnected, this.onError);
    }
    event.preventDefault();
}


onConnected() {
    // Subscribe to the Public Topic
    this.stompClient.subscribe('/topic/public', this.onMessageReceived);

    // Tell your username to the server
    this.stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({sender: this.username, type: 'JOIN'})
    )

    this.connectingElement.classList.add('hidden');
}


onError(error) {
    this.connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    this.connectingElement.style.color = 'red';
}


sendMessage(event) {
    var messageContent = this.messageInput.value.trim();
    if(messageContent && this.stompClient) {
        var chatMessage = {
            sender: this.username,
            content: this.messageInput.value,
            type: 'CHAT'
        };
        this.stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        this.messageInput.value = '';
    }
    event.preventDefault();
}


onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');

    if(message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
    } else {
        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = this.getAvatarColor(message.sender);

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    this.messageArea.appendChild(messageElement);
    this.messageArea.scrollTop = this.messageArea.scrollHeight;
}


getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }
    var index = Math.abs(hash % this.colors.length);
    return this.colors[index];
}
componentWillMount(){
    this.usernameForm.addEventListener('submit', this.connect, true)
    this.messageForm.addEventListener('submit', this.sendMessage, true)
}
 
}
export default MessageComponent;




import React, { Component } from 'react'
import ConnectionListComponent from './ConnectionListComponent';

class ChatComponent extends Component{
    state = {  }
render(){
   return(
    <div className="body-item chat">
      <h1>Chat</h1>

      <h2>Your connections</h2>
      <ConnectionListComponent type={0} />
      <h2>Add connections</h2>
      <ConnectionListComponent type={1} />
      <h2>Message</h2>


      <div id="dialogue-page" class="hidden">
		<div class="dialogue-container">
			<ul id="messageList">

			</ul>
			<form id="dialogueForm" name="dialogueForm" nameForm="dialogueForm">
				<div class="form-group">
					<div class="input-group clearfix">
						<input className="w3-input w3-animate-input" type="text" id="chatMessage"
							placeholder="Enter a message...." autocomplete="off"
							class="form-control" />
                <input class="w3-btn w3-blue" type="submit" value="Send" />
					</div>
				</div>
			</form>
		</div>
	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
	<script src="/js/script.js"></script>


    </div>
   );
}
}
export default ChatComponent;
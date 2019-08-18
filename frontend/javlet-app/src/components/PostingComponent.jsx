import React, { Component } from 'react'

class PostingComponent extends Component{
    state = {  }
render(){
   return(
<form method="post" action="">
<div class="input-group">
<input class="form-control" type="text" name="content" placeholder="Make a post..."/>
            <span class="input-group-btn">
              <button class="btn btn-success" type="submit" name="post">Post</button>
            </span>
          </div>
</form>
   );
}
}
export default PostingComponent;
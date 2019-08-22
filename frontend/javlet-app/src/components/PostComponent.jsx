import React, { Component } from 'react'
import axios from 'axios';

class PostComponent extends Component{
  constructor(props) {
    super(props)
    this.state = {
        wallPosts: [],   
        // postType: '',
        // title: '', 
        // body: '',
        // author: '',
        // id: 0,
        // test: "h33"
    };
    this.refreshWall = this.refreshWall.bind(this)
  }

  componentDidMount() {
    this.refreshWall();
  }

  refreshWall() {
    axios.get('http://localhost:8080/wall')
      .then(response => {
        this.setState({ wallPosts: response.data })
      })
  }

  render(){
    return(
      <div className="post">   
        <p>{this.state.wallPosts.length}</p>
        {/* <p>{this.state.test}</p> */}

          {/* {this.state.wallPosts.map(item => (
              <div>
              <h2 key={item.id}>{item.title}</h2>
              <p key={item.id}>{item.body}</p>
              <h4 key={item.id}> By {item.author} ({item.postType})</h4>
              </div>
          ))} */}
        </div>
    
    );
  }

}

export default PostComponent;
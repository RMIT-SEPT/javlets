import React, { Component } from 'react'
import axios from 'axios';

class ScheduledLiveStream extends Component{
  constructor(props) {
    super(props)
    this.state = {
      allPosts: [],
      livestreamPosts: [],
      postType: 'allPosts',
      error: ''
    };
    this.refreshWall = this.refreshWall.bind(this)
  }

  componentDidMount(){
    this.refreshWall();
  }

  refreshWall() {
      return axios
      .get(
        // 'http://javlet.social:8080/wall'
        'http://localhost:8080/wall'
      )
      .then(result => {
          const allPosts = result.data.reverse().map(obj => ({type: obj.type, title: obj.title, body: obj.body, author: obj.msgAuthor, id: obj.postId, category: obj.category, selectDate: obj.selectDate}));
          
          const livestreamPosts = allPosts.filter(function(category){
            return category.category === "livestream";
          })
          this.setState({ allPosts });
          this.setState({ livestreamPosts });
      })
      .catch(error => {
        this.setState({
          error: `${error}`
        });
      });
  }

  render(){
    return(
      <div>
        {this.state.livestreamPosts.map(item => (  
          <div className="post" key={item.id}>
            <h5>{item.selectDate}</h5>
            <h2>{item.title}</h2>
            <p>{item.body}</p>
            <h4> By {item.author} ({item.type})</h4>
          </div>
        ))}
      </div>
    );
  }
}

export default ScheduledLiveStream;
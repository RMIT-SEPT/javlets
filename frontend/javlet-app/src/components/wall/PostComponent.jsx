import React, { Component } from 'react'
import axios from 'axios';
import WallPostInputForm from './WallPostInputForm';

class PostComponent extends Component{
  constructor(props) {
    super(props)
    this.state = {
        allPosts: [],
        mentorPosts: [],
        studentPosts: [],
        postType: 'allPosts',
        error: ''
    };
    this.handlePostTypeChange = this.handlePostTypeChange.bind(this);
    this.refreshWall = this.refreshWall.bind(this)
  }

  componentDidMount(){
    this.refreshWall();
  }

  refreshWall() {
      return axios
      .get(
        'http://localhost:8080/wall'
      )
      .then(result => {
          const allPosts = result.data.reverse().map(obj => ({type: obj.type, title: obj.title, body: obj.body, author: obj.authorAccount.username, id: obj.id}));
          const mentorPosts = allPosts.filter(function(type){
            return type.type === "Mentor";
          })
          const studentPosts = allPosts.filter(function(type){
            return type.type === "Student";
          })
          // console.log("!!!")
          // console.log(studentPosts)
          this.setState({ allPosts });
          this.setState({ mentorPosts });
          this.setState({ studentPosts });
      })
      .catch(error => {
        this.setState({
          error: `${error}`
        });
      });
      
  }

  render(){
    var displayPosts = this.state.allPosts;
    if(this.state.postType==="Mentor"){
      displayPosts = this.state.mentorPosts;
    }
    else if(this.state.postType==="Student"){
      displayPosts = this.state.studentPosts;
    }
    return(
      <div>
        <div>
          <WallPostInputForm />
          <h2>Filter Posts:</h2>
          <input className="w3-radio" type="radio" value="Mentor"   name="formSelect" onClick={this.handlePostTypeChange} /> Display Mentor Posts <br />
          <input className="w3-radio" type="radio" value="Student"  name="formSelect" onClick={this.handlePostTypeChange} /> Display Student Posts<br />
          <input className="w3-radio" type="radio" value="All"      name="formSelect" onClick={this.handlePostTypeChange} /> Display All Posts<br />
          <br />
        </div>
        <div>
          {displayPosts.map(item => (  
            <div className="post" key={item.id}>
                <h2>{item.title}</h2>
                <p>{item.body}</p>
                <h4> By {item.author} ({item.type})</h4>
            </div>
          ))}
        </div>
      </div>
    );
  }

  handlePostTypeChange(event) {
    this.setState({ postType: event.target.value });
    // console.log(this.state.mentorPosts)
  }
}

export default PostComponent;
import React, { Component } from 'react'
import DatePicker from "react-datepicker";
import axios from 'axios';
import cookie from 'js-cookie';
 
import "react-datepicker/dist/react-datepicker.css";

class ScheduleForm extends Component {
    constructor(props) {
      super(props);
      this.state = {
        user: [],
        posts: [],
        newPost: '',
        title: '', 
        newDate: new Date(),
        body: '',
        id: 0
      };  
      this.handleTitleChange = this.handleTitleChange.bind(this);
      this.handleDateChange = this.handleDateChange.bind(this);
      this.handleBodyChange = this.handleBodyChange.bind(this);
      this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount() {
      if(cookie.get('id')){
        // axios.get('http://javlet.social:8080/auth/get?id=' + cookie.get('id'))
        axios.get('http://localhost:8080/auth/get/' + cookie.get('studentId'))
        .then((response) => {
            this.setState({user: response.data});
        });
      }
    }
  
    render() {
      return (
        <div>
          <div>        
            <form id="form" onSubmit={this.handleSubmit} >  
              <h2>Enter Details of Your Stream</h2>        
              <label> Title </label> <br />
              <input onChange={this.handleTitleChange} value={this.state.title}/>
              <br />

              <label> Date/Time </label> <br />
              <DatePicker selected={this.state.newDate} onChange={this.handleDateChange} showTimeSelect dateFormat="Pp" />
              <br />
              
              <label> Details </label>
              <br />
              <input onChange={this.handleBodyChange} value={this.state.body} />
              <br />
              <input className="w3-btn w3-blue" type="submit" value="Submit" />
            </form>
          </div>

          <div>
            {this.state.posts.reverse().map(item => (  
              <div className="post" key={item.postId}>
                <h5>{item.selectDate}</h5>
                <h2>{item.title}</h2>
                <p>{item.body}</p>
                <h4> By {item.author} ({item.postType})</h4>
              </div>
            ))}
        </div>
      </div>
      );
    }

    handleTitleChange(event) {
        this.setState({ title: event.target.value });
    }

    handleDateChange = date => {
      this.setState({
        newDate: date
      });
    };

    handleBodyChange(event) {
      this.setState({ body: event.target.value });
    }
  
    handleSubmit(event) {
      event.preventDefault();
      if (!this.state.title.length) {
        return;
      }
      const newItem = {
        title: this.state.title,
        selectDate: "Livestream scheduled for: " + this.state.newDate.toString(),
        body: this.state.body,
        author: this.state.user.givenName + " " + this.state.user.familyName,
        postId: Date.now(),
        userId: this.state.user.username,
        postType: 'Mentor',
        category: 'livestream'
      };
      this.setState( {posts: this.state.posts.concat(newItem)})
      this.setState({title: "", newDate: new Date(), body: ""});
      // return axios.post('http://javlet.social:8080/wall/newPost', newItem);
      return axios.post('http://localhost:8080/wall/newPost', newItem);
    }
  }

  export default ScheduleForm;
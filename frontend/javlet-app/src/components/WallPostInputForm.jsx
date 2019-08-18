import React, { Component } from 'react'

class WallPostInputForm extends Component {
    constructor(props) {
        super(props);
        this.state = {
            postType: '',
            title: '', 
            body:'',
            author:''
        };

        this.onPostTypeClick = this.onPostTypeClick.bind(this);
        this.handlePostTitleChange = this.handlePostTitleChange.bind(this);
        this.handleBodyChange = this.handleBodyChange.bind(this);
        this.handleAuthorChange = this.handleAuthorChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    onPostTypeClick(event) {
        this.setState({postType: event.target.value});
    }

    handlePostTitleChange(event) {
        this.setState({title: event.target.value});
    }
  
    handleBodyChange(event) {
        this.setState({body: event.target.value});
    }
    handleAuthorChange(event) {
        this.setState({author: event.target.value});
    }
  
    handleSubmit(event) {
        alert('Post to Wall: \nType: ' + this.state.postType + '\nTitle: ' + this.state.title
                + '\nMessage: ' + this.state.body + '\nAuthor: ' + this.state.author);
        // event.preventDefault();
    }
  
    render() {
        return (
            <form onSubmit={this.handleSubmit} >
                <input type="radio" value="mentor" name="formSelect" onClick={this.onPostTypeClick} /> Posting as a Mentor <br />
                <input type="radio" value="student" name="formSelect" onClick={this.onPostTypeClick} /> Posting as a Student <br />
                <br />
                <label>
                    Title of post
                    <br />
                    <input type="text" value={this.state.value} onChange={this.handlePostTitleChange} />
                </label>
                <br />
                <label>
                    What would you like to say 
                    <br />
                    <input type="textarea" value={this.state.value} onChange={this.handleBodyChange} />
                </label> 
                <br/>
                <label>
                    Author
                    <br />
                    <input type="text" value={this.state.value} onChange={this.handleAuthorChange} />
                </label>
                <br />
                <input type="submit" value="Submit" />
            </form>
        );
    }
}

export default WallPostInputForm;


// modify below as a way to create posts to wall initially?
// class TodoApp extends React.Component {
//   constructor(props) {
//     super(props);
//     this.state = { items: [], text: '' };
//     this.handleChange = this.handleChange.bind(this);
//     this.handleSubmit = this.handleSubmit.bind(this);
//   }

//   render() {
//     return (
//       <div>
//         <h3>TODO</h3>
//         <TodoList items={this.state.items} />
//         <form onSubmit={this.handleSubmit}>
//           <label htmlFor="new-todo">
//             What needs to be done?
//           </label>
//           <input
//             id="new-todo"
//             onChange={this.handleChange}
//             value={this.state.text}
//           />
//           <button>
//             Add #{this.state.items.length + 1}
//           </button>
//         </form>
//       </div>
//     );
//   }

//   handleChange(e) {
//     this.setState({ text: e.target.value });
//   }

//   handleSubmit(e) {
//     e.preventDefault();
//     if (!this.state.text.length) {
//       return;
//     }
//     const newItem = {
//       text: this.state.text,
//       id: Date.now()
//     };
//     this.setState(state => ({
//       items: state.items.concat(newItem),
//       text: ''
//     }));
//   }
// }

// class TodoList extends React.Component {
//   render() {
//     return (
//       <ul>
//         {this.props.items.map(item => (
//           <li key={item.id}>{item.text}</li>
//         ))}
//       </ul>
//     );
//   }
// }

// ReactDOM.render(
//   <TodoApp />,
//   document.getElementById('todos-example')
// );
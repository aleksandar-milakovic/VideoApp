import React from "react";
import ReactDOM from "react-dom";
import { Route, Link, HashRouter as Router, Switch, Redirect, NavLink } from "react-router-dom";
import { Navbar, Nav, Container, Button } from "react-bootstrap";
import Home from "./components/Home";


import Login from './components/authorization/Login';
import NotFound from "./components/NotFound";
import {logout} from './services/auth';
import Register from './components/Register.js';
import Videos from "./components/Video/Videos";
import Video from "./components/Video/Video";
import VideoEdit from "./components/Video/VideoEdit";
import Users from "./Users/Users";
import User from "./Users/User";
import VideoAxios from "./apis/VideoAxios";
import VideoAdd from "./components/Video/VideoAdd";

class App extends React.Component {
  constructor(){
    super()
    this.state = {
      selectedMovie: ""
    }
  }

  goToUser(id) {
    this.props.history.push("/video/"+id);
  }

  render() {
    
    const jwt = window.localStorage['jwt'];

   
      return (
        <div>
          <Router>
            <Navbar expand bg="dark" variant="dark">
              <Navbar.Brand as={Link} to="/">
                  JWD
              </Navbar.Brand>
              <Nav>
               
                <Nav.Link style={{color:'red',fontSize:'35px'}} as={Link} to="/videos">
                  Videos
                </Nav.Link>

                {window.localStorage['role']=='ROLE_ADMIN'?
                <Nav.Link style={{color:'red',fontSize:'35px'}} as={Link} to="/users">
                  USERS
                </Nav.Link> :null}
                {window.localStorage['id']!=null?
                <Nav.Link style={{color:'white',fontSize:'35px'}} as={Link} to={"/user/"+window.localStorage['id']}>
                  Profile
                </Nav.Link> :null


                }
                
              { jwt!=null ?
                <div   style={{float:'right',paddingLeft:'1000px'}}><Nav.Link onClick={()=>logout()}>Logout</Nav.Link>
               </div>:
                <div    style={{float:'right',paddingLeft:'1000px'}}><Nav.Link as={Link} to="/login">Login</Nav.Link></div>}
               {jwt!=null ? null:<div  style={{float:'right',paddingLeft:'25px'}}><Nav.Link style={{float:'right'}} as={Link} to="/register">Register</Nav.Link></div>
  }</Nav>
            </Navbar>
            <Container style={{paddingTop:"25px"}}>
              <Switch>
                <Route exact path="/" component={Home} />
                <Route exact path="/login" component={Login} />
               <Route exact path="/register" component={Register}/>
               <Route exact path="/videos" component={Videos} />
               <Route exact path="/users" component={Users} />
               <Route exact path="/video/:id" component={Video} />
               <Route exact path="/videoAdd/:id" component={VideoAdd} />
               <Route exact path="/videoEdit/:id" component={VideoEdit} />
               <Route exact path="/user/:id" component={User} />
               
                <Route component={NotFound} />
              </Switch>
            </Container>
          </Router>
        </div>
      );
  

    
  }
}

ReactDOM.render(<App />, document.querySelector("#root"));

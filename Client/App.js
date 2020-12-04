import React, {useState} from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import Chessboard from './pages/chessground';
import "react-chessground/dist/styles/chessground.css";
import Chess from 'chess.js';

import Home from './pages/home'
import Login from './pages/login'
import Register from './pages/register'
import LoginLayout from "./pages/loginlayout";
import Profile from './pages/profile'

import useWindowSize from "./components/useWindowSize";
import {sendPostRequest} from "./components/API";



function App() {
    const {width, height} = useWindowSize();
    const [adminDashboard, setAdminDashboard] = useState(false);


    //sendPostRequest("newUser", {"user":"test0", "password":"test1", "email":"test2"}).then(r=>console.log(r.data))
    //sendPostRequest("move", {"from": "b2","to":"b4","match":"1"}).then(r=>console.log(r.data))
    return (
        <BrowserRouter>
            <div id='body' className="container mt-0" style={{ marginTop: 40}}>
            <Switch>
                <Route exact path="/">
                    <LoginLayout width={width} height={height}>
                        <Login setAdminDashboard={setAdminDashboard} />
                    </LoginLayout>
                </Route>
                <Route  path="/Home">
                    <Home width={width} height={height}/>
                </Route>
                <Route path="/Register">
                    <LoginLayout width={width} height={height} >
                        <Register />
                    </LoginLayout>
                </Route>
                <Route path="/Board">
                    <Chessboard/>
                </Route>
                <Route path='/Profile'>
                    <LoginLayout>
                        <Profile/>
                    </LoginLayout>
                </Route>
            </Switch>
            </div>
        </BrowserRouter>
    )
}




export default App;

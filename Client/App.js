import React, {useState} from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";

import Home from './pages/home'
import Login from './pages/login'
import Register from './pages/register'
import Board from './pages/Board'
import LoginLayout from "./pages/loginlayout";
import MoveValidation from "./MoveValidation";

import useWindowSize from "./components/useWindowSize";
import {sendPostRequest} from "./components/API";

function App() {
    const {width, height} = useWindowSize();
    const [adminDashboard, setAdminDashboard] = useState(false);
    sendPostRequest("move", {"hello": "world"}).then(r=>console.log(r.data))
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
                    <MoveValidation />
                </Route>
            </Switch>
            </div>
        </BrowserRouter>
    )
}

export default App;

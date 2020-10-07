import React from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";


import Login from './pages/login'

function App() {
    return (
        <BrowserRouter>
            <div id='body' className="container mt-2" style={{ marginTop: 40}}>
            <Switch>
                <Route exact path="/">
                    <Login />
                </Route>
            </Switch>
            </div>
        </BrowserRouter>
    )
}

export default App;

import React, {useState} from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";

import Home from './components/Home';
import Login from './components/Login';
import Register from './components/Register'
import LoginLayout from "./components/LoginLayout";
import Profile from './components/Profile'
import ProfileLayout from "./components/profilelayout";

import useWindowSize from "./hooks/useWindowSize";

import {createMuiTheme, ThemeProvider} from '@material-ui/core/styles';
import CssBaseline from "@material-ui/core/CssBaseline";

import {SnackbarProvider, useSnackbar} from 'notistack';

import "react-chessground/dist/styles/chessground.css";
import 'bootstrap/dist/css/bootstrap.min.css'

import { useHistory } from "react-router";
import Invitations from "./components/Invitations";
import ContinueGame from "./components/ContinueGame"

const Router = props => {

    const {width, height} = useWindowSize();
    const history = useHistory();

    const [userData, setUserData] = useState({});

    return (
        <Switch>
            <Route exact path="/">
                <LoginLayout width={width} height={height}>
                    <Login history={history} setUserData={setUserData} {...props}/>
                </LoginLayout>
            </Route>
            <Route path="/home">
                <Home width={width} height={height} history={history} {...props} userData={userData} />
            </Route>
            <Route path="/register">
                <LoginLayout width={width} height={height}>
                    <Register history={history} {...props}/>
                </LoginLayout>
            </Route>
            <Route path='/profile'>
                <ProfileLayout>
                    <Profile width={width} height={height} history={history} userData={userData} {...props} />
                </ProfileLayout>
            </Route>
        </Switch>
    )
}

const LoadApp = () =>
{
    const {enqueueSnackbar} = useSnackbar();
    const produceSnackBar = (message, variant = "error") => enqueueSnackbar(message, {variant: variant});

    return (
        <BrowserRouter>
            <Router produceSnackBar={produceSnackBar}/>
        </BrowserRouter>
    );
};

const App = () =>
{
    const theme = createMuiTheme({ palette: { primary: { main: '#0B8AAD' }}});

    return (
        <ThemeProvider theme={theme}>
            <CssBaseline/>
            <SnackbarProvider maxSnack={3} preventDuplicate>
                <LoadApp/>
            </SnackbarProvider>
        </ThemeProvider>
    );
};

export default App;

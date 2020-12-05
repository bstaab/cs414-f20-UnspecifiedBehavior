import React from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";

import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register'
import LoginLayout from "./pages/loginlayout";
import Profile from './pages/profile'
import ProfileLayout from "./pages/profilelayout";

import useWindowSize from "./components/useWindowSize";

import {createMuiTheme, ThemeProvider} from '@material-ui/core/styles';
import CssBaseline from "@material-ui/core/CssBaseline";

import {SnackbarProvider, useSnackbar} from 'notistack';

import "react-chessground/dist/styles/chessground.css";
import 'bootstrap/dist/css/bootstrap.min.css'

import {sendPostRequest} from "./components/API";
import { useHistory } from "react-router";

const Router = props => {

    const {width, height} = useWindowSize();
    const history = useHistory();

    return (
        <Switch>
            <Route exact path="/">
                <LoginLayout width={width} height={height}>
                    <Login history={history} {...props}/>
                </LoginLayout>
            </Route>
            <Route  path="/Home">
                <Home width={width} height={height} history={history}/>
            </Route>
            <Route path="/Register">
                <LoginLayout width={width} height={height}>
                    <Register history={history} {...props}/>
                </LoginLayout>
            </Route>
            <Route path='/Profile'>
                <ProfileLayout>
                    <Profile width={width} height={height} history={history} {...props}/>
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
    const theme = createMuiTheme({
        palette: {
            primary: {main: '#2BC903'},
            secondary: {main: '#0B8AAD'}
        }
    });

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

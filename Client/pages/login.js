import React, {useState} from 'react';

import {Button, Grid, InputAdornment, TextField, Typography} from "@material-ui/core";

import PersonIcon from '@material-ui/icons/Person';
import LockIcon from '@material-ui/icons/Lock';

import '../static/css/login.scss';

import {sendPostRequest} from "../components/API";
import Chessground from "react-chessground";


const Login = props => {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    function login(username, password) {
        props.setUserData(username);
        props.setUserPassword(password);
        sendPostRequest("login", {"username" : username, "password": password})
            .then(r => {
                let validLogin = r.data.valid;
                if (validLogin) props.history.push('home');
                else props.produceSnackBar("Login Failed", "error");
            });
    }

    return (
        <Grid
            container direction={"column"} justify={"center"} alignItems={"center"} alignContent={"center"}
            spacing={8}
        >
            <Grid item>
                <Typography variant={"h4"} align={"center"}>Welcome to Portal Chess</Typography>
            </Grid>
            <LoginFields
                username={username} setUsername={setUsername}
                password={password} setPassword={setPassword}
                login={login} {...props}
            />
            <Grid item container justify={"center"} alignItems={"center"} alignContent={"center"}>
                <Grid item style={{width: "80%"}} align={"center"}>
                    <Button
                        color={"primary"} variant={"contained"} style={{width: "80%", height: "50px"}}
                        onClick={() => {login(username, password)}}
                    >
                        Login
                    </Button>
                </Grid>
            </Grid>
        </Grid>
    )
}

const LoginFields = props => {
     return(
        <Grid
            item container direction={"column"} justify={"center"} alignItems={"center"} alignContent={"center"}
            spacing={3} style={{width: '90%'}}
        >
            <Grid item style={{width: '100%'}}>
                <TextField
                    fullWidth color={"primary"} variant={"outlined"}
                    autoComplete={"username"} id={'username'}
                    label={"Username"} value={props.username} onChange={(e) => props.setUsername(e.target.value)}
                    onKeyDown={(e) =>
                    {
                        if(e.key === 'Enter') props.login();
                    }}
                    InputProps={{
                        startAdornment: (
                            <InputAdornment position="start">
                                <PersonIcon />
                            </InputAdornment>
                        ),
                    }}
                />
            </Grid>
            <Grid item style={{width: '100%'}}>
                <TextField
                    fullWidth color={"primary"} variant={"outlined"}
                    type={"password"} autoComplete={"current-password"} id={'password'}
                    label={"Password"} value={props.password} onChange={(e) => props.setPassword(e.target.value)}
                    onKeyDown={(e) =>
                    {
                        if(e.key === 'Enter') props.login();
                    }}
                    InputProps={{
                        startAdornment: (
                            <InputAdornment position="start">
                                <LockIcon />
                            </InputAdornment>
                        ),
                    }}
                />
            </Grid>
        </Grid>
    );
}

export default Login;



import React, {useEffect, useState} from 'react';
import {Button} from "reactstrap";
import '../static/css/login.css';
import {Grid, Typography, TextField, InputAdornment} from "@material-ui/core";
import {useHistory} from "react-router";

import PersonIcon from "@material-ui/icons/Person";
import LockIcon from "@material-ui/icons/Lock";
import MailIcon from '@material-ui/icons/Mail';



    function Register()  {
        return (
            <div>
                {renderRegister()}
            </div>
        )
    }

    function renderRegister() {
        const history = useHistory();

        const [username, setUsername] = useState("");
        const [password, setPassword] = useState("");
        const [email, setEmail] = useState("");
        const [phoneNumber, setPhoneNumber] = useState("");
        const [isUsernameUnique, setIsUsernameUnique] = useState(true);
        const [isEmailUnique, setIsEmailUnique] = useState(true);

        //const [queryResults] = useState([]);

        useEffect(() => {
            checkUniqueUsername(username, isUsernameUnique, setIsUsernameUnique);
        },[username]);

        useEffect(() => {
            checkUniqueEmail(email, isEmailUnique, setIsEmailUnique);
        },[email]);

        return (
            <Grid
                container direction={"column"} justify={"center"} alignItems={"center"} alignContent={"center"}
                spacing={8}
            >
                <Grid item style={{paddingTop: '50px', paddingBottom: '14px'}}>
                    <Typography variant={"h4"} align={"center"}>Welcome to Portal Chess</Typography>
                </Grid>
                <LoginFields
                    username={username} setUsername={setUsername}
                    password={password} setPassword={setPassword}
                    email={email} setEmail={setEmail}
                    phoneNumber={phoneNumber} setPhoneNumber={setPhoneNumber}
                    isUsernameUnique={isUsernameUnique}
                    isEmailUnique={isEmailUnique}
                    //createAccount={createAccount} {...props}
                />
                <Grid item container justify={"center"} alignItems={"center"} alignContent={"center"} style={{paddingTop: '0px'}}>
                    <Grid item style={{width: "80%"}} align={"center"}>
                        <Button
                            color={"primary"} variant={"contained"} style={{width: "80%", height: "50px"}}
                            //disabled={disabledAccountCreation} onClick={() => createAccount()}
                            onClick={() =>{history.push('home')}}
                        >
                            Create Account
                        </Button>
                    </Grid>
                </Grid>
            </Grid>

        )
    }

    const LoginFields = props => {
        return (
            <Grid
                item container direction={"column"} justify={"center"} alignItems={"center"} alignContent={"center"}
                spacing={3} style={{width: '90%', paddingBottom: '20px'}}
            >
                <Grid item style={{width: '100%'}}>
                    <TextField
                        fullWidth color={"primary"} variant={"outlined"} required
                        label={"Email"} value={props.email} onChange={(e) => props.setEmail(e.target.value)}
                        onKeyDown={(e) =>
                        {
                            //if(e.keyCode === 13) props.createAccount();
                        }}
                        error={!props.isEmailUnique}
                        helperText={!props.isEmailUnique ? "This email already has an account" : " "}
                        InputProps={{
                            startAdornment: (
                                <InputAdornment position="start">
                                    <MailIcon />
                                </InputAdornment>
                            ),
                        }}
                    />
                </Grid>
                <Grid item style={{width: '100%'}}>
                    <TextField
                        fullWidth color={"primary"} variant={"outlined"}
                        autoComplete={"username"} required
                        label={"New Username"} value={props.username} onChange={(e) => props.setUsername(e.target.value)}
                       // onKeyDown={(e) =>
                       // {
                       //     //if(e.keyCode === 13) props.createAccount();
                       // }}
                        error={!props.isUsernameUnique}
                        helperText={!props.isUsernameUnique ? "Username already taken" : " "}
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
                        type={"password"} autoComplete={"current-password"} required
                        label={"New Password"} value={props.password} onChange={(e) => props.setPassword(e.target.value)}
                        onKeyDown={(e) =>
                        {
                            //if(e.keyCode === 13) props.createAccount();
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
        )
    }

    function checkUniqueEmail(email, isEmailUnique, setIsEmailUnique) {
       // this.setState({queryResults: email}, () => {console.log(this.queryResults)}}
        //TODO Check if entered email is in database
        //TODO Send Query to server
        if(email.length < 1) {return;}
        //Make query with username
        //sendRequestWithBody
        //if(body.results.length > 0) {
        if(email == 'user@rams.colostate.edu') {
            setIsEmailUnique(false);
        } else {
            setIsEmailUnique(true);
        }
    }

    function checkUniqueUsername(username, isUsernameUnique, setIsUsernameUnique) {
        //TODO Check if entered username is in database
        if(username.length < 1) {return;}
        //Make query with username
        //sendRequestWithBody
        //if(body.results.length > 0) {
        if(username == 'user') {
            setIsUsernameUnique(false);
        } else {
            setIsUsernameUnique(true);
        }
    }

    function confirmMatchingPasswords() {
        //TODO Check if both password inputs match
    }

    function registerNewUser() {
        //TODO Add user email, nickname, and password to the user database
    }

    export default Register;


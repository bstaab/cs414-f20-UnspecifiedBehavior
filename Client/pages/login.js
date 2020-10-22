import React, {Component} from 'react';
import {FormGroup, Label, Input, Col, Button, Container, Row} from "reactstrap";
import '../static/css/login.css';
import {useHistory} from "react-router";

const renderLogin = () => {

    const history = useHistory();

    return (
        <div >
            <Container className='center'>
                <h2>Sign In</h2>
                <Col>
                    <FormGroup className='form'>
                        <Label>Email</Label>
                        <Input type='email' name='email'/>
                    </FormGroup>
                </Col>
                <Col>
                    <FormGroup className='form'>
                        <Label>Password</Label>
                        <Input type='password' name='password' />
                    </FormGroup>
                </Col>
                <Button className='submit' onClick={() => history.push('home')}>Submit</Button>
            </Container>
            <Container className='sign-up'>
                <Row>
                    <p>Not a registered user?</p>
                    <Button onClick={() => history.push('register')}>Sign up</Button>
                </Row>
            </Container>
            <Button onClick={() => history.push('board')}>Board</Button>
        </div>
    )
}

function renderWelcome() {
    return (
        <div >
            <h1 className='text-center'>Welcome to Portal Chess</h1>
        </div>
    )
}

function Login() {

    return (
        <div>
            {renderWelcome()}
            {renderLogin()}
        </div>
    )
}

export default Login;



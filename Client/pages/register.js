import React, {Component} from 'react';
import {FormGroup, Label, Input, Col, Button, Container, Row} from "reactstrap";
import '../static/css/login.css';


    function Register()  {
        return (
            <div>
                {renderWelcome()}
                {renderRegister()}
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

    function renderRegister() {
        return (
            <div >
                <Container className='center'>
                    <h2>Register</h2>
                    <Col>
                        <FormGroup className='form'>
                            <Label>Email</Label>
                            <Input type='email' name='email'/>
                        </FormGroup>
                    </Col>
                    <Col>
                        <FormGroup className='form'>
                            <Label>Username</Label>
                            <Input type='email' name='email'/>
                        </FormGroup>
                    </Col>
                    <Col>
                        <FormGroup className='form'>
                            <Label>Password</Label>
                            <Input type='password' name='password' />
                        </FormGroup>
                    </Col>
                    <Col>
                        <FormGroup className='form'>
                            <Label>Confirm Password</Label>
                            <Input type='password' name='password' />
                        </FormGroup>
                    </Col>
                    <Button className='submit'  onClick={event => window.location.href='home'}>Register</Button>
                </Container>
                <Container className='sign-up'>
                    <Row>
                        <p>Already registered?</p>
                        <Button  onClick={event => window.location.href='/'}>Login</Button>
                    </Row>
                </Container>
            </div>

        )
    }

    function checkUniqueEmail() {
        //TODO Check if entered email is in database
    }

    function checkUniqueUsername() {
        //TODO Check if entered username is in database
    }

    function confirmMatchingPasswords() {
        //TODO Check if both password inputs match
    }

    function registerNewUser() {
        //TODO Add user email, nickname, and password to the user database
    }

    export default Register;


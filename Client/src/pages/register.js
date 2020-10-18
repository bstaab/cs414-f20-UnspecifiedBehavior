import React, {Component} from 'react';
import {FormGroup, Label, Input, Col, Button, Container, Row} from "reactstrap";
import '../components/css/login.css';


export default class Register extends Component {
    constructor(props) {
        super(props);

        this.renderRegister.bind(this);
        this.renderWelcome.bind(this);

        this.state = {
            email: "",
            username: "",
            password: "",
            matchingPassword: ""
        };
    }

    render() {
        return (
            <div>
                {this.renderWelcome()}
                {this.renderRegister()}
            </div>
        )
    }

    renderWelcome() {
        return (
            <div >
                <h1 className='text-center'>Welcome to Portal Chess</h1>
            </div>
        )
    }

    renderRegister() {
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
                    <Button className='submit'>Register</Button>
                </Container>
                <Container className='sign-up'>
                    <Row>
                        <p>Already registered?</p>
                        <Button>Login</Button>
                    </Row>
                </Container>
            </div>

        )
    }

    checkUniqueEmail() {
        //TODO Check if entered email is in database
    }

    checkUniqueUsername() {
        //TODO Check if entered username is in database
    }

    confirmMatchingPasswords() {
        //TODO Check if both password inputs match
    }

    registerNewUser() {
        //TODO Add user email, nickname, and password to the user database
    }

}

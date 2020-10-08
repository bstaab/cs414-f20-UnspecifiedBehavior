import React, {Component} from 'react';
import {FormGroup, Label, Input, Col, Button, Container, Row} from "reactstrap";
import '../components/css/login.css';


export default class Login extends Component {
    constructor(props) {
        super(props);

        this.renderLogin.bind(this);
        this.renderWelcome.bind(this);
    }

    render() {
        return (
            <div>
                {this.renderWelcome()}
                {this.renderLogin()}
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

    renderLogin() {
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
                    <Button className='submit'>Submit</Button>
                </Container>
                <Container className='sign-up'>
                    <Row>
                        <p>Not a registered user?</p>
                        <Button>Sign up</Button>
                    </Row>
                </Container>
            </div>

        )
    }



}

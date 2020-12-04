import React, {Component, useState} from 'react';
import Navigation from "./Navigation";
import CreateMatch from "./CreateMatch";
import Background from "../static/images/homeBackground.jpg";
//import Container from "@material-ui/core/Container";
import {
    Button,
    Col,
    Collapse, DropdownItem, DropdownMenu,
    DropdownToggle,
    Nav,
    Navbar,
    NavbarBrand,
    NavItem,
    NavLink,
    Row,
    UncontrolledDropdown,
    Container
} from "reactstrap";
import {useHistory} from "react-router";
import Link from "@material-ui/core/Link";

export default class HomeNavigation extends Component {
    constructor(props) {
        super(props);

        this.togglePopup = this.togglePopup.bind(this);
        this.toggleMatchPopup = this.toggleMatchPopup.bind(this);
        this.renderNavigation = this.renderNavigation.bind(this);
        this.renderMenu = this.renderMenu.bind(this);

        this.state = {
            placeName: " ",
            popupOpen: false,
            isOpen: false,
            matchPopup: false
        }
    }

    render() {
        return (
            <div>
                {this.renderHome()}
            </div>
        )
    }

    renderHome() {
        return (
            <div className="home-image">
                <Container fluid style={{paddingLeft: '0px', paddingRight: '0px'}}>
                    <Row>
                        <Col sm={12} md={{size: 12, offset: 0}}>
                            {this.renderNavigation()}
                            {this.renderMenu(this.props.height)}
                            {this.renderPop()}
                            {this.renderMatchPopup()}
                        </Col>
                    </Row>
                </Container>
            </div>
        )
    }

    renderNavigation() {
        return (
            <div>
                <Navbar color="dark" dark expand="md">
                    <NavbarBrand>Portal Chess</NavbarBrand>
                    <Collapse isOpen={this.state.isOpen} navbar>
                        <Nav className="mr-auto" navbar>
                            <NavItem>
                                <Button color="dark" onClick={() => this.props.history.push('profile')}>Profile</Button>
                            </NavItem>
                            <NavItem>
                                <Button color="dark" onClick={this.togglePopup}>Invitations</Button>
                            </NavItem>
                            <UncontrolledDropdown nav inNavbar>
                                <DropdownToggle style={{color: 'white'}} nav caret>
                                    Options
                                </DropdownToggle>
                                <DropdownMenu right>
                                    <DropdownItem>
                                        Option 1
                                    </DropdownItem>
                                    <DropdownItem>
                                        Option 2
                                    </DropdownItem>
                                    <DropdownItem divider/>
                                    <DropdownItem>
                                        Reset
                                    </DropdownItem>
                                </DropdownMenu>
                            </UncontrolledDropdown>
                            <NavItem>
                                <Button color="dark">Log Out</Button>
                            </NavItem>
                        </Nav>
                    </Collapse>
                </Navbar>
            </div>
        )
    }

    renderMenu() {
        return (
            <div>
                <Container style={{
                    backgroundColor: 'rgba(192,192,192, 0.3)',
                    width: '42%',
                    height: this.props.height,
                    marginLeft: '0'
                }}>
                    <br/>
                    <Button color='primary' block onClick={this.toggleMatchPopup}>Create A Game</Button>
                    <br/>
                    <Button color='secondary' block onClick={() => this.props.history.push('board')}> Continue A Game</Button>
                    <br/>
                    <Button color='secondary' block onClick={this.togglePopup}> Invitations</Button>
                    <br/>
                </Container>
            </div>
        )
    }

    renderPop() {
        return <Navigation popupOpen={this.state.popupOpen} togglePopup={this.togglePopup}/>
    }

    togglePopup() {
        this.setState({popupOpen: !this.state.popupOpen});
    }

    renderMatchPopup() {
        return <CreateMatch popupOpen={this.state.matchPopup} togglePopup={this.toggleMatchPopup} />
    }

    toggleMatchPopup() {
        this.setState({matchPopup: !this.state.matchPopup});
    }

}
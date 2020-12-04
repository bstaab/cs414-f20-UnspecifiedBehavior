import React, {Component, useState} from 'react';
import Navigation from "./Navigation";
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


    function HomeNavigation() {
        return (
            <div>
                {renderHome()}
            </div>
        )
    }

        function renderHome() {
            //const [placeName, setPlaceName] = useState("");
            //const [popupOpen, setPopupOpen] = useState(false);
            localStorage.setItem('placeName', '');
            localStorage.setItem('popupOpen', 'false');
            localStorage.setItem('isOpen', 'false');

            return (
                <div className="home-image">
                    <Container fluid style={{paddingLeft: '0px', paddingRight: '0px'}}>
                        <Row>
                            <Col sm={12} md={{size: 12, offset: 0}}>
                                {renderNavigation()}
                                {renderMenu()}
                                {renderPop()}
                            </Col>
                        </Row>
                    </Container>
                </div>
            )
        }

        function renderNavigation() {
            const history = useHistory();
            //const [isOpen, setIsOpen] = useState(false);
            const open = localStorage.getItem('isOpen');
            let isOpen = false;
            if (open == 'true') {
                isOpen = true;
            }

            return (
                <div>
                    <Navbar color="dark" dark expand="md">
                        <NavbarBrand>Portal Chess</NavbarBrand>
                        <Collapse isOpen={isOpen} navbar>
                            <Nav className="mr-auto" navbar>
                                <NavItem>
                                    <Button color="dark" onClick={() => {history.push('/Profile')}}>Profile</Button>
                                </NavItem>
                                <NavItem>
                                    <Button color="dark" onClick={togglePopup}>Invitations</Button>
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

        function renderMenu() {
            const history = useHistory();
            return (
                <div>
                    <Container style={{
                        backgroundColor: 'rgba(192,192,192, 0.3)',
                        width: '42%',
                        height: 'auto',
                        marginLeft: '0'
                    }}>
                        <br/>
                        <Button color='primary' block onClick={() => {history.push('/Board')}}>Create A Game</Button>
                        <br/>
                        <Button color='secondary' block onClick={() => {history.push('/Board')}}> Continue A Game</Button>
                        <br/>
                        <Button color='secondary' block onClick={togglePopup}> Invitations</Button>
                        <br/>
                    </Container>
                </div>
            )
        }

        function renderPop() {
            let popupOpen = false;
            if (localStorage.getItem('popupOpen') == 'true') {
                popupOpen = true;
            }
            return <Navigation popupOpen={popupOpen} togglePopup={togglePopup}/>
        }

        function togglePopup() {
            const popupOpen = localStorage.getItem('popupOpen');
            if (popupOpen == 'false') {
                localStorage.setItem('popupOpen', 'true');
            }
            else {
                localStorage.setItem('popupOpen', 'false');
            }
            //setPopupOpen({popupOpen: !popupOpen});
        }

    export default HomeNavigation;
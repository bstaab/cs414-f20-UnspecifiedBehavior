import React, {Component} from 'react';
import Navigation from "../components/Navigation";
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

export default class Home extends Component {
    constructor(props) {
        super(props);

        this.renderNavigation = this.renderNavigation.bind(this);
        this.renderPop = this.renderPop.bind(this);
        this.togglePopup = this.togglePopup.bind(this);

        this.state ={
            popupOpen: false,
            isOpen: false
        }

    }

    render() {
        return (
            <div style={{backgroundImage: "url(" + Background + ")", backgroundSize: 'cover', height: '600px'}}>
                <Container style={{padding: '0px'}}>
                    <Row>
                        <Col sm={12} md={{size: 12, offset: 0}}>
                            {this.renderNavigation()}
                            {this.renderMenu()}
                            {this.renderPop()}
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
                    <NavbarBrand >Portal Chess</NavbarBrand>
                    <Collapse isOpen={this.state.isOpen} navbar>
                        <Nav className="mr-auto" navbar>
                            <NavItem>
                                <NavLink >Profile</NavLink>
                            </NavItem>
                            <NavItem>
                                <Button color="dark" onClick={this.togglePopup}>Invitations</Button>
                            </NavItem>
                            <UncontrolledDropdown nav inNavbar>
                                <DropdownToggle nav caret>
                                    Options
                                </DropdownToggle>
                                <DropdownMenu right>
                                    <DropdownItem>
                                        Option 1
                                    </DropdownItem>
                                    <DropdownItem>
                                        Option 2
                                    </DropdownItem>
                                    <DropdownItem divider />
                                    <DropdownItem>
                                        Reset
                                    </DropdownItem>
                                </DropdownMenu>
                            </UncontrolledDropdown>
                        </Nav>
                    </Collapse>
                </Navbar>
            </div>
        )
    }

    renderMenu() {
        return (
            <div>
                <Container style={{backgroundColor: 'rgba(192,192,0.3)', width: '42%', height: '600px'}}>
                    <br />
                    <Button color='primary' block>Create A Game</Button>
                    <br />
                    <Button color='secondary' block> Invitations</Button>
                    <br />
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
}
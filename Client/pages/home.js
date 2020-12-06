import React, {Component, useState} from 'react';
import Navigation from "./Navigation";
import CreateMatch from "./CreateMatch";
import {Button, Col, Collapse, DropdownItem, DropdownMenu, DropdownToggle, Nav, Navbar, NavbarBrand, NavItem, Row, UncontrolledDropdown, Container
} from "reactstrap";
import Chessground from "react-chessground";
import Match from "./Match";

export default class Home extends Component {
    constructor(props) {
        super(props);

        this.togglePopup = this.togglePopup.bind(this);
        this.toggleMatchPopup = this.toggleMatchPopup.bind(this);
        this.renderNavigation = this.renderNavigation.bind(this);
        this.renderMenu = this.renderMenu.bind(this);
        this.toggleChessPopup = this.toggleChessPopup.bind(this);
        this.onMove = this.onMove.bind(this);

        this.state = {
            placeName: " ",
            popupOpen: false,
            isOpen: false,
            matchPopup: false,
            chessboardPopup: false,
            fen: "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR",
            gameListPopup: false
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
                {this.renderNavigation()}

                <Row>
                    <Col xs={3}>
                        {this.renderMenu(this.props.height)}
                        {this.renderPop()}
                        {this.renderMatchPopup()}
                        {this.renderGameList()}
                    </Col>
                </Row>
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
                                        Messages
                                    </DropdownItem>
                                    <DropdownItem divider/>
                                    <DropdownItem>
                                        Unregister
                                    </DropdownItem>
                                </DropdownMenu>
                            </UncontrolledDropdown>
                            <NavItem>
                                <Button color="dark" onClick={() => this.props.history.push('/')}>Log Out</Button>
                            </NavItem>
                        </Nav>
                    </Collapse>
                </Navbar>
            </div>
        )
    }



    renderMenu() {
        return (
            <Container style={{
                backgroundColor: 'rgba(192,192,192, 0.3)',
                height: this.props.height,
                marginLeft: '0'
            }}>
                <br/>
                <Button color='primary' block onClick={this.toggleMatchPopup}>Create A Game</Button>
                <br/>
                <Button color='secondary' block onClick={this.toggleGameList}> Continue A Game</Button>
                <br/>
                <Button color='secondary' block onClick={this.togglePopup}> Invitations</Button>
                <br/>
            </Container>
        );
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

    renderChessBoard() {return(
        <Chessground
            width="38vw"
            height="38vw"
            fen={'rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR'}
            onMove={this.onMove}
        />    )}

    onMove(from, to) {
        console.log(from, to);
    }

    toggleChessPopup() {
        this.setState({chessboardPopup : !this.state.chessboardPopup});
    }

    renderGameList() {
        return <Match popupOpen={this.state.gameListPopup} togglePopup={this.toggleGameList}/>
    }

    toggleGameList() {
        this.setState({gameListPopup: !this.state.gameListPopup});
    }

}
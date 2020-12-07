import React, {useState, useEffect} from 'react';
import CreateMatch from "./CreateMatch";
import Invite from './Invite'
import {Button, Col, Collapse, DropdownItem, DropdownMenu, DropdownToggle, Nav, Navbar, NavbarBrand, NavItem, Row, UncontrolledDropdown, Container
} from "reactstrap";
import Chessground from "react-chessground";
import Match from "./Match";
import {sendPostRequest} from "../components/API";
import {Grid} from "@material-ui/core";


function Home(props) {
    const [popupOpen, setPopupOpen] = useState(false);
    const [isOpen, setIsOpen] = useState(false);
    const [matchPopup, setMatchPopup] = useState(false);
    const [fen, setFen] = useState("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
    const [gameListPopup, setGameListPopup] = useState(false);
    const [gameListW, setGameListW] = useState([]);
    const [gameListB, setGameListB] = useState([]);
    const [currentGame, setCurrentGame] = useState();
    const [chessBoardPopup, setChessBoardPopup] = useState(false);
    const [whiteUser, setWhiteUser] = useState();
    const [blackUser, setBlackUser] = useState();


    return (
        <div className="home-image">
            <NavigationMenu isOpen={isOpen} {...props} setPopupOpen={setPopupOpen} popupOpen={popupOpen}/>
            <Row>
                <Col xs={3}>
                    {renderMenu(props, setMatchPopup, matchPopup, setGameListPopup, gameListPopup, setPopupOpen, popupOpen, setGameListW, setGameListB)}
                    <Invite {...props}  openInviteModal={popupOpen} setOpenInviteModal={setPopupOpen}
                            setChessBoardPopup={setChessBoardPopup} setCurrentGame={setCurrentGame}
                            chessBoardPopup={chessBoardPopup} setWhiteUser={setWhiteUser}
                            setBlackUser={setBlackUser} setFen={setFen}
                    />
                    <CreateMatch {...props} popupOpen={matchPopup} togglePopup={() => toggleMatchPopup(setMatchPopup, matchPopup)} />
                    <Match {...props} popupOpen={gameListPopup} gameListW={gameListW} gameListB={gameListB} setCurrentGame={setCurrentGame}
                           setChessBoardPopup={setChessBoardPopup} chessBoardPopup={chessBoardPopup}
                           whiteUser={whiteUser} black={blackUser} setFen={setFen}
                           togglePopup={() => toggleGameList(props, setGameListPopup, gameListPopup, setGameListW, setGameListB)}
                    />
                    {chessBoardPopup ? <ChessBoard fen={fen} setFen={setFen} currentGame={currentGame} whiteUser={whiteUser} blackUser={blackUser} {...props}/> : null}
                </Col>
            </Row>
        </div>
    )

}

function ChessBoard(props) {
    function onMove(from, to) {
        sendPostRequest('move', {'whiteUser': props.whiteUser, 'blackUser': props.blackUser,'from' : from, 'to' : to, 'username': props.userData})
            .then(
                r => {
                    console.log(r.data)
                        if (!r.data.valid) {
                            props.setFen('rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR')
                            props.produceSnackBar('Invalid Move', 'error');
                        }
                        props.setFen((' ' + r.data.fen).slice(1));

                }
            )

    }

    return (
        <div>
            <Col xs={9}>
                <Chessground
                    width="30vw"
                    height="30vw"
                    fen={props.fen}
                    onMove={onMove}
                />
            </Col>
        </div>

    )
}

function toggleGameList(props, setGameListPopup, gameListPopup, setGameListW, setGameListB) {
    setGameListPopup(!gameListPopup);

    sendPostRequest('currentMatches', {'username': props.userData})
        .then(r => {
            console.log(r.data);
                setGameListW(r.data.wGames)
                setGameListB(r.data.bGames)
            }
        )
}

function NavigationMenu(props) {
    return (
        <div>
            <Navbar color="dark" dark expand="md">
                <NavbarBrand>Portal Chess</NavbarBrand>
                <Collapse isOpen={props.isOpen} navbar>
                    <Nav className="mr-auto" navbar>
                        <NavItem>
                            <Button color="dark" onClick={() => props.history.push('profile')}>Profile</Button>
                        </NavItem>
                        <NavItem>
                            <Button color="dark" onClick={() => togglePopup(props.setPopupOpen, props.popupOpen)}>Invitations</Button>
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
                                <DropdownItem onClick={() => {unregisterUser(props); props.history.push('/')}}>
                                    Unregister
                                </DropdownItem>
                            </DropdownMenu>
                        </UncontrolledDropdown>
                        <NavItem>
                            <Button color="dark" onClick={() => props.history.push('/')}>Log Out</Button>
                        </NavItem>
                    </Nav>
                </Collapse>
            </Navbar>
        </div>
    )
}



function renderMenu(props, setMatchPopup, matchPopup, setGameListPopup, gameListPopup, setPopupOpen, popupOpen, setGameListW, setGameListB) {
    return (
        <Container style={{
            backgroundColor: 'rgba(192,192,192, 0.3)',
            marginLeft: '0'
        }}>
            <br/>
            <Button color='primary' block onClick={() => toggleMatchPopup(setMatchPopup, matchPopup)}>Create A Game</Button>
            <br/>
            <Button color='secondary' block onClick={() => toggleGameList(props, setGameListPopup, gameListPopup, setGameListW, setGameListB)}> Continue A Game</Button>
            <br/>
            <Button color='secondary' block onClick={() => togglePopup(setPopupOpen, popupOpen)}> Invitations</Button>
            <br/>
        </Container>
    );
}

function togglePopup(setPopupOpen, popupOpen) {
    setPopupOpen(!popupOpen)
}

function toggleMatchPopup(setMatchPopup, matchPopup) {
    setMatchPopup(!matchPopup)
}


function unregisterUser(props) {
    sendPostRequest('unregister', {'username': props.userData, 'password' : props.password})
        .then(r => {
            let valid = r.data.valid;
            if (!valid) props.produceSnackBar("Unable to Register", "info");
        });

}

export default Home;


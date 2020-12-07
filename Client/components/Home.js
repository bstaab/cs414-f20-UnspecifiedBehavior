import React, {useState, useEffect} from 'react';
import CreateMatch from "./CreateMatch";
import Invitations from './Invitations'
import {Button, Col, Collapse, DropdownItem, DropdownMenu, DropdownToggle, Nav, Navbar, NavbarBrand, NavItem, Row, UncontrolledDropdown, Container
} from "reactstrap";
import Chessground from "react-chessground";
import ContinueGame from "./ContinueGame";
import {sendPostRequest} from "../hooks/API";

function Home(props) {

    const [createGamePopupOpen, setCreateGamePopupOpen] = useState(false);
    const [invitationsPopupOpen, setInvitationsPopupOpen] = useState(false);
    const [continueGamePopupOpen, setContinueGamePopupOpen] = useState(false);
    const [chessBoardPopupOpen, setChessBoardPopupOpen] = useState(false);

    const [fen, setFen] = useState("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");

    const [opponentUserData, setOpponentUserData] = useState();
    const [isWhiteUserInOpenGame, setIsWhiteUserInOpenGame] = useState();

    return (
        <div className="home-image">
            <NavigationMenu {...props}/>
            <Row>
                <Col xs={3}>
                    <Container style={{backgroundColor: 'rgba(192,192,192, 0.3)', marginLeft: '0'}}>
                        <br/>
                        <Button color='primary' block onClick={() => setCreateGamePopupOpen(!createGamePopupOpen)}>Create A Game</Button>
                        <br/>
                        <Button color='secondary' block onClick={() => setInvitationsPopupOpen(!invitationsPopupOpen)}>Invitations</Button>
                        <br/>
                        <Button color='secondary' block onClick={() => setContinueGamePopupOpen(!continueGamePopupOpen)}>Continue A Game</Button>
                        <br/>
                    </Container>
                    {createGamePopupOpen ? <CreateMatch
                        {...props} createGamePopupOpen={createGamePopupOpen} setCreateGamePopupOpen={setCreateGamePopupOpen}
                    /> : null }
                    {invitationsPopupOpen ? <Invitations
                        {...props} invitationsPopupOpen={invitationsPopupOpen} setInvitationsPopupOpen={setInvitationsPopupOpen}
                        setOpponentUserData={setOpponentUserData} chessBoardPopupOpen={chessBoardPopupOpen} setChessBoardPopupOpen={setChessBoardPopupOpen}
                        setFen={setFen} setIsWhiteUserInOpenGame={setIsWhiteUserInOpenGame}
                        isWhiteUserInOpenGame={isWhiteUserInOpenGame} opponentuserData={opponentUserData}
                    /> : null }
                    {continueGamePopupOpen ? <ContinueGame
                        {...props} continueGamePopupOpen={continueGamePopupOpen} setContinueGamePopupOpen={setContinueGamePopupOpen}
                        setOpponentUserData={setOpponentUserData} chessBoardPopupOpen={chessBoardPopupOpen}
                        setChessBoardPopupOpen={setChessBoardPopupOpen} setFen={setFen} setIsWhiteUserInOpenGame={setIsWhiteUserInOpenGame}
                    /> : null}
                    {chessBoardPopupOpen ? <ChessBoard fen={fen} setFen={setFen} opponentUserData={opponentUserData} isWhiteUserInOpenGame={isWhiteUserInOpenGame} {...props}/> : null}
                </Col>
            </Row>
        </div>
    )

}

function ChessBoard(props) {

    let whiteUser = props.isWhiteUserInOpenGame ? props.userData.username : props.opponentUserData.username;
    let blackUser = props.isWhiteUserInOpenGame ? props.opponentUserData.username : props.userData.username;

    function onMove(from, to) {
        sendPostRequest('move', {'whiteUser': whiteUser, 'blackUser': blackUser,'from' : from, 'to' : to, 'username': props.userData.username})
            .then(
                r => {
                    console.log(r.data);
                    if (!r.data.valid) {
                        props.setFen('rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR')
                        props.produceSnackBar('Invalid Move', 'error');
                    }
                    props.setFen(r.data.fen);
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

function NavigationMenu(props) {
    return (
        <div>
            <Navbar color="dark" dark expand="md">
                <NavbarBrand>Portal Chess</NavbarBrand>
                <Nav className="mr-auto" navbar>
                    <NavItem>
                        <Button color="dark" onClick={() => props.history.push('profile')}>Profile</Button>
                    </NavItem>
                    <NavItem>
                        <Button color="dark" onClick={() => {unregisterUser(props); props.history.push('/')}}>Unregister</Button>
                    </NavItem>
                    <NavItem>
                        <Button color="dark" onClick={() => props.history.push('/')}>Log Out</Button>
                    </NavItem>
                </Nav>
            </Navbar>
        </div>
    )
}

function unregisterUser(props) {
    sendPostRequest('unregister', {'username': props.userData.username, 'password' : props.userData.password})
        .then(r => {
            let valid = r.data.valid;
            if (!valid) props.produceSnackBar("Unable to Register", "info");
        });

}

export default Home;


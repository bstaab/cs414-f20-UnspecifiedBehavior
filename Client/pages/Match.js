import React, {useEffect, useState} from 'react';
import {Button, Modal, ModalBody, ModalFooter, Table} from "reactstrap";
import Col from "reactstrap/es/Col";
import Chessground from "react-chessground";
import {sendPostRequest} from "../components/API";


function Match(props) {
    const [chessBoardPopup, setChessBoardPopup] = useState(false);
    const [popup, setPopup] = useState(false);
    const [currentGame, setCurrentGame] = useState();


    function defineRow(username) {
        return (
            <tr>
                <td>{username}</td>
                <td>
                    <Button onClick={() => {
                        toggleChessPopup(setChessBoardPopup, chessBoardPopup);
                        setCurrentGame(username);
                    }} color="primary">Play Game</Button>
                </td>
            </tr>
        )

    }

    function defineName() {
        return (
            <Table striped responsive>
                <thead>
                <tr>
                    <th>Opponent</th>
                    <th>Game</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                {
                    props.gameListW.concat(props.gameListB).map(username => {console.log(username); defineRow(username)})
                }
                </tbody>
            </Table>
        )
    }

    return (
        <div>
            {
                chessBoardPopup ? <ChessBoard fen={props.fen} currentGame={currentGame} {...props}/> : null
            }
            <Modal isOpen={props.popupOpen} centered={true} toggle={() => props.togglePopup(!props.popupOpen)}>
                <ModalBody>
                    {defineName(setChessBoardPopup, chessBoardPopup)}
                </ModalBody>
                <ModalFooter>
                    <Button color="secondary" onClick={() => props.togglePopup(!props.popupOpen)}>Close</Button>
                </ModalFooter>
            </Modal>
        </div>
    )
}

function ChessBoard(props) {
    let isWhite = props.gameListW.includes(props.currentGame);
    //const [fen, setFen] = useState('rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR');

    useEffect(() => {
        sendPostRequest('fetchGame', {'whiteUser': isWhite ? props.currentGame : props.userData, "blackUser": isWhite ? props.userData : props.currentGame})
            .then(
                r => {
                    props.setFen(r.data.fen)
                }
            )
    }, [])

    function onMove(from, to) {
        sendPostRequest('move', {'from' : from, 'to' : to, 'match' : 1})
            .then(

            )
    }

    return (
        <Col xs={9}>
            <Chessground
                width="38vw"
                height="38vw"
                fen={props.fen}
                onMove={() => onMove()}
            />
        </Col>
    )
}


function toggleChessPopup(setChessBoardPopup, chessBoardPopup) {
    setChessBoardPopup(!chessBoardPopup)
}

export default Match;
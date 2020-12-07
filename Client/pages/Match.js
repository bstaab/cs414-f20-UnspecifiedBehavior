import React, {useEffect, useState} from 'react';
import {Button, Modal, ModalBody, ModalFooter, Table} from "reactstrap";
import Col from "reactstrap/es/Col";
import Chessground from "react-chessground";
import {sendPostRequest} from "../components/API";


function Match(props) {

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
                    props.gameListW.concat(props.gameListB).map(username =>
                        <tr>
                            <td>{username}</td>
                            <td>
                                <Button onClick={() => {
                                    props.setChessBoardPopup(!props.chessBoardPopup);
                                    props.setCurrentGame(username);
                                    fetch()
                                }} color="primary">Play Game</Button>
                            </td>
                        </tr>
                    )
                }
                </tbody>
            </Table>
        )
    }

    function fetch() {
        sendPostRequest('fetchGame', {'whiteUser': props.whiteUser, 'blackUser': props.blackUser})
            .then(
                r => {
                    props.setFen(r.data.fen)
                }
            )
    }

    return (
        <div>
            <Modal isOpen={props.popupOpen} centered={true} toggle={() => props.togglePopup(!props.popupOpen)}>
                <ModalBody>
                    {defineName(props.setChessBoardPopup, props.chessBoardPopup)}
                </ModalBody>
                <ModalFooter>
                    <Button color="secondary" onClick={() => props.togglePopup(!props.popupOpen)}>Close</Button>
                </ModalFooter>
            </Modal>
        </div>
    )
}


export default Match;
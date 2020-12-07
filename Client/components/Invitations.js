import React, {useEffect, useState} from 'react';
import {Button, Modal, ModalBody, ModalFooter, Table} from "reactstrap";

import {sendPostRequest} from "../hooks/API";

function Invitations(props) {

    const [gameListW, setGameListW] = useState([]);
    const [gameListB, setGameListB] = useState([]);

    useEffect(() => {
        sendPostRequest('currentMatches', {'username': props.userData.username})
            .then(r => {
                    setGameListW(r.data.wGames)
                    setGameListB(r.data.bGames)
                }
            )
    }, []);

    return (
        <div>
            <Modal isOpen={props.invitationsPopupOpen} centered={true} toggle={() => props.setInvitationsPopupOpen(!props.invitationsPopupOpen)}>
                <ModalBody>
                    <Table striped responsive>
                        <thead>
                        <tr>
                            <th>Opponent</th>
                            <th>Game</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            gameListW.concat(gameListB).map((username, index) =>
                                <tr key={index}>
                                    <td>{username}</td>
                                    <td>
                                        <Button onClick={() => {
                                            props.setChessBoardPopupOpen(!props.chessBoardPopupOpen);
                                            sendPostRequest('userData', {username: username}).then(
                                                r => {
                                                    props.setOpponentUserData(r.data)
                                                    let whiteUser = props.isWhiteUserInOpenGame ? props.userData.username : r.data.username;
                                                    let blackUser = props.isWhiteUserInOpenGame ? r.data.username : props.userData.username;

                                                    sendPostRequest('fetchGame', {'whiteUser': whiteUser, 'blackUser': blackUser})
                                                    .then(r => props.setFen(r.data.fen))
                                                }
                                            )
                                        }} color="primary">Play Game</Button>
                                    </td>
                                </tr>
                            )
                        }
                        </tbody>
                    </Table>
                </ModalBody>
                <ModalFooter>
                    <Button color="secondary" onClick={() => props.setInvitationsPopupOpen(!props.invitationsPopupOpen)}>Close</Button>
                </ModalFooter>
            </Modal>
        </div>
    )
}


export default Invitations;
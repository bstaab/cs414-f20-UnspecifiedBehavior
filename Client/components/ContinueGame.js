import React, {useEffect, useState} from 'react';
import {Button, Modal, ModalBody, ModalFooter, Table} from "reactstrap";

import {sendPostRequest} from "../hooks/API";

function ContinueGame(props) {

    const [gameListW, setGameListW] = useState([]);
    const [gameListB, setGameListB] = useState([]);

    console.log((gameListW))

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
            <Modal isOpen={props.continueGamePopupOpen} centered={true} toggle={() => props.setContinueGamePopupOpen(!props.continueGamePopupOpen)}>
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
                                            sendPostRequest('userData', {username: username}).then(
                                                r => {
                                                    props.setOpponentUserData(r.data);
                                                    let whiteUser = gameListB.includes(username) ? username : props.userData.username;
                                                    let blackUser = gameListW.includes(username) ? username : props.userData.username;
                                                    props.setIsWhiteUserInOpenGame(whiteUser === props.userData.username)
                                                    sendPostRequest('fetchGame', {'whiteUser': whiteUser, 'blackUser': blackUser})
                                                        .then(r => {
                                                                props.setFen(r.data.fen);
                                                                props.setContinueGamePopupOpen(!props.continueGamePopupOpen);
                                                                props.setChessBoardPopupOpen(!props.chessBoardPopupOpen);
                                                            }
                                                        )



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
                    <Button color="secondary" onClick={() => props.setContinueGamePopupOpen(!props.continueGamePopupOpen)}>Close</Button>
                </ModalFooter>
            </Modal>
        </div>
    )
}


export default ContinueGame;
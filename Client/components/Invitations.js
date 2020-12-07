import React, {useEffect, useState} from 'react'
import {IconButton} from "@material-ui/core";
import {sendPostRequest} from "../hooks/API";
import {Table, Modal} from 'reactstrap';

import CheckIcon from '@material-ui/icons/Check';
import CloseIcon from '@material-ui/icons/Close';

function Invitations(props) {
    const [invitationList, setInvitationList] = useState([]);

    useEffect(() => {
        sendPostRequest('getAllMessages', {'username': props.userData.username}).then(
            r => {
                if (r.data.valid) setInvitationList(r.data.from);
                else props.produceSnackBar('No Matches', 'info');
            }
        )
    }, [])

    function createNewGame(opponentName) {
        sendPostRequest('newChessMatch', {'user1': props.userData.username, 'user2': opponentName})
            .then(
                r => {
                    if (r.data.valid) {
                        props.setIsWhiteUserInOpenGame(r.data.whiteUser === props.userData.username)
                        sendPostRequest('userData', {username: opponentName}).then(
                            r => {
                                props.setOpponentUserData(r.data)
                                props.setFen(r.data.fen);
                                props.produceSnackBar('Game Successfully Accepted', 'success');
                                props.setChessBoardPopupOpen(!props.chessBoardPopupOpen);
                                props.setInvitationsPopupOpen(!props.invitationsPopupOpen);
                            })
                    }
                    else {
                        props.produceSnackBar('Game Acceptance Failed', 'error');
                    }
                }
            )

    }

    function quitGame(opponentName) {

        sendPostRequest('reject', {'to': props.userData.username, 'from' : opponentName})
            .then(
                r => {
                    if (r.data.valid) {
                        props.produceSnackBar('Rejected Invitation', 'info');
                    }
                    else {
                        props.produceSnackBar('Unable to reject invitation', 'error');
                    }
                }
            )
    }

    return (
        <div>
            <Modal isOpen={props.invitationsPopupOpen} centered={true} toggle={() => props.setInvitationsPopupOpen(!props.invitationsPopupOpen)}>
                <Table striped responsive>
                    <thead>
                    <tr>
                        <th>Opponent</th>
                        <th>Option</th>
                    </tr>
                    </thead>
                    <tbody>
                    {invitationList.map(opponentName =>
                        <tr>
                            <td>{opponentName}</td>
                            <td>
                                <IconButton onClick={() => createNewGame(opponentName)}><CheckIcon/></IconButton>
                                <IconButton onClick={() => quitGame(opponentName)}><CloseIcon/></IconButton>
                            </td>
                        </tr>

                    )}
                    </tbody>
                </Table>

            </Modal>
        </div>
    )
}

export default Invitations;
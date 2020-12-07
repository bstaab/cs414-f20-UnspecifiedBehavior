import React, {useState} from 'react';
import {Button, Modal, ModalBody, ModalFooter, ModalHeader, Input, Table} from "reactstrap";
import TextField from "@material-ui/core/TextField";
import {sendPostRequest} from "../hooks/API";


function CreateMatch(props) {
    const [opponentName, setOpponentName] = useState('');
    const [validOpponent, setValidOpponent] = useState(true);

    function sendInvite() {
        sendPostRequest('userData', {'username': opponentName}).then(
            r => {
                if (r.data.valid) {
                    sendPostRequest('matchRequest', {'to': opponentName, 'from': props.userData.username})
                        .then(
                            r => {
                                console.log(r.data);
                                if (r.data.valid) props.produceSnackBar('Game Invite Sent', 'info');
                                else props.produceSnackBar('Game Invite Failed', 'error');
                            })
                }
                else {
                    props.produceSnackBar('Invalid User', 'error');
                    setValidOpponent(false);
                }
            })
    }

    return (
        <Modal isOpen={props.createGamePopupOpen} centered={true} toggle={() => props.setCreateGamePopupOpen(props.createGamePopupOpen)}>
            <ModalHeader>Invite an opponent</ModalHeader>
            <ModalBody>
                <TextField value={opponentName} onChange={(e) => setOpponentName(e.target.value)} error={!validOpponent} placeholder="Username"/>
            </ModalBody>
            <ModalFooter>
                <Button color="secondary" onClick={() => props.setCreateGamePopupOpen(!props.createGamePopupOpen)}>Close</Button>
                <Button color="primary" onClick={() => sendInvite()}>Invite</Button>
            </ModalFooter>
        </Modal>
    )
}


export default CreateMatch;
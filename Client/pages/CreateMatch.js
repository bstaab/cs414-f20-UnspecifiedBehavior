import React, {Component, useState} from 'react';
import {Button, Modal, ModalBody, ModalFooter, ModalHeader, Input, Table} from "reactstrap";
import TextField from "@material-ui/core/TextField";
import {sendPostRequest} from "../components/API";


    function CreateMatch(props) {
        const [opponentName, setOpponentName] = useState('');
        const [validOpponent, setValidOpponent] = useState(true);

        function sendInvite() {
            sendPostRequest('userData', {'username': opponentName}).then(
                r => {
                    console.log(opponentName);
                    console.log(r.data);
                    if (r.data.valid) {
                        sendPostRequest('matchRequest', {'from': props.userData, 'to': opponentName})
                            .then(
                                r => {
                                    props.produceSnackBar('Invite sent', 'info');
                                })
                    }
                    else {
                        setValidOpponent(false);
                    }
            })
        }

        return (
            <Modal isOpen={props.popupOpen} centered={true} toggle={() => props.togglePopup()}>
                <ModalHeader>Invite an opponent</ModalHeader>
                <ModalBody>
                    <TextField value={opponentName} onChange={(e) => setOpponentName(e.target.value)} error={!validOpponent} placeholder="Enter username"/>
                </ModalBody>
                <ModalFooter>
                    <Button color="secondary" onClick={() => props.togglePopup()}>Close</Button>
                    <Button color="primary" onClick={() => sendInvite()}>Invite</Button>
                </ModalFooter>
            </Modal>
        )
    }


export default CreateMatch;
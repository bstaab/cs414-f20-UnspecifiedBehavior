import React, {Component } from 'react';
import {Button, Modal, ModalBody, ModalFooter, ModalHeader, Input, Table} from "reactstrap";
import TextField from "@material-ui/core/TextField";

export default class CreateMatch extends Component {
    constructor(props){
        super(props);

        this.defineName = this.defineName.bind(this);
        this.togglePopup = this.togglePopup.bind(this);
        this.updateOpponentName = this.updateOpponentName.bind(this);

        this.state = {
            opponentName: " "
        }
    }

    render() {
        return (
            <Modal isOpen={this.props.popupOpen} centered={true} toggle={this.togglePopup}>
                <ModalHeader>Invite an opponent</ModalHeader>
                <ModalBody>
                    <Input onChange={(e) => this.updateOpponentName(e.target.value)} placeholder="Enter username"></Input>
                </ModalBody>
                <ModalFooter>
                    <Button color="secondary" onClick={this.togglePopup}>Close</Button>
                    <Button color="primary" onClick={this.sendInvite}>Invite</Button>
                </ModalFooter>
            </Modal>
        )
    }

    defineName() {
        return (
            <TextField />
        )
    }

    sendInvite() {

    }

    updateOpponentName(name) {
        this.setState({opponentName: name});
    }

    togglePopup() {
        this.props.togglePopup();
    }

}
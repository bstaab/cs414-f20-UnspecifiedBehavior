import React, {Component } from 'react';
import {Button, Modal, ModalBody, ModalFooter, ModalHeader, Input, Table} from "reactstrap";
import TextField from "@material-ui/core/TextField";
import Chessground from "./chessground";

export default class Chessboard extends Component {
    constructor(props) {
        super(props);

        this.togglePopup = this.togglePopup.bind(this);

    }

    render() {
        return (
            <Modal isOpen={this.props.popupOpen} centered={true} toggle={this.togglePopup}>
                <ModalHeader>Invite an opponent</ModalHeader>
                <ModalBody>
                    <Chessground/>
                </ModalBody>
            </Modal>
        )
    }

    togglePopup() {
        this.props.togglePopup();
    }
}


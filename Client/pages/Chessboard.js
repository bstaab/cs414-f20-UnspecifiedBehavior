import React, {Component } from 'react';
import {Button, Modal, ModalBody, ModalFooter, ModalHeader, Input, Table} from "reactstrap";
import TextField from "@material-ui/core/TextField";
import Chessground from "./chessground";

export default class Chessboard extends Component {
    constructor(props) {
        super(props);

        this.defineName = this.defineName.bind(this);
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

    defineName() {
        return (
            <TextField/>
        )
    }



    togglePopup() {
        this.props.togglePopup();
    }
}


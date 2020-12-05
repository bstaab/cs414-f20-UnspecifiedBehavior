import React, {Component } from 'react';
import {Button, Modal, ModalBody, ModalFooter, ModalHeader, Input, Table} from "reactstrap";
import TextField from "@material-ui/core/TextField";
import Chessground from "react-chessground";
import "react-chessground/dist/styles/chessground.css"

export default class Chessboard extends Component {
    constructor(props) {
        super(props);

        this.togglePopup = this.togglePopup.bind(this);

    }

    onMove(from, to) {
        console.log(from, to);
    }

    render() {
        return (
            <Modal isOpen={this.props.popupOpen} centered={true} toggle={this.togglePopup}>
                <ModalHeader>Invite an opponent</ModalHeader>
                <ModalBody>
                    <Chessground
                        width="38vw"
                        height="38vw"
                        fen={this.props.fen}
                        onMove={this.onMove}
                        />
                </ModalBody>
            </Modal>
        )
    }

    togglePopup() {
        this.props.togglePopup();
    }
}


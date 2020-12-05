import React, {Component } from 'react';
import {Button, ModalBody, ModalFooter, ModalHeader, Input, Table} from "reactstrap";
import Modal from "@material-ui/core/Modal";
import Chessground from "./chessground";

export default class Chessboard extends Component {
    constructor(props) {
        super(props);

        this.togglePopup = this.togglePopup.bind(this);

    }

    render() {
        return (
            <Modal open={this.props.popupOpen} onClose={this.togglePopup}>
            <div>
                <Chessground />
            </div>
            </Modal>
        )
    }

    togglePopup() {
        this.props.togglePopup();
    }
}

